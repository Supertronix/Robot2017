package com.team5910.frc2017.commands.Shooter;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterMotorSpeedIncDec extends Command {
	double changeValue;
	
	public ShooterMotorSpeedIncDec(double aChangeValue) {
		requires(Robot.superstructure.shooter);
		changeValue = aChangeValue;
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.shooter.incDecSpeed(changeValue);
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
