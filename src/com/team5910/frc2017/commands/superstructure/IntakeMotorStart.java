package com.team5910.frc2017.commands.superstructure;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeMotorStart extends Command {

	public IntakeMotorStart() {
		requires(RobotControleur.superstructure.intaker);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.superstructure.intaker.intake();
		 new ShufflerMotorStart();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
