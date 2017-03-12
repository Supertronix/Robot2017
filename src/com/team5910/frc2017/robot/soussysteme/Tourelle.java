package com.team5910.frc2017.robot.soussysteme;

import com.ctre.CANTalon;
import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.interaction.AffichageStation;

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
	
	public void setState(SystemState aWantedState)
	{
		actualState = aWantedState;
	}
	
	public CANTalon tourellePan = new CANTalon(RobotMap.TOURELLE_PAN_MOTEUR);
	public CANTalon tourelleTilt = new CANTalon(RobotMap.TOURELLE_TILT_MOTEUR);
	
	double panSP = 0.0;
	double tiltSP = 0.0;
	
	double autoSPupdate = 0.0;

	public Tourelle() {
		actualState = SystemState.DISABLED;
		
		//TurretPanDrive.changeControlMode(CANTalon.TalonControlMode.Position);
		//tourellePan.changeControlMode(CANTalon.TalonControlMode.Position);
		tourellePan.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		tourellePan.setControlMode(0);
		tourellePan.setPosition(0);
		tourellePan.enable();
		
		//tourelleTilt.changeControlMode(CANTalon.TalonControlMode.Position);
		tourelleTilt.setControlMode(0);
		tourelleTilt.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		tourelleTilt.setPosition(0);
		tourelleTilt.setForwardSoftLimit(-30);
		tourelleTilt.enableForwardSoftLimit(true);
		
		tourelleTilt.setReverseSoftLimit(-170);
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
		 //Utilities.clamp(panSP, RobotMap.kPanLowSPLimit, RobotMap.kPanHighSPLimit);
		// TurretPanDrive.set(panSP);
		 
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
		//Utilities.clamp(panSP, RobotMap.kPanLowSPLimit, RobotMap.kPanHighSPLimit);
		// TurretPanDrive.set(panSP);
		 
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
				 actualState = SystemState.MANUAL_CONTROL;
				 break;
			 case MANUAL_CONTROL:
				 actualState = SystemState.AUTO_SCAN;
				 break; 
			 case AUTO_SCAN:
				 actualState = SystemState.MANUAL_CONTROL;
				 break; 
			 case AUTO_LOCK:
				 actualState = SystemState.MANUAL_CONTROL;
				 break; 
		 }
	    }

	public void stop() {
		tourellePan.disable();
		tourelleTilt.disable();
		actualState =  SystemState.DISABLED;
		
	}
	
	public void manualDrive(double aPanValue, double aTiltValue) {
		tourellePan.set(aPanValue);
		tourelleTilt.set(aTiltValue);	
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void updateDashboard()
	{
		SmartDashboard.putNumber(AffichageStation.TOURELLE_TILT_POT, tourelleTilt.getAnalogInPosition());
		SmartDashboard.putNumber(AffichageStation.TOURELLE_PAN_POT, tourellePan.getAnalogInPosition());
	}
	
	public void gripUpdatePan(double aSPUpdateValue)
	{
		if (actualState == SystemState.AUTO_LOCK)
		{
			autoSPupdate = aSPUpdateValue;
		}
			
	}
	
	public void TeleopPeriodic()
	{
		//tourelleTilt.set(SmartDashboard.getNumber(AffichageStation.TOURELLE_TILT_TSP, 130));
	}
}

