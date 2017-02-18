package com.team5910.frc2017.commands;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class EncoderReset extends Command {

	public EncoderReset() {
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
