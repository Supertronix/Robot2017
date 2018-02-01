package com.team5910.frc2017.commande;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeIntakerArreter extends Command {

	public CommandeIntakerArreter() {
		requires(RobotControleur.robot.intaker);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.intaker.arreter();
		 new CommandeBrasseurArreter();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
