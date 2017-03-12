package com.team5910.frc2017.commands.superstructure;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class IndexerMotorStop extends Command {

	public IndexerMotorStop() {
		requires(RobotControleur.robot.indexeur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.indexeur.stop();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
