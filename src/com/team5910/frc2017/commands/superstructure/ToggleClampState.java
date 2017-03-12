package com.team5910.frc2017.commands.superstructure;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleClampState extends Command {

	public ToggleClampState() {
		requires(RobotControleur.robot.machoire);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.machoire.toggleAsked();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
