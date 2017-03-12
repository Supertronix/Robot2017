package com.team5910.frc2017.robot.subsystems;

import com.ctre.CANTalon;
import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Brasseur extends Subsystem {
	
	VictorSP IndexerShuffleDrive1;
	CANTalon IndexerShuffleDrive2;
	
	boolean reverseEachTime = true;
	boolean lastClockwise = true;
    
	public Brasseur() {
		IndexerShuffleDrive1 = new VictorSP(RobotMap.BRASSEUR_GAUCHE);
		IndexerShuffleDrive1.setInverted(RobotMap.INVERSION_BRASSEUR_GAUCHE);
		
		IndexerShuffleDrive2 = new CANTalon(RobotMap.BRASSEUR_DROIT);
		IndexerShuffleDrive2.setInverted(RobotMap.INVERSION_BRASSEUR_DROIT);
		
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

