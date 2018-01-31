package com.team5910.frc2017.robot.interaction.vision;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.google.gson.Gson;
import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.interaction.AffichageStation;
import com.team5910.frc2017.robot.soussysteme.Tourelle;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionEcouteur extends Thread implements RobotMap.Vision
{
	
    DigitalOutput visionModeRaspberry = new DigitalOutput(RASPBERRY_PIN);
	
	public static String donneeRecue = "";
	protected DatagramSocket connexion = null;
	protected BufferedReader lecteur = null;
    protected boolean encoreDesDonnees = true;
    protected Tourelle tourelle = null;
    //public VisionData visionData;
    
    Gson gson = new Gson();
    
	public VisionEcouteur(Tourelle tourelle) throws IOException {
	    this("udpReciever");
	    this.tourelle = tourelle; // TODO - voir s'il faut gerer une synchronisation
		visionModeRaspberry.disablePWM();
		visionModeRaspberry.set(false);
	    }
	
	public VisionEcouteur(String name) throws IOException {
        super(name);
        SmartDashboard.putString(AffichageStation.VISION_STATUT,"Vision : Ecouteur en construction");
        connexion = new DatagramSocket(RASPBERRY_PORT);
        SmartDashboard.putString(AffichageStation.VISION_STATUT,"Vision : Liaison de la connexion");
    }
	
	
	public void run() {
		SmartDashboard.putString(AffichageStation.VISION_STATUT,"Vision : Reception active");
    	System.out.println("thread vision start");
    	byte[] tampon = new byte[256];
    	DatagramPacket packet = new DatagramPacket(tampon, tampon.length);
    	
    	while (encoreDesDonnees) {
            try {
            	SmartDashboard.putString(AffichageStation.VISION_STATUT, Utility.getFPGATime() + "Vision : Reception de donnees");
                // receive request
            	packet.setLength(tampon.length);
                connexion.receive(packet);
                byte[] data = packet.getData();
                donneeRecue = new String(data, 0, packet.getLength());
                SmartDashboard.putString(AffichageStation.VISION_DONNEE, donneeRecue);
                //System.out.println ("'" + donneeRecue + "'");
                
                //tourelle.setPanSetpoint(Double.parseDouble(lastDataReceived));
                //tourelle.tourellePan.set(Double.parseDouble(lastDataReceived)*5);
                //tourelle.ajusterPan(Double.parseDouble(lastDataReceived)*5);
                
                tourelle.visionData = gson.fromJson(donneeRecue, VisionData.class);
                tourelle.visionData.whenRecieved = System.currentTimeMillis();
                tourelle.setEtat(tourelle.visionData.trouvee);
                //if(!tourelle.visionData.trouvee && Math.abs(tourelle.visionData.positionX) >= 0.1)
               // {
            	tourelle.ajusterPan(tourelle.visionData.positionX*0.75); //15
            	tourelle.ajusterTilt(tourelle.visionData.distanceAvecRobot);
                //}               
            } catch (IOException e) {
                e.printStackTrace();
                SmartDashboard.putString(AffichageStation.VISION_STATUT, "Vision : Exception dans la reception des donnees");
                encoreDesDonnees = false;
            }
        }
        connexion.close();
	}
	
}
