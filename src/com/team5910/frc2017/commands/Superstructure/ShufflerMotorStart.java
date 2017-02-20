package com.team5910.frc2017.commands.Superstructure;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShufflerMotorStart extends Command {

	public ShufflerMotorStart() {
		requires(Robot.superstructure.shuffler);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.shuffler.shuffle();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
