package com.team5910.frc2017.commande;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeBrasseurArreter extends Command {

	public CommandeBrasseurArreter() {
		requires(RobotControleur.robot.brasseur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.brasseur.stop();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
