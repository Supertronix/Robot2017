package com.team5910.frc2017.commands.Drive;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveEncoderReset extends Command {

	public DriveEncoderReset() {
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
