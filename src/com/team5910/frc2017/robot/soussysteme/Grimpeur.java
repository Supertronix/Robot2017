package com.team5910.frc2017.robot.soussysteme;

import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Grimpeur extends Subsystem {
	
	VictorSP moteurGrimpeur;
    
	public Grimpeur() {
		moteurGrimpeur = new VictorSP(RobotMap.GRIMPEUR_MOTEUR);
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	public void climb()
	{
		if (RobotMap.INVERSION_GRIMPEUR_MOTEUR) 
			moteurGrimpeur.set(-1);
		else
			moteurGrimpeur.set(1);
	}
	
	public void stop() {
		moteurGrimpeur.set(0);
	}
}

