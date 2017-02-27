package com.team5910.frc2017.robot.subsystems;

import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Grimpeur extends Subsystem {
	
	VictorSP Climbdrive;
    
	Grimpeur() {
		Climbdrive = new VictorSP(RobotMap.kClimbDriveOut);
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	public void climb()
	{
		if (RobotMap.kRevertClimbDrive) 
			Climbdrive.set(-1);
		else
			Climbdrive.set(1);
	}
	
	public void stop() {
		Climbdrive.set(0);
	}
}

