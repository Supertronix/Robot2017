package com.team5910.frc2017.robot;

import java.io.IOException;
import edu.wpi.first.wpilibj.IterativeRobot;

import com.team5910.frc2017.robot.RaspCom.GRIPReceiver;
import com.team5910.frc2017.robot.Subsystems.Drive;
import com.team5910.frc2017.robot.Subsystems.Superstructure;
import com.team5910.frc2017.robot.Utils.Controller;
import com.team5910.frc2017.robot.Utils.USBCamStreamer;
import com.team5910.frc2017.robot.Utils.Utilities;

public class Robot extends IterativeRobot 
{
	// Subsystems
    Drive mDrive = Drive.getInstance();
    Superstructure mSuperstructure = new Superstructure();
    Controller mController = Controller.getInstance();
	
	//public static double lastCommandReceived = 0.0f;
	
	public void stopAll() {
        mDrive.stop();
        mSuperstructure.stopAll();
    }
	
	 public void zeroAllSensors() {
	        mDrive.zeroSensors();
	        mSuperstructure.zeroSensors();
	 }
	
	@Override
	public void robotInit() 
	{
		// Reset all state
        zeroAllSensors();
        
		try { new USBCamStreamer().start(); } catch (IOException e) { e.printStackTrace(); }
		try { new GRIPReceiver().start(); } catch (IOException e) { e.printStackTrace(); }
	}

	@Override
	public void autonomousInit() 
	{
	}

	@Override
	public void autonomousPeriodic()
	{
	}
	@Override
	public void teleopInit() 
	{
	}

	@Override
	public void teleopPeriodic() 
	{
		
		double x1 = 0;
		double y1 = 0;
		double x2 = 0;
		double y2 = 0;
		
		
		if (Math.abs(mController.getLeftDriveX()) > .2)
			x1 = mController.getLeftDriveX();
       
        if (Math.abs(mController.getLeftDriveY()) > .2)
        	y1 = mController.getLeftDriveY();
       
        if (Math.abs(mController.getRightDriveX()) > .2)
            x2 = mController.getRightDriveX();
        
        if (Math.abs(mController.getRightDriveY()) > .2)
            y2 = mController.getRightDriveY();
        
        double x = (x1 + x2)/ 2;
        
        mDrive.manualDrive(Utilities.clamp(x + y1, -1, 1), Utilities.clamp(y2 - x, -1, 1), Utilities.clamp(y1 - x, -1, 1), Utilities.clamp(x + y2, -1, 1));
        
        if (mController.getIntakeButton()) { mSuperstructure.intakeButtonEnabled(); } else { mSuperstructure.intakeButtonDisabled(); }         
        if (mController.getClimberButton()) { mSuperstructure.climberButtonEnabled(); } else { mSuperstructure.climberButtonDisabled(); }
        if (mController.getClampButton()) { mSuperstructure.clampButtonEnabled(); }
        	
	}

}

