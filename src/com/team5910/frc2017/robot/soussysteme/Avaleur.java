package com.team5910.frc2017.robot.soussysteme;

import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Avaleur extends Subsystem implements RobotMap.Avaleur{
	
	VictorSP avaleurBalles;
    
	public Avaleur() {
		avaleurBalles = new VictorSP(AVALEUR_MOTEUR);
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	public void avaler()
	{
		if (INVERSION_AVALEUR_MOTEUR) 
			avaleurBalles.set(-1);
		else
			avaleurBalles.set(1);
	}
	
	public void arreter() {
		avaleurBalles.set(0);
	}
	
}

