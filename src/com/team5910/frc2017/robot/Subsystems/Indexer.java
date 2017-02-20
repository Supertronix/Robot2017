package com.team5910.frc2017.robot.Subsystems;

import com.ctre.CANTalon;
import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Indexer extends Subsystem {
	
	VictorSP IndexerDrive;
    
	Indexer() {
		IndexerDrive = new VictorSP(RobotMap.kIndexerDriveOut);
		IndexerDrive.setInverted(RobotMap.kRevertIndexerDrive);		
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	public void index()
	{
		IndexerDrive.set(1);
	}
	
	public void stop() {
		IndexerDrive.set(0);
	}
	
}

