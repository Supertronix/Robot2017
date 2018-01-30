package com.team5910.frc2017.commande.drive;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeConduiteEncodeurZero extends Command {

	public CommandeConduiteEncodeurZero() {
		requires(Robot.drive);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.drive.resetEncoders();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
