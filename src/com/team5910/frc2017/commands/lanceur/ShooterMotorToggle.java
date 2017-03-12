package com.team5910.frc2017.commands.lanceur;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterMotorToggle extends Command {

	public ShooterMotorToggle() {
		requires(RobotControleur.robot.lanceur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.lanceur.toggle();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
