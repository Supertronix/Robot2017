package com.team5910.frc2017.robot.subsystems;

import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intaker extends Subsystem {
	
	VictorSP IntakerDrive;
    
	Intaker() {
		IntakerDrive = new VictorSP(RobotMap.INTAKER_MOTEUR);
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	public void intake()
	{
		if (RobotMap.INVERSION_INTAKER_MOTEUR) 
			IntakerDrive.set(-1);
		else
			IntakerDrive.set(1);
	}
	
	public void stop() {
		IntakerDrive.set(0);
	}
	
}

