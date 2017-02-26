package com.team5910.frc2017.commands.Superstructure;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbMotorStop extends Command {

	public ClimbMotorStop() {
		requires(Robot.superstructure.grimpeur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.grimpeur.stop();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
