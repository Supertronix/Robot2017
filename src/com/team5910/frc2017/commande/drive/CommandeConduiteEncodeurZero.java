package com.team5910.frc2017.commande.drive;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeConduiteEncodeurZero extends Command {

	public CommandeConduiteEncodeurZero() {
		requires(RobotControleur.drive);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.drive.resetEncoders();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
