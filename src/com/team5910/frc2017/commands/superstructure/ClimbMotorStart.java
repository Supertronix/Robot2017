package com.team5910.frc2017.commands.superstructure;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbMotorStart extends Command {

	public ClimbMotorStart() {
		requires(RobotControleur.robot.grimpeur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.grimpeur.climb();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
