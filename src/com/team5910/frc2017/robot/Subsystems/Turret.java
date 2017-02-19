package com.team5910.frc2017.robot.Subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;


public class Turret extends Subsystem {
	
	CANTalon TurretPanDrive = new CANTalon(RobotMap.kTurretPanDriveId);
	CANTalon TurretTiltDrive = new CANTalon(RobotMap.kTurretTiltDriveId);
	
	double turretWantedRot = 508;

	Turret() {
		
		//TurretPanDrive.changeControlMode(CANTalon.TalonControlMode.Position);
		//TurretPanDrive.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		//TurretPanDrive.setControlMode(0);
		//TurretPanDrive.enable();
		
		//TurretTiltDrive.changeControlMode(CANTalon.TalonControlMode.Position);
		//TurretTiltDrive.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		//TurretTiltDrive.setControlMode(0);
		//TurretTiltDrive.enable();		
		
	}

    public void initDefaultCommand() {
    }

	public void stop() {
		TurretPanDrive.set(0);
		TurretTiltDrive.set(0);
		
	}
}

