package com.team5910.frc2017.commands.Superstructure;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleClampState extends Command {

	public ToggleClampState() {
		requires(Robot.superstructure.clamp);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.clamp.toggleAsked();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
