package com.team5910.frc2017.commands.lanceur;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterMotorStop extends Command {

	public ShooterMotorStop() {
		requires(Robot.superstructure.lanceur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.lanceur.stop();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
