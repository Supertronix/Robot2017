package com.team5910.frc2017.robot.soussysteme;

import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Indexeur extends Subsystem {
	
	VictorSP IndexerDrive;
    
	public Indexeur() {
		IndexerDrive = new VictorSP(RobotMap.INDEXEUR_MOTEUR);
		IndexerDrive.setInverted(RobotMap.INVERSION_INDEXEUR_MOTEUR);		
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	public void index()
	{
		IndexerDrive.set(1);
	}
	
	public void arreter() {
		IndexerDrive.set(0);
	}
	
}

