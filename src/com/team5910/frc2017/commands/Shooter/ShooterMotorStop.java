package com.team5910.frc2017.commands.Shooter;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterMotorStop extends Command {

	public ShooterMotorStop() {
		requires(Robot.superstructure.shooter);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.shooter.stop();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}