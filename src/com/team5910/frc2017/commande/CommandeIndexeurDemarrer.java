package com.team5910.frc2017.commande;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeIndexeurDemarrer extends Command {

	public CommandeIndexeurDemarrer() {
		requires(RobotControleur.robot.indexeur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.indexeur.index();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
