package com.team5910.frc2017.commands.superstructure;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeMotorStart extends Command {

	public IntakeMotorStart() {
		requires(Robot.superstructure.intaker);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.intaker.intake();
		 new ShufflerMotorStart();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
