package com.team5910.frc2017.robot.Subsystems;

import com.ctre.CANTalon;
import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shuffler extends Subsystem {
	
	VictorSP IndexerShuffleDrive1;
	CANTalon IndexerShuffleDrive2;
	
	boolean reverseEachTime = true;
	boolean lastClockwise = true;
    
	Shuffler() {
		IndexerShuffleDrive1 = new VictorSP(RobotMap.kIndexerShuffleLeftDriveOut);
		IndexerShuffleDrive1.setInverted(RobotMap.kRevertIndexerShuffleLeftDrive);
		
		IndexerShuffleDrive2 = new CANTalon(RobotMap.kIndexerShuffleRightDriveId);
		IndexerShuffleDrive2.setInverted(RobotMap.kRevertIndexerShuffleRightDrive);
		
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	public void shuffle()
	{
		if (reverseEachTime)
		{
			if (lastClockwise)
			{
				IndexerShuffleDrive1.set(-1);
				IndexerShuffleDrive2.set(-1);
				lastClockwise = false;
			}
			else
			{
				IndexerShuffleDrive1.set(1);
				IndexerShuffleDrive2.set(1);
				lastClockwise = true;
			}
		}
		else
		{
			IndexerShuffleDrive1.set(1);
			IndexerShuffleDrive2.set(1);
		}
	}
	
	public void stop() {
		IndexerShuffleDrive1.set(0);
		IndexerShuffleDrive2.set(0);
	}
	
}

