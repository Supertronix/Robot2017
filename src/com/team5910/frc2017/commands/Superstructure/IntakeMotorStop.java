package com.team5910.frc2017.commands.Superstructure;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeMotorStop extends Command {

	public IntakeMotorStop() {
		requires(Robot.superstructure.intaker);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.intaker.stop();
		 new ShufflerMotorStop();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
