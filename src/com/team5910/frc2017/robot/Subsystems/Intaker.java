package com.team5910.frc2017.robot.Subsystems;

import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intaker extends Subsystem {
	
	VictorSP IntakerDrive;
    
	Intaker() {
		IntakerDrive = new VictorSP(RobotMap.kIntakerDriveOut);
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	public void intake()
	{
		if (RobotMap.kRevertIntakeDrive) 
			IntakerDrive.set(-1);
		else
			IntakerDrive.set(1);
	}
	
	public void stop() {
		IntakerDrive.set(0);
	}
	
}

