package com.team5910.frc2017.commands.superstructure;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class ShufflerMotorStart extends Command {

	public ShufflerMotorStart() {
		requires(RobotControleur.superstructure.brasseur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.superstructure.brasseur.shuffle();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
