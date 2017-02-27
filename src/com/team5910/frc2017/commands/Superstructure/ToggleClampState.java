package com.team5910.frc2017.commands.superstructure;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleClampState extends Command {

	public ToggleClampState() {
		requires(Robot.superstructure.machoire);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.machoire.toggleAsked();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
