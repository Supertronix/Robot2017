package com.team5910.frc2017.robot.interaction.vision;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.google.gson.Gson;
import com.team5910.frc2017.robot.RobotControleur;
import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.interaction.AffichageStation;
import com.team5910.frc2017.robot.soussysteme.Tourelle;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionEcouteur extends Thread
{
	
    DigitalOutput visionModeRaspberry = new DigitalOutput(25);
	
	public static String donneeRecue = "";
	protected DatagramSocket socket = null;
	protected BufferedReader in = null;
    protected boolean moreQuotes = true;
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
        SmartDashboard.putString(AffichageStation.VISION_STATUT,"UDPReceiver constructor called");
        socket = new DatagramSocket(RobotMap.RASPBERRY_PORT);
        SmartDashboard.putString(AffichageStation.VISION_STATUT,"UDPReceiver datagram binding");
    }
	
	
	public void run() {
		SmartDashboard.putString(AffichageStation.VISION_STATUT,"UDPReceiver running");
    	System.out.println("thread start");
    	byte[] buf = new byte[256];
    	DatagramPacket packet = new DatagramPacket(buf, buf.length);
    	
    	while (moreQuotes) {
            try {
            	SmartDashboard.putString(AffichageStation.VISION_STATUT, Utility.getFPGATime() + "Receiving data");
                // receive request
            	packet.setLength(buf.length);
                socket.receive(packet);
                byte[] data = packet.getData();
                donneeRecue = new String(data, 0, packet.getLength());
                SmartDashboard.putString("last Data Received", donneeRecue);
                //Robot.lastCommandReceived = Double.parseDouble(lastDataReceived);
                //System.out.println ("'" + donneeRecue + "'");
                
                //tourelle.setPanSetpoint(Double.parseDouble(lastDataReceived));
                //tourelle.TurretPanDrive.set(Double.parseDouble(lastDataReceived)*5);
                //tourelle.gripUpdatePan(Double.parseDouble(lastDataReceived)*5);
                
                tourelle.visionData = gson.fromJson(donneeRecue, VisionData.class);
                tourelle.visionData.whenRecieved = System.currentTimeMillis();
                tourelle.gripUpdateState(tourelle.visionData.trouvee);
                //if(!tourelle.visionData.trouvee && Math.abs(tourelle.visionData.positionX) >= 0.1)
               // {
            	tourelle.gripUpdatePan(tourelle.visionData.positionX*0.75); //15
            	tourelle.gripUpdateTilt(tourelle.visionData.distanceAvecRobot);
                //}               
            } catch (IOException e) {
                e.printStackTrace();
                SmartDashboard.putString(AffichageStation.VISION_STATUT,"Exception in receiving data");
                moreQuotes = false;
            }
        }
        socket.close();
	}
	
}
