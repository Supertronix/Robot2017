package com.team5910.frc2017.robot.RaspCom;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;

import com.google.gson.Gson;
import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GRIPReceiver extends Thread
{
	public static String lastDataReceived = "";
	protected DatagramSocket socket = null;
	protected BufferedReader in = null;
    protected boolean moreQuotes = true;
    Gson gson = new Gson();
    
	public GRIPReceiver() throws IOException {
	    this("udpReciever");
	    }
	
	public GRIPReceiver(String name) throws IOException {
        super(name);
        SmartDashboard.putString("VisionStatus","UDPReceiver constructor called");
        socket = new DatagramSocket(3620);
        SmartDashboard.putString("VisionStatus","UDPReceiver datagram binding");
    }
	
	public void run() {
		SmartDashboard.putString("VisionStatus","UDPReceiver running");
    	System.out.println("thread start");
    	byte[] buf = new byte[256];
    	DatagramPacket packet = new DatagramPacket(buf, buf.length);
    	
    	while (moreQuotes) {
            try {
            	SmartDashboard.putString("VisionStatus","Test");
                // receive request
            	packet.setLength(buf.length);
                socket.receive(packet);
                byte[] data = packet.getData();
                lastDataReceived = new String(data, 0, packet.getLength());
                //Robot.lastCommandReceived = Double.parseDouble(lastDataReceived);
                
                //SmartDashboard.putString("last Data Received", lastDataReceived);
                System.out.println ("'" + lastDataReceived + "'");
                // figure out response
                //visionData = gson.fromJson(lastDataReceived, VisionData.class);
                //visionData.whenRecieved = System.currentTimeMillis();
        		//System.out.println(visionData);
            } catch (IOException e) {
                e.printStackTrace();
                SmartDashboard.putString("VisionStatus","Exception in receiving data");
                moreQuotes = false;
            }
        }
        socket.close();
	}
	
	 /*public String toHex(String arg) {
	        return String.format("%040x", new BigInteger(1, arg.getBytes()));
	    }*/
	
}
