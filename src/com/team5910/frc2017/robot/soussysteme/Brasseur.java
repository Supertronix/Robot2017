package com.team5910.frc2017.robot.soussysteme;

import com.ctre.CANTalon;
import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Brasseur extends Subsystem {
	
	VictorSP moteurBrasseurGauche;
	CANTalon moteurBrasseurDroit;
	
	boolean inversionSens = false;
	boolean sensHoraire = true;
    
	public Brasseur() {
		moteurBrasseurGauche = new VictorSP(RobotMap.BRASSEUR_GAUCHE);
		moteurBrasseurGauche.setInverted(RobotMap.INVERSION_BRASSEUR_GAUCHE);
		
		moteurBrasseurDroit = new CANTalon(RobotMap.BRASSEUR_DROIT);
		moteurBrasseurDroit.setInverted(RobotMap.INVERSION_BRASSEUR_DROIT);
		
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	public void shuffle()
	{
		if (inversionSens)
		{
			if (sensHoraire)
			{
				moteurBrasseurGauche.set(-1);
				moteurBrasseurDroit.set(-1);
				sensHoraire = false;
			}
			else
			{
				moteurBrasseurGauche.set(1);
				moteurBrasseurDroit.set(1);
				sensHoraire = true;
			}
		}
		else
		{
			moteurBrasseurGauche.set(1);
			moteurBrasseurDroit.set(1);
		}
	}
	
	public void stop() {
		moteurBrasseurGauche.set(0);
		moteurBrasseurDroit.set(0);
	}
	
}

