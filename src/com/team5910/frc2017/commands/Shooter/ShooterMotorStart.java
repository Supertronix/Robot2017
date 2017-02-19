package com.team5910.frc2017.commands.Shooter;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterMotorStart extends Command {

	public ShooterMotorStart() {
		requires(Robot.superstructure.shooter);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.shooter.shoot();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
