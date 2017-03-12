package com.team5910.frc2017.commande.lanceur;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeLanceurAccelerer extends Command {
	double changeValue;
	
	public CommandeLanceurAccelerer(double aChangeValue) {
		requires(RobotControleur.robot.lanceur);
		changeValue = aChangeValue;
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.lanceur.incDecSpeed(changeValue);
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
