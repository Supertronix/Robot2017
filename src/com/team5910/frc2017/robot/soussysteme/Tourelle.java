package com.team5910.frc2017.robot.soussysteme;

import java.awt.print.Printable;
import java.io.Console;

import com.ctre.CANTalon;
import com.team5910.frc2017.commande.tourelle.CommandeTourelleChercherCible;
import com.team5910.frc2017.commande.tourelle.CommandeTourelleChercherCibleAuto;
import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.interaction.AffichageStation;
import com.team5910.frc2017.robot.interaction.vision.VisionData;
import com.team5910.frc2017.robot.outil.Calculateur;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Tourelle extends Subsystem implements RobotMap.Tourelle{
	
    public VisionData visionData;	// TODO verifier concurrence
	
	public enum EtatControle {
        INACTIF,
		MANUEL, // The turret is manually controlled
        RECHERCHE, // The turret is trying to find the target
        VERROUILLE, // The turret is locked to target and listening raspberry pi to follow
    }
	
	private EtatControle etatActuel;
	private CommandeTourelleChercherCible commandeChercherCible;
	
	public EtatControle getState()
	{
		return etatActuel;
	}
	public void setEtatControle(EtatControle etatDesire)
	{
		if (commandeChercherCible == null) { commandeChercherCible = new CommandeTourelleChercherCible(); } 
		
		etatActuel = etatDesire;
		if (etatDesire == EtatControle.RECHERCHE)
		{
			//tourelleTilt.changeControlMode(CANTalon.TalonControlMode.Position);
			tourelleTilt.setControlMode(0);
			tourellePan.changeControlMode(CANTalon.TalonControlMode.Position);
			commandeChercherCible.start();
		}
		else if (etatDesire == EtatControle.VERROUILLE)
		{
			//tourelleTilt.changeControlMode(CANTalon.TalonControlMode.Position);
			tourelleTilt.setControlMode(0);
			commandeChercherCible.cancel();
			setPanCible(tourellePan.getPosition());
			tourellePan.setControlMode(0); // DIRECT MOTOR DRIVE
		}
		else if (etatDesire == EtatControle.MANUEL)
		{
			commandeChercherCible.cancel();
			tourellePan.setControlMode(0);
			tourelleTilt.setControlMode(0);
		}
	}
	
	public void setTiltPositionMode()
	{
		tourelleTilt.changeControlMode(CANTalon.TalonControlMode.Position);
	}

	public void setAutoState()
	{
		etatActuel = EtatControle.RECHERCHE;
		tourelleTilt.changeControlMode(CANTalon.TalonControlMode.Position);
		tourellePan.changeControlMode(CANTalon.TalonControlMode.Position);
		
	}
	public CANTalon tourellePan = new CANTalon(TOURELLE_PAN_MOTEUR);
	public CANTalon tourelleTilt = new CANTalon(TOURELLE_TILT_MOTEUR);
	
	double panCible = TOURELLE_PAN_DEFAUT;
	double cibleTilt = TOURELLE_TILT_DEFAUT;
	
	double autoSPupdate = 0.0;

	public Tourelle() {
		etatActuel = EtatControle.MANUEL;
		
		//tourellePan.changeControlMode(CANTalon.TalonControlMode.Position);
		tourellePan.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		tourellePan.setPID(PAN_KP, PAN_KI, 0);
		
		tourellePan.setControlMode(0);
		tourellePan.setPosition(0);
		tourellePan.enable();
		tourellePan.setForwardSoftLimit(TOURELLE_PAN_LIMITE_MAXIMUM);
		tourellePan.enableForwardSoftLimit(true);
		tourellePan.setReverseSoftLimit(TOURELLE_PAN_LIMITE_MINIMUM);
		tourellePan.enableReverseSoftLimit(true);
		
		tourellePan.reverseSensor(true);
		
		//tourelleTilt.changeControlMode(CANTalon.TalonControlMode.Position);
		tourelleTilt.setControlMode(0);
		tourelleTilt.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		tourelleTilt.setPID(TILT_KP, TILT_KI, 0);
		//tourelleTilt.setPosition(0);
		tourelleTilt.setForwardSoftLimit(TOURELLE_TILT_LIMITE_MAXIMUM);
		tourelleTilt.enableForwardSoftLimit(true);
		
		tourelleTilt.setReverseSoftLimit(TOURELLE_TILT_LIMITE_MINIMUM);
		tourelleTilt.enableReverseSoftLimit(true);
		tourelleTilt.reverseSensor(true);
		tourelleTilt.enable();	
		
	}
	
	 public void setPanCible(double ciblePan) {
		 if (etatActuel == EtatControle.INACTIF)
			 return;
		 ciblePan = ciblePan;
		 Calculateur.clamp(ciblePan, TOURELLE_PAN_LIMITE_MINIMUM, TOURELLE_PAN_LIMITE_MAXIMUM);
		 tourellePan.set(ciblePan); 
	    }
	 
	 public void setTiltCible(double cibleTilt) {
		 if (etatActuel == EtatControle.INACTIF)
			 return;
		 cibleTilt = cibleTilt;
		 Calculateur.clamp(cibleTilt, TOURELLE_TILT_LIMITE_MINIMUM, TOURELLE_TILT_LIMITE_MAXIMUM);
		 tourelleTilt.set(cibleTilt);
	    }
	 
	 public void offsetPanSP(double deltaPan) {
		 if (etatActuel == EtatControle.INACTIF)
			 return;
		 panCible += deltaPan;
		 Calculateur.clamp(panCible, TOURELLE_PAN_LIMITE_MINIMUM, TOURELLE_PAN_LIMITE_MAXIMUM);
		 tourellePan.set(panCible);
		 
	    }
	 
	 public void offsetTiltSP(double deltaTilt) {
		 if (etatActuel == EtatControle.INACTIF)
			 return;
		 cibleTilt += deltaTilt;
		//Utilities.clamp(tiltSP, kTiltLowSPLimit, kTiltHighSPLimit);
		 //TurretTiltDrive.set(tiltSP);
	    }

	 public void inverserControleAutoMan() {
		 switch (etatActuel) {
			 case INACTIF:
				 setEtatControle(EtatControle.MANUEL);
				 break;
			 case MANUEL:
				 setEtatControle(EtatControle.RECHERCHE);
				 break; 
			 case RECHERCHE:
				 setEtatControle(EtatControle.MANUEL);
				 break; 
			 case VERROUILLE:
				 setEtatControle(EtatControle.MANUEL);
				 break; 
		 }
	    }

	public void arreter() {
		tourellePan.disable();
		tourelleTilt.disable();
		etatActuel = EtatControle.INACTIF;
		
	}
	
	public void conduireManuellement(double ciblePan, double cibleTilt) {
		if (etatActuel == EtatControle.INACTIF)
			 return;
		else if (etatActuel == EtatControle.MANUEL)
			tourellePan.set(ciblePan);
			tourelleTilt.set(cibleTilt);	
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void ajusterPan(double cible)
	{
		if (etatActuel == EtatControle.VERROUILLE)
		{
			tourellePan.set(cible); // DIrect drive
		}
			
	}
	
	public void ajusterTilt(double aDistance)
	{
		/*SmartDashboard.putNumber("DISTANCE DETECTED", aDistance);
		
		// SP 650 - 730
		
		// distance // SP
		// OLD 20 // -658
		
		// 20.6 - 656 pot
		// 19.75 - 644 pot
		// 19.3 - 629 pot
		// 18.68 - 622
		
		// FORMULE : y=ax+b 
		double a = 15.625;
		double b = 158.13;
		
		// Distance
		if (actualState == SystemState.AUTO_LOCK)
		{
			double result = -(a*aDistance + b);
			setTiltSetpoint(result);
			System.out.println ("UPDATE TILT SET POINT TO" + result);
		}*/
			
	}
	
	public void setEtat(boolean cibleEnVue)
	{
		if (cibleEnVue && etatActuel == EtatControle.RECHERCHE)
		{
			setEtatControle(EtatControle.VERROUILLE);
		}
		else if (!cibleEnVue && etatActuel == EtatControle.VERROUILLE)
		{
			setEtatControle(EtatControle.RECHERCHE);
		}
	}
	
	
	public boolean aFiniPanCible()
	{
		return (Math.abs(tourellePan.getClosedLoopError()) <= 5);
	}
	
	public boolean aFiniTiltCible()
	{
		return (Math.abs(tourelleTilt.getClosedLoopError()) <= 5);
	}
	public boolean aFiniPanMoteur()
	{
		return (Math.abs(tourellePan.get()) <= 0.2);
	}
	
	public void gotoPanOppositeSP()
	{
		if (panCible == TOURELLE_PAN_LIMITE_MINIMUM)
		{
			setPanCible(TOURELLE_PAN_LIMITE_MAXIMUM);
		}
		else
		{
			setPanCible(TOURELLE_PAN_LIMITE_MINIMUM);
		}
			
	}
	
	public void journaliser()
	{
		SmartDashboard.putNumber("TILT P", tourelleTilt.getP());
		SmartDashboard.putNumber("TILT I", tourelleTilt.getI());
		SmartDashboard.putNumber("TILT SP", cibleTilt);
	}	

}

