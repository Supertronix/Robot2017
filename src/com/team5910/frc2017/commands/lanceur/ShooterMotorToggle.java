package com.team5910.frc2017.commands.lanceur;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterMotorToggle extends Command {

	public ShooterMotorToggle() {
		requires(Robot.superstructure.lanceur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.lanceur.toggle();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
