package com.team5910.frc2017.commande.lanceur;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeLanceurArreter extends Command {

	public CommandeLanceurArreter() {
		requires(RobotControleur.robot.lanceur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.lanceur.arreter();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
