package com.team5910.frc2017.commands;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeMotorStop extends Command {

	public IntakeMotorStop() {
		requires(Robot.superstructure.intaker);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.intaker.stop();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
