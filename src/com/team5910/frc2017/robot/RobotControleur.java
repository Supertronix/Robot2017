package com.team5910.frc2017.robot;

import java.io.IOException;

import com.team5910.frc2017.commande.CommandeArreterBrasseurIndexeurLanceur;
import com.team5910.frc2017.commande.CommandeIndexeurArreter;
import com.team5910.frc2017.commande.lanceur.CommandeLanceurArreter;
import com.team5910.frc2017.commande.tourelle.CommandeTourelleChangerEtat;
import com.team5910.frc2017.commande.tourelle.CommandeTourellePositionnerPan;
import com.team5910.frc2017.commande.tourelle.CommandeTourellePositionnerTilt;
import com.team5910.frc2017.robot.interaction.AffichageStation;
import com.team5910.frc2017.robot.interaction.Manette;
import com.team5910.frc2017.robot.interaction.vision.USBCamStreamer;
import com.team5910.frc2017.robot.interaction.vision.VisionEcouteur;
import com.team5910.frc2017.robot.outil.Calculateur;
import com.team5910.frc2017.robot.soussysteme.Drive;
import com.team5910.frc2017.robot.soussysteme.Tourelle;
import com.team5910.frc2017.robot.soussysteme.Tourelle.SystemState;
import com.team5910.frc2017.robot.trajet.CommandeB1;
import com.team5910.frc2017.robot.trajet.CommandeB2;
import com.team5910.frc2017.robot.trajet.CommandeB3;
import com.team5910.frc2017.robot.trajet.CommandeImmobile;
import com.team5910.frc2017.robot.trajet.CommandeR1;
import com.team5910.frc2017.robot.trajet.CommandeR2;
import com.team5910.frc2017.robot.trajet.CommandeR3;
import com.team5910.frc2017.robot.trajet.CommandeWTF;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotControleur extends IterativeRobot 
{
	public static Robot robot;
	public static Manette oi;
	
    Command commandeAutonome;
    SendableChooser autoChooser;
    DigitalOutput RaspberryVisionMode = new DigitalOutput(25);
     
    
	//public static double lastCommandReceived = 0.0f;
	
	public void stopAll() {
        Robot.drive.arreter();
        robot.stopAll();
    }
	
	 public void zeroAllSensors() {
	        Robot.drive.zeroSensors();
	        robot.zeroSensors();
	 }
	
	@Override
	public void robotInit() 
	{		
		robot = new Robot();
		oi = new Manette();
		
		// Reset all state
        zeroAllSensors();
        
		try { new USBCamStreamer().start(); } catch (IOException e) { e.printStackTrace(); }
		try { new VisionEcouteur(robot.tourelle).start(); } catch (IOException e) { e.printStackTrace(); }
		
		RaspberryVisionMode.disablePWM();
		RaspberryVisionMode.set(false);
		
		// http://wpilib.screenstepslive.com/s/3120/m/7932/l/81109-choosing-an-autonomous-program-from-smartdashboard
		autoChooser = new SendableChooser();
		autoChooser.addDefault("No move", new CommandeImmobile());
		autoChooser.addObject("R1", new CommandeR1());
		autoChooser.addObject("R2", new CommandeR2());
		autoChooser.addObject("R3", new CommandeR3());	
		autoChooser.addObject("B1", new CommandeB1());
		autoChooser.addObject("B2", new CommandeB2());
		autoChooser.addObject("B3", new CommandeB3());	
		autoChooser.addObject("WTF",new CommandeWTF());
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);
	}

	@Override
	public void autonomousInit() 
	{
		Robot.drive.zeroSensors();
		Command stopMotors = new CommandeArreterBrasseurIndexeurLanceur();
		stopMotors.start();
		
		commandeAutonome = (Command) autoChooser.getSelected();
		commandeAutonome.start();
	}

	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
		//Robot.drive.updateDashboard();

	}
	
	@Override
	public void teleopInit() 
	{
		if (commandeAutonome != null) { commandeAutonome.cancel(); }
		Command stopMotors = new CommandeArreterBrasseurIndexeurLanceur();
		stopMotors.start();
		
		RobotControleur.robot.tourelle.setState(SystemState.MANUAL_CONTROL);
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
        
        Robot.drive.manualDrive(Calculateur.clamp(x + y1, -1, 1), Calculateur.clamp(y2 - x, -1, 1), Calculateur.clamp(y1 - x, -1, 1), Calculateur.clamp(x + y2, -1, 1));
        
        double pan = 0.0;
        double tilt = 0.0;
     
        if (Math.abs(oi.getPanAxe()) > .2)
            pan = oi.getPanAxe();
        
        if (Math.abs(oi.getTiltAxe()) > .2)
            tilt = oi.getTiltAxe();
        
       // Robot.drive.updateDashboard();
        //robot.tourelle.debugPeriodic();
        if (robot.tourelle.getState() == SystemState.AUTO_LOCK || robot.tourelle.getState() == SystemState.AUTO_SCAN)
        	robot.tourelle.manualDrive(pan*5, tilt*0.5);
        else
        	robot.tourelle.manualDrive(pan*5, tilt);
        
        Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	@Override
	public void disabledInit() 
	{	
		if (commandeAutonome != null) { commandeAutonome.cancel(); }
	}
	
	@Override
	public void disabledPeriodic() 
	{	
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);
		Timer.delay(0.2);
	}
	
	


}

