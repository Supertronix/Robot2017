package com.team5910.frc2017.commands.superstructure;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleClampState extends Command {

	public ToggleClampState() {
		requires(RobotControleur.superstructure.machoire);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.superstructure.machoire.toggleAsked();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
