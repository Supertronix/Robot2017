package org.usfirst.frc.team5910.robot;

import java.io.IOException;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team5910.robot.Libs.ADXRS450_Supertronix;
import org.usfirst.frc.team5910.robot.RaspCom.UDPReceiver;

import com.ctre.CANTalon;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

enum state {
	INIT,
	CALIBRATION,
	AUTONOMOUS,
	TELEOP,
	TESTS,
	FINAL	
}

public class Robot extends IterativeRobot 
{
	Thread visionThread;
	RobotDrive myRobot = new RobotDrive(0, 1); // class that handles basic drive
	Joystick stick = new Joystick(0); // set to ID 1 in DriverStation
	ADXRS450_Supertronix gyro = new ADXRS450_Supertronix();
	CANTalon turretRot = new CANTalon(RobotMap.kTurretRotTalonId);
	VictorSP testDrive = new VictorSP(2);
	Relay ledstrip = new Relay(0);
	Servo testServo = new Servo(9);
	
	@Override
	public void robotInit() 
	{
		turretRot.changeControlMode(CANTalon.TalonControlMode.Position);
		turretRot.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		//turretRot.setControlMode(0);
		//turretRot.enable();
		//t

		ledstrip.setDirection(Relay.Direction.kForward);
		SmartDashboard.putNumber("turretwantedpos", 511.0);
		SmartDashboard.putNumber("servo", 0.0);
		SmartDashboard.putNumber("testMot", 0.0);
		SmartDashboard.putBoolean("ledstrip", false);
		SmartDashboard.putNumber("P", 50.0);
		SmartDashboard.putNumber("I", 0.000000001);
		SmartDashboard.putNumber("D", 0.0);
		try
		{
			System.out.println("Trying to start UDP receiver");
			SmartDashboard.putString("VisionStatus","Trying to start UDP receiver");
			new UDPReceiver().start();
		} catch (IOException e)
		{
			SmartDashboard.putString("VisionStatus","Exception while starting UDP receiver");
			e.printStackTrace();
		}
	
		visionThread = new Thread(() -> {
			// Get the UsbCamera from CameraServer
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			// Set the resolution
			camera.setResolution(640, 480);

			// Get a CvSink. This will capture Mats from the camera
			CvSink cvSink = CameraServer.getInstance().getVideo();
			// Setup a CvSource. This will send images back to the Dashboard
			CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);

			// Mats are very memory expensive. Lets reuse this Mat.
			Mat mat = new Mat();

			// This cannot be 'true'. The program will never exit if it is. This
			// lets the robot stop this thread when restarting robot code or
			// deploying.
			while (!Thread.interrupted()) {
				// Tell the CvSink to grab a frame from the camera and put it
				// in the source mat.  If there is an error notify the output.
				if (cvSink.grabFrame(mat) == 0) {
					// Send the output the error.
					outputStream.notifyError(cvSink.getError());
					// skip the rest of the current iteration
					continue;
				}
				// Put a rectangle on the image
				Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
						new Scalar(255, 255, 255), 5);
				// Give the output stream a new image to display
				outputStream.putFrame(mat);
			}
		});
		visionThread.setDaemon(true);
		visionThread.start();
	}

	@Override
	public void autonomousInit() 
	{
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic()
	{
	}
	@Override
	public void teleopInit() 
	{
		gyro.reset();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() 
	{
		
		
		testServo.set(SmartDashboard.getNumber("servo", 0.0));
		
		SmartDashboard.putNumber("Capteur", turretRot.getAnalogInRaw());
		turretRot.set(SmartDashboard.getNumber("turretwantedpos", 511.0));
		SmartDashboard.putNumber("Commande", turretRot.getOutputVoltage());
		turretRot.setPID(SmartDashboard.getNumber("P", 50.0), SmartDashboard.getNumber("I", 0.000000001),SmartDashboard.getNumber("D", 0.0));
		
		testDrive.set(SmartDashboard.getNumber("testMot", 0.0));
		myRobot.arcadeDrive(stick);
	
		if (SmartDashboard.getBoolean("ledstrip", false))
			ledstrip.set(Relay.Value.kOn);
		else
			ledstrip.set(Relay.Value.kOff);
		
		SmartDashboard.putNumber("GyroAngle", gyro.getAngle()%360);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() 
	{
	
	}
}

