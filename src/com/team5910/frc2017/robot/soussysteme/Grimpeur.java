package com.team5910.frc2017.robot.soussysteme;

import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Grimpeur extends Subsystem implements RobotMap.Grimpeur{
	
	VictorSP moteurGrimpeur;
    
	public Grimpeur() {
		moteurGrimpeur = new VictorSP(GRIMPEUR_MOTEUR);
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	public void grimper()
	{
		if (INVERSION_GRIMPEUR_MOTEUR) 
			moteurGrimpeur.set(-1);
		else
			moteurGrimpeur.set(1);
	}
	
	public void arreter() {
		moteurGrimpeur.set(0);
	}
}

