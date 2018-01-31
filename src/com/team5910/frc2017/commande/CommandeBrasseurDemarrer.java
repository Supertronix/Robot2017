package com.team5910.frc2017.commande;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeBrasseurDemarrer extends Command {

	public CommandeBrasseurDemarrer() {
		requires(RobotControleur.robot.brasseur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.brasseur.brasser();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
