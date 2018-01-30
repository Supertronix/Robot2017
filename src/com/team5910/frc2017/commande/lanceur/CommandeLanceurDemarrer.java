package com.team5910.frc2017.commande.lanceur;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeLanceurDemarrer extends Command {

	public CommandeLanceurDemarrer() {
		requires(RobotControleur.robot.lanceur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.lanceur.shoot();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
