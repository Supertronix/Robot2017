package com.team5910.frc2017.commands.lanceur;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterMotorSpeedIncDec extends Command {
	double changeValue;
	
	public ShooterMotorSpeedIncDec(double aChangeValue) {
		requires(RobotControleur.robot.lanceur);
		changeValue = aChangeValue;
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.lanceur.incDecSpeed(changeValue);
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
