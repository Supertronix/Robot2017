package com.team5910.frc2017.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.outils.Utilities;

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
	
	public CANTalon TurretPanDrive = new CANTalon(RobotMap.kTurretPanDriveId);
	public CANTalon TurretTiltDrive = new CANTalon(RobotMap.kTurretTiltDriveId);
	
	double panSP = 0.0;
	double tiltSP = 0.0;

	Tourelle() {
		actualState = SystemState.DISABLED;
		
		//TurretPanDrive.changeControlMode(CANTalon.TalonControlMode.Position);
		//TurretPanDrive.changeControlMode(CANTalon.TalonControlMode.
		TurretPanDrive.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		TurretPanDrive.setControlMode(0);
		//TurretPanDrive.enable();
		
		//TurretTiltDrive.changeControlMode(CANTalon.TalonControlMode.Position);
		TurretTiltDrive.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		TurretTiltDrive.setControlMode(0);
		//TurretTiltDrive.enable();		
		
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
		TurretPanDrive.disable();
		TurretTiltDrive.disable();
		actualState =  SystemState.DISABLED;
		
	}
	
	public void manualDrive(double aPanValue, double aTiltValue) {
		TurretPanDrive.set(aPanValue);
		TurretTiltDrive.set(aTiltValue);	
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void updateDashboard()
	{
		SmartDashboard.putNumber("tiltPot", TurretTiltDrive.getAnalogInPosition());
		SmartDashboard.putNumber("panPot", TurretPanDrive.getAnalogInPosition());
	}
}

