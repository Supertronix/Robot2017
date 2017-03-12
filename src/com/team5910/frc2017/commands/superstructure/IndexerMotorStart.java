package com.team5910.frc2017.commands.superstructure;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class IndexerMotorStart extends Command {

	public IndexerMotorStart() {
		requires(RobotControleur.superstructure.indexeur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.superstructure.indexeur.index();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
