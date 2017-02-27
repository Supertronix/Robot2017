package com.team5910.frc2017.commands.superstructurerenamedouble;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IndexerMotorStart extends Command {

	public IndexerMotorStart() {
		requires(Robot.superstructure.indexer);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.indexer.index();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
