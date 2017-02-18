package com.team5910.frc2017.commands;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbMotorStart extends Command {

	public ClimbMotorStart() {
		requires(Robot.superstructure.climber);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.climber.climb();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
