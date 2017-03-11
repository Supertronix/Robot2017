package com.team5910.frc2017.robot;

import java.io.IOException;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.team5910.frc2017.commands.CommandeR2;
import com.team5910.frc2017.commands.CommandeR3;
import com.team5910.frc2017.commands.CommandeImmobile;
import com.team5910.frc2017.commands.CommandeR1;
import com.team5910.frc2017.commands.drive.CommandeConduiteDistance;
import com.team5910.frc2017.commands.tourelle.TurretSetState;
import com.team5910.frc2017.robot.outils.OI;
import com.team5910.frc2017.robot.outils.USBCamStreamer;
import com.team5910.frc2017.robot.outils.Utilities;
import com.team5910.frc2017.robot.raspberry.GRIPReceiver;
import com.team5910.frc2017.robot.subsystems.Drive;
import com.team5910.frc2017.robot.subsystems.Superstructure;
import com.team5910.frc2017.robot.subsystems.Tourelle;

public class Robot extends IterativeRobot 
{
	// Subsystems
	public static Drive drive;
	public static Superstructure superstructure;
	public static OI oi;
	
    Command autonomousCommand;
    SendableChooser autoChooser;
    
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
		
		autoChooser = new SendableChooser();
		autoChooser.addDefault("No move", new CommandeImmobile());
		autoChooser.addObject("R1", new CommandeR1());
		autoChooser.addObject("R2", new CommandeR2());
		autoChooser.addObject("R3", new CommandeR3());
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);
	}

	@Override
	public void autonomousInit() 
	{
		autonomousCommand = (Command) autoChooser.getSelected();
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
		if (autonomousCommand != null) { autonomousCommand.cancel(); }
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
		
		
		if (Math.abs(oi.getConduiteGaucheX()) > .2)
			x1 = oi.getConduiteGaucheX();
       
        if (Math.abs(oi.getConduiteGaucheY()) > .2)
        	y1 = oi.getConduiteGaucheY();
       
        if (Math.abs(oi.getConduiteDroiteX()) > .2)
            x2 = oi.getConduiteDroiteX();
        
        if (Math.abs(oi.getConduiteDroiteY()) > .2)
            y2 = oi.getConduiteDroiteY();
        
        double x = (x1 + x2)/ 2;
        
        drive.manualDrive(Utilities.clamp(x + y1, -1, 1), Utilities.clamp(y2 - x, -1, 1), Utilities.clamp(y1 - x, -1, 1), Utilities.clamp(x + y2, -1, 1));
        
        double pan = 0.0;
        double tilt = 0.0;
     
        if (Math.abs(oi.getPanAxe()) > .2)
            pan = oi.getPanAxe();
        
        if (Math.abs(oi.getTiltAxe()) > .2)
            tilt = oi.getTiltAxe();
        
        superstructure.tourelle.manualDrive(pan, tilt);
        superstructure.tourelle.updateDashboard();
        drive.updateDashboard();
        
        Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
        
        superstructure.tourelle.TeleopPeriodic();
	}
	
	@Override
	public void disabledInit() 
	{	
		if (autonomousCommand != null) { autonomousCommand.cancel(); }
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

