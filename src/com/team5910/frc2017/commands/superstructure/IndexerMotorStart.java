package com.team5910.frc2017.commands.superstructure;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IndexerMotorStart extends Command {

	public IndexerMotorStart() {
		requires(Robot.superstructure.indexeur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.indexeur.index();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
