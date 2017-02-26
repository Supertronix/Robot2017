package com.team5910.frc2017.robot;

import java.io.IOException;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import com.team5910.frc2017.commands.DepositGearAndPassLine;
import com.team5910.frc2017.commands.Turret.TurretSetState;
import com.team5910.frc2017.robot.RaspCom.GRIPReceiver;
import com.team5910.frc2017.robot.Subsystems.Drive;
import com.team5910.frc2017.robot.Subsystems.Superstructure;
import com.team5910.frc2017.robot.Subsystems.Tourelle;
import com.team5910.frc2017.robot.Utils.OI;
import com.team5910.frc2017.robot.Utils.USBCamStreamer;
import com.team5910.frc2017.robot.Utils.Utilities;

public class Robot extends IterativeRobot 
{
	// Subsystems
	public static Drive drive;
	public static Superstructure superstructure;
	public static OI oi;
	
    Command autonomousCommand;
    
	//public static double lastCommandReceived = 0.0f;
	
	public void stopAll() {
        drive.arreter();
        superstructure.stopAll();
    }
	
	 public void zeroAllSensors() {
	        drive.zeroSensors();
	        superstructure.zeroSensors();
	 }
	
	@Override
	public void robotInit() 
	{
		drive = new Drive();
		superstructure = new Superstructure();
		oi = new OI();
		
		// Reset all state
        zeroAllSensors();
        
		try { new USBCamStreamer().start(); } catch (IOException e) { e.printStackTrace(); }
		try { new GRIPReceiver(superstructure.tourelle).start(); } catch (IOException e) { e.printStackTrace(); }
		autonomousCommand = new DepositGearAndPassLine();
	}

	@Override
	public void autonomousInit() 
	{
		drive.zeroSensors();
		autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
		drive.updateDashboard();
	}
	
	@Override
	public void teleopInit() 
	{
		autonomousCommand.cancel();
		new TurretSetState(Tourelle.SystemState.MANUAL_CONTROL);
		Scheduler.getInstance().run();
	}
	
	
	@Override
	public void teleopPeriodic() 
	{
		Scheduler.getInstance().run();
		double x1 = 0;
		double y1 = 0;
		double x2 = 0;
		double y2 = 0;
		
		
		if (Math.abs(oi.getLeftDriveX()) > .2)
			x1 = oi.getLeftDriveX();
       
        if (Math.abs(oi.getLeftDriveY()) > .2)
        	y1 = oi.getLeftDriveY();
       
        if (Math.abs(oi.getRightDriveX()) > .2)
            x2 = oi.getRightDriveX();
        
        if (Math.abs(oi.getRightDriveY()) > .2)
            y2 = oi.getRightDriveY();
        
        double x = (x1 + x2)/ 2;
        
        drive.manualDrive(Utilities.clamp(x + y1, -1, 1), Utilities.clamp(y2 - x, -1, 1), Utilities.clamp(y1 - x, -1, 1), Utilities.clamp(x + y2, -1, 1));
        
        double pan = 0.0;
        double tilt = 0.0;
     
        if (Math.abs(oi.getPanAxis()) > .2)
            pan = oi.getPanAxis();
        
        if (Math.abs(oi.getTiltAxis()) > .2)
            tilt = oi.getTiltAxis();
        
        superstructure.tourelle.manualDrive(pan, tilt);
        superstructure.tourelle.updateDashboard();
        
        Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	@Override
	public void disabledInit() 
	{	
	}
	
	@Override
	public void disabledPeriodic() 
	{	
	}
	
	@Override
	public void robotPeriodic() 
	{	
	}

}

