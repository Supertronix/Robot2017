package com.team5910.frc2017.commande;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeGrimpeurDemarrer extends Command {

	public CommandeGrimpeurDemarrer() {
		requires(RobotControleur.robot.grimpeur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.grimpeur.grimper();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
