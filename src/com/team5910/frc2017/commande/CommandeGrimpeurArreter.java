package com.team5910.frc2017.commande;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeGrimpeurArreter extends Command {

	public CommandeGrimpeurArreter() {
		requires(RobotControleur.robot.grimpeur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.grimpeur.arreter();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
