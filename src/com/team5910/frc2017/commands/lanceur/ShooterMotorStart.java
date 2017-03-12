package com.team5910.frc2017.commands.lanceur;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterMotorStart extends Command {

	public ShooterMotorStart() {
		requires(RobotControleur.superstructure.lanceur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.superstructure.lanceur.shoot();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
