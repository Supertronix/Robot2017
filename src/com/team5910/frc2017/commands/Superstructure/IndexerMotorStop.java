package com.team5910.frc2017.commands.Superstructure;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IndexerMotorStop extends Command {

	public IndexerMotorStop() {
		requires(Robot.superstructure.indexer);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.indexer.stop();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
