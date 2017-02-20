package com.team5910.frc2017.commands.Superstructure;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShufflerMotorStop extends Command {

	public ShufflerMotorStop() {
		requires(Robot.superstructure.shuffler);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.shuffler.stop();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
