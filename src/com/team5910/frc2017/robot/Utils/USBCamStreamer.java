package com.team5910.frc2017.robot.Utils;

import java.io.IOException;

import org.opencv.core.Mat;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class USBCamStreamer extends Thread
{
	CvSink cvSink;
	CvSource outputStream;
	Mat mat;
	
	public USBCamStreamer() throws IOException {
	    this("CameraServer");
	    }
	
	public USBCamStreamer(String name) throws IOException {
        super(name);
        
        // Get the UsbCamera from CameraServer
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        
        // Set the resolution
     	camera.setResolution(640, 480);

     	// Get a CvSink. This will capture Mats from the camera
     	cvSink = CameraServer.getInstance().getVideo();
     	
		// Setup a CvSource. This will send images back to the Dashboard
		outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);
		
		// Mats are very memory expensive. Lets reuse this Mat.
		mat = new Mat();
    }
	
	public void run() {
		while (!Thread.interrupted()) {
		if (cvSink.grabFrame(mat) == 0) {
			// Send the output the error.
			outputStream.notifyError(cvSink.getError());
			// skip the rest of the current iteration
			continue;
		}
		// Give the output stream a new image to display
		outputStream.putFrame(mat);
		}

	}
}
