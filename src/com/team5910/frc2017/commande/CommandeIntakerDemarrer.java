package com.team5910.frc2017.commande;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeIntakerDemarrer extends Command {

	public CommandeIntakerDemarrer() {
		requires(RobotControleur.robot.intaker);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.intaker.avaler();
		 new CommandeBrasseurDemarrer();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
