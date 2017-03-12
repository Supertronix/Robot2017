package com.team5910.frc2017.robot.interaction.vision;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.google.gson.Gson;
import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.interaction.AffichageStation;
import com.team5910.frc2017.robot.soussysteme.Tourelle;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionEcouteur extends Thread
{
	public static String lastDataReceived = "";
	protected DatagramSocket socket = null;
	protected BufferedReader in = null;
    protected boolean moreQuotes = true;
    protected Tourelle tourelle = null;
    Gson gson = new Gson();
    
	public VisionEcouteur(Tourelle tourelle) throws IOException {
	    this("udpReciever");
	    this.tourelle = tourelle; // TODO - voir s'il faut gerer une synchronisation
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
            	SmartDashboard.putString(AffichageStation.VISION_STATUT,"Test");
                // receive request
            	packet.setLength(buf.length);
                socket.receive(packet);
                byte[] data = packet.getData();
                lastDataReceived = new String(data, 0, packet.getLength());
                //Robot.lastCommandReceived = Double.parseDouble(lastDataReceived);
                
                //SmartDashboard.putString("last Data Received", lastDataReceived);
                System.out.println ("'" + lastDataReceived + "'");
                
                //tourelle.setPanSetpoint(Double.parseDouble(lastDataReceived));
                //tourelle.TurretPanDrive.set(Double.parseDouble(lastDataReceived)*5);
                tourelle.gripUpdatePan(Double.parseDouble(lastDataReceived)*5);
                // figure out response
                //visionData = gson.fromJson(lastDataReceived, VisionData.class);
                //visionData.whenRecieved = System.currentTimeMillis();
        		//System.out.println(visionData);
            } catch (IOException e) {
                e.printStackTrace();
                SmartDashboard.putString(AffichageStation.VISION_STATUT,"Exception in receiving data");
                moreQuotes = false;
            }
        }
        socket.close();
	}
	
	 /*public String toHex(String arg) {
	        return String.format("%040x", new BigInteger(1, arg.getBytes()));
	    }*/
	
}
