package com.team5910.frc2017.robot.Subsystems;

import com.ctre.CANTalon;
import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Indexer extends Subsystem {
	
	VictorSP IndexerDrive;
	VictorSP IndexerShuffleDrive1;
	CANTalon IndexerShuffleDrive2;
    
	Indexer() {
		IndexerDrive = new VictorSP(RobotMap.kIndexerDriveOut);
		IndexerDrive.setInverted(RobotMap.kRevertIndexerDrive);
		
		IndexerShuffleDrive1 = new VictorSP(RobotMap.kIndexerShuffleDrive1Out);
		IndexerShuffleDrive1.setInverted(RobotMap.kRevertIndexerShuffleDrive1);
		
		IndexerShuffleDrive2 = new CANTalon(RobotMap.kIndexerShuffleDrive2Id);
		IndexerShuffleDrive2.setInverted(RobotMap.kRevertIndexerShuffleDrive2);
		
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	public void index()
	{
		IndexerDrive.set(1);
		IndexerShuffleDrive1.set(1);
		IndexerShuffleDrive2.set(1);
	}
	
	public void stop() {
		IndexerDrive.set(0);
		IndexerShuffleDrive1.set(0);
		IndexerShuffleDrive2.set(0);
	}
	
}

