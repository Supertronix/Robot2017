package com.team5910.frc2017.commands.superstructure;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbMotorStop extends Command {

	public ClimbMotorStop() {
		requires(RobotControleur.superstructure.grimpeur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.superstructure.grimpeur.stop();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
