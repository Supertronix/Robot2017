package com.team5910.frc2017.robot.soussysteme;

import com.ctre.CANTalon;
import com.team5910.frc2017.commande.tourelle.CommandeTourelleChercherCible;
import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.interaction.AffichageStation;
import com.team5910.frc2017.robot.outil.Calculateur;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Tourelle extends Subsystem {
	
	public enum SystemState {
        DISABLED,
		MANUAL_CONTROL, // The turret is manually controlled
        AUTO_SCAN, // The turret is trying to find the target
        AUTO_LOCK, // The turret is locked to target and listening raspberry pi to follow
    }
	
	private SystemState actualState;
	private CommandeTourelleChercherCible commandeChercherCible;
	
	public void setState(SystemState aWantedState)
	{
		actualState = aWantedState;
		if (aWantedState == SystemState.AUTO_SCAN)
		{
			commandeChercherCible = new CommandeTourelleChercherCible();
			commandeChercherCible.start();
		}
		else
		{
			commandeChercherCible.cancel();
			setPanSetpoint(tourellePan.getPosition());
		}
	}
	
	public CANTalon tourellePan = new CANTalon(RobotMap.TOURELLE_PAN_MOTEUR);
	public CANTalon tourelleTilt = new CANTalon(RobotMap.TOURELLE_TILT_MOTEUR);
	
	double panSP = -500;
	double tiltSP = 0.0;
	
	double autoSPupdate = 0.0;

	public Tourelle() {
		actualState = SystemState.MANUAL_CONTROL;
		
		tourellePan.changeControlMode(CANTalon.TalonControlMode.Position);
		tourellePan.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		tourellePan.setPID(RobotMap.PAN_KP, RobotMap.PAN_KI, 0);
		
		//tourellePan.setControlMode(0);
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
		tourelleTilt.setPosition(0);
		tourelleTilt.setForwardSoftLimit(RobotMap.TOURELLE_TILT_LIMITE_MAXIMUM);
		tourelleTilt.enableForwardSoftLimit(true);
		
		tourelleTilt.setReverseSoftLimit(RobotMap.TOURELLE_TILT_LIMITE_MINIMUM);
		tourelleTilt.enableReverseSoftLimit(true);
		tourelleTilt.reverseSensor(true);
		tourelleTilt.setP(0.1);
		tourelleTilt.enable();	
		SmartDashboard.putNumber(AffichageStation.TOURELLE_TILT_TSP, 130);
		
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
		 //Utilities.clamp(tiltSP, RobotMap.kTiltLowSPLimit, RobotMap.kTiltHighSPLimit);
		 //TurretTiltDrive.set(tiltSP);
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

	public void stop() {
		tourellePan.disable();
		tourelleTilt.disable();
		actualState =  SystemState.DISABLED;
		
	}
	
	public void manualDrive(double aPanValue, double aTiltValue) {
		if (actualState == SystemState.DISABLED)
			 return;
		else if (actualState == SystemState.MANUAL_CONTROL)
			offsetPanSP(aPanValue);
			//tourelleTilt.set(aTiltValue);	
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void tourellePeriodic()
	{
		if (actualState == SystemState.DISABLED)
			 return;
		else if (actualState == SystemState.AUTO_SCAN)
		{
			//tourellePan.set(panSP);
		}
	}
	
	public void gripUpdatePan(double aTargetX)
	{
		if (actualState == SystemState.AUTO_LOCK)
		{
			offsetPanSP(aTargetX);
		}
			
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
		SmartDashboard.putNumber("PAN P", tourellePan.getP());
		SmartDashboard.putNumber("PAN I", tourellePan.getI());
		
		SmartDashboard.putNumber("PAN SP", 0.0);
	}
	
	public void debugPeriodic()
	{
		tourellePan.setP(SmartDashboard.getNumber("PAN P", tourellePan.getP()));
		tourellePan.setI(SmartDashboard.getNumber("PAN I", tourellePan.getI()));
		
		tourellePan.set(SmartDashboard.getNumber("PAN SP", 0.0));
	}
	

}

