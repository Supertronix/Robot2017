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
import com.team5910.frc2017.robot.interaction.SelecteurModeAutonome;
import com.team5910.frc2017.robot.interaction.SelecteurModeAutonomeViaInterrupteur;
import com.team5910.frc2017.robot.interaction.SelecteurModeAutonomeViaDashBoard;
import com.team5910.frc2017.robot.interaction.vision.USBCamStreamer;
import com.team5910.frc2017.robot.interaction.vision.VisionEcouteur;
import com.team5910.frc2017.robot.outil.Calculateur;
import com.team5910.frc2017.robot.soussysteme.Roues;
import com.team5910.frc2017.robot.soussysteme.Tourelle;
import com.team5910.frc2017.robot.soussysteme.Tourelle.SystemState;
import com.team5910.frc2017.robot.trajet.CommandeTrajetB1;
import com.team5910.frc2017.robot.trajet.CommandeTrajetB2;
import com.team5910.frc2017.robot.trajet.CommandeTrajetB3;
import com.team5910.frc2017.robot.trajet.CommandeImmobile;
import com.team5910.frc2017.robot.trajet.CommandeTrajetR1;
import com.team5910.frc2017.robot.trajet.CommandeTrajetR2;
import com.team5910.frc2017.robot.trajet.CommandeTrajetR3;
import com.team5910.frc2017.robot.trajet.CommandeTrajetWTF;

import edu.wpi.first.wpilibj.DigitalInput;
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
	public static Manette manette;
	
    DigitalOutput RaspberryVisionMode = new DigitalOutput(25);
        
	//SelecteurModeAutonomeViaDashBoard selecteurModeAutonomeViaDashboard;
	//SelecteurModeAutonomeViaInterrupteur selecteurModeAutonomeViaInterrupteur;
    SelecteurModeAutonome selecteurModeAutonome;
    
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
		manette = new Manette();
		
		// Reset all state
        zeroAllSensors();
        
		try { new USBCamStreamer().start(); } catch (IOException e) { e.printStackTrace(); }
		try { new VisionEcouteur(robot.tourelle).start(); } catch (IOException e) { e.printStackTrace(); }
		
		RaspberryVisionMode.disablePWM();
		RaspberryVisionMode.set(false);

		//selecteurModeAutonomeViaDashboard = new SelecteurModeAutonomeViaDashBoard();
		//selecteurModeAutonomeViaInterrupteur = new SelecteurModeAutonomeViaInterrupteur();
		selecteurModeAutonome = new SelecteurModeAutonomeViaInterrupteur();
	}

	Command commandeAutonome;
	@Override
	public void autonomousInit() 
	{		
		Robot.drive.zeroSensors();
		Command stopMotors = new CommandeArreterBrasseurIndexeurLanceur();
		stopMotors.start();
		
		commandeAutonome = selecteurModeAutonome.lireChoix();		
		//commandeAutonome = selecteurModeAutonome.lireChoix();
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
		
		
		if (Math.abs(manette.getConduiteGaucheX()) > .2)
			x1 = manette.getConduiteGaucheX();
       
        if (Math.abs(manette.getConduiteGaucheY()) > .2)
        	y1 = manette.getConduiteGaucheY();
       
        if (Math.abs(manette.getConduiteDroiteX()) > .2)
            x2 = manette.getConduiteDroiteX();
        
        if (Math.abs(manette.getConduiteDroiteY()) > .2)
            y2 = manette.getConduiteDroiteY();
        
        double x = (x1 + x2)/ 2;
        
        Robot.drive.conduire(Calculateur.clamp(x + y1, -1, 1), Calculateur.clamp(y2 - x, -1, 1), Calculateur.clamp(y1 - x, -1, 1), Calculateur.clamp(x + y2, -1, 1));
        
        double pan = 0.0;
        double tilt = 0.0;
     
        if (Math.abs(manette.getPanAxe()) > .2)
            pan = manette.getPanAxe();
        
        if (Math.abs(manette.getTiltAxe()) > .2)
            tilt = manette.getTiltAxe();
        
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
		selecteurModeAutonome.afficherChoix();
	}
	
	


}

