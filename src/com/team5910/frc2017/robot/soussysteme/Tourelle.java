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


public class Tourelle extends Subsystem {
	
    public VisionData visionData;	// TODO verifier concurrence
	
	public enum SystemState {
        DISABLED,
		MANUAL_CONTROL, // The turret is manually controlled
        AUTO_SCAN, // The turret is trying to find the target
        AUTO_LOCK, // The turret is locked to target and listening raspberry pi to follow
    }
	
	private SystemState actualState;
	private CommandeTourelleChercherCible commandeChercherCible;
	
	public SystemState getState()
	{
		return actualState;
	}
	public void setState(SystemState aWantedState)
	{
		if (commandeChercherCible == null) { commandeChercherCible = new CommandeTourelleChercherCible(); } 
		
		actualState = aWantedState;
		if (aWantedState == SystemState.AUTO_SCAN)
		{
			//tourelleTilt.changeControlMode(CANTalon.TalonControlMode.Position);
			tourelleTilt.setControlMode(0);
			tourellePan.changeControlMode(CANTalon.TalonControlMode.Position);
			commandeChercherCible.start();
		}
		else if (aWantedState == SystemState.AUTO_LOCK)
		{
			//tourelleTilt.changeControlMode(CANTalon.TalonControlMode.Position);
			tourelleTilt.setControlMode(0);
			commandeChercherCible.cancel();
			setPanSetpoint(tourellePan.getPosition());
			tourellePan.setControlMode(0); // DIRECT MOTOR DRIVE
		}
		else if (aWantedState == SystemState.MANUAL_CONTROL)
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
		actualState = SystemState.AUTO_SCAN;
		tourelleTilt.changeControlMode(CANTalon.TalonControlMode.Position);
		tourellePan.changeControlMode(CANTalon.TalonControlMode.Position);
		
	}
	public CANTalon tourellePan = new CANTalon(RobotMap.TOURELLE_PAN_MOTEUR);
	public CANTalon tourelleTilt = new CANTalon(RobotMap.TOURELLE_TILT_MOTEUR);
	
	double panSP = RobotMap.TOURELLE_PAN_DEFAUT;
	double tiltSP = RobotMap.TOURELLE_TILT_DEFAUT;
	
	double autoSPupdate = 0.0;

	public Tourelle() {
		actualState = SystemState.MANUAL_CONTROL;
		
		//tourellePan.changeControlMode(CANTalon.TalonControlMode.Position);
		tourellePan.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		tourellePan.setPID(RobotMap.PAN_KP, RobotMap.PAN_KI, 0);
		
		tourellePan.setControlMode(0);
		tourellePan.setPosition(0);
		tourellePan.enable();
		tourellePan.setForwardSoftLimit(RobotMap.TOURELLE_PAN_LIMITE_MAXIMUM);
		tourellePan.enableForwardSoftLimit(true);
		tourellePan.setReverseSoftLimit(RobotMap.TOURELLE_PAN_LIMITE_MINIMUM);
		tourellePan.enableReverseSoftLimit(true);
		
		tourellePan.reverseSensor(true);
		
		//tourelleTilt.changeControlMode(CANTalon.TalonControlMode.Position);
		tourelleTilt.setControlMode(0);
		tourelleTilt.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		tourelleTilt.setPID(RobotMap.TILT_KP, RobotMap.TILT_KI, 0);
		//tourelleTilt.setPosition(0);
		tourelleTilt.setForwardSoftLimit(RobotMap.TOURELLE_TILT_LIMITE_MAXIMUM);
		tourelleTilt.enableForwardSoftLimit(true);
		
		tourelleTilt.setReverseSoftLimit(RobotMap.TOURELLE_TILT_LIMITE_MINIMUM);
		tourelleTilt.enableReverseSoftLimit(true);
		tourelleTilt.reverseSensor(true);
		tourelleTilt.enable();	
		
	}
	
	 public void setPanSetpoint(double aPanSP) {
		 if (actualState == SystemState.DISABLED)
			 return;
		 panSP = aPanSP;
		 Calculateur.clamp(panSP, RobotMap.TOURELLE_PAN_LIMITE_MINIMUM, RobotMap.TOURELLE_PAN_LIMITE_MAXIMUM);
		 tourellePan.set(panSP); 
	    }
	 
	 public void setTiltSetpoint(double aTiltSP) {
		 if (actualState == SystemState.DISABLED)
			 return;
		 tiltSP = aTiltSP;
		 Calculateur.clamp(tiltSP, RobotMap.TOURELLE_TILT_LIMITE_MINIMUM, RobotMap.TOURELLE_TILT_LIMITE_MAXIMUM);
		 tourelleTilt.set(tiltSP);
	    }
	 
	 public void offsetPanSP(double aPanOffset) {
		 if (actualState == SystemState.DISABLED)
			 return;
		 panSP += aPanOffset;
		 Calculateur.clamp(panSP, RobotMap.TOURELLE_PAN_LIMITE_MINIMUM, RobotMap.TOURELLE_PAN_LIMITE_MAXIMUM);
		 tourellePan.set(panSP);
		 
	    }
	 
	 public void offsetTiltSP(double aTiltOffset) {
		 if (actualState == SystemState.DISABLED)
			 return;
		 tiltSP += aTiltOffset;
		//Utilities.clamp(tiltSP, RobotMap.kTiltLowSPLimit, RobotMap.kTiltHighSPLimit);
		 //TurretTiltDrive.set(tiltSP);
	    }

	 public void toggleAutoMan() {
		 switch (actualState) {
			 case DISABLED:
				 setState(SystemState.MANUAL_CONTROL);
				 break;
			 case MANUAL_CONTROL:
				 setState(SystemState.AUTO_SCAN);
				 break; 
			 case AUTO_SCAN:
				 setState(SystemState.MANUAL_CONTROL);
				 break; 
			 case AUTO_LOCK:
				 setState(SystemState.MANUAL_CONTROL);
				 break; 
		 }
	    }

	public void arreter() {
		tourellePan.disable();
		tourelleTilt.disable();
		actualState =  SystemState.DISABLED;
		
	}
	
	public void manualDrive(double aPanValue, double aTiltValue) {
		if (actualState == SystemState.DISABLED)
			 return;
		else if (actualState == SystemState.MANUAL_CONTROL)
			//offsetPanSP(aPanValue);
			tourellePan.set(aPanValue);
			tourelleTilt.set(aTiltValue);	
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void gripUpdatePan(double aTargetX)
	{
		if (actualState == SystemState.AUTO_LOCK)
		{
			tourellePan.set(aTargetX); // DIrect drive
		}
			
	}
	
	public void gripUpdateTilt(double aDistance)
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
	
	public void gripUpdateState(boolean targetSeen)
	{
		if (targetSeen && actualState == SystemState.AUTO_SCAN)
		{
			setState(SystemState.AUTO_LOCK);
		}
		else if (!targetSeen && actualState == SystemState.AUTO_LOCK)
		{
			setState(SystemState.AUTO_SCAN);
		}
	}
	
	
	public boolean panSPdone()
	{
		return (Math.abs(tourellePan.getClosedLoopError()) <= 5);
	}
	
	public boolean tiltSPdone()
	{
		return (Math.abs(tourelleTilt.getClosedLoopError()) <= 5);
	}
	public boolean isPanMotorDone()
	{
		return (Math.abs(tourellePan.get()) <= 0.2);
	}
	
	public void gotoPanOppositeSP()
	{
		if (panSP == RobotMap.TOURELLE_PAN_LIMITE_MINIMUM)
		{
			setPanSetpoint(RobotMap.TOURELLE_PAN_LIMITE_MAXIMUM);
		}
		else
		{
			setPanSetpoint(RobotMap.TOURELLE_PAN_LIMITE_MINIMUM);
		}
			
	}
	
	public void debuginit()
	{
		SmartDashboard.putNumber("TILT P", tourelleTilt.getP());
		SmartDashboard.putNumber("TILT I", tourelleTilt.getI());
		
		SmartDashboard.putNumber("TILT SP", tiltSP);
	}
	
	public void debugPeriodic()
	{
		tourelleTilt.setP(SmartDashboard.getNumber("TILT P", tourelleTilt.getP()));
		tourelleTilt.setI(SmartDashboard.getNumber("TILT I", tourelleTilt.getI()));
		
		setTiltSetpoint(SmartDashboard.getNumber("TILT SP", 0.0));
	}
	

}

