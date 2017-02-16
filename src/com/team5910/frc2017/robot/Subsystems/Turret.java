package com.team5910.frc2017.robot.Subsystems;

import com.ctre.CANTalon;
import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;


public class Turret extends Subsystem {
	
	CANTalon ShooterDriveMain = new CANTalon(RobotMap.kShooterDriveMainId);
	CANTalon ShooterDriveSlave = new CANTalon(RobotMap.ShooterDriveSlaveId);
	
	CANTalon TurretPanDrive = new CANTalon(RobotMap.kTurretPanDriveId);
	CANTalon TurretTiltDrive = new CANTalon(RobotMap.kTurretTiltDriveId);
	
	double turretWantedRot = 508;
	
	Turret() {
	
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }

	public void stop() {
		// TODO Auto-generated method stub
		
	}
}

