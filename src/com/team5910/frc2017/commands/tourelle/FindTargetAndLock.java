package com.team5910.frc2017.commands.tourelle;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FindTargetAndLock extends Command {

	public FindTargetAndLock() {
		requires(Robot.superstructure.tourelle);
		 
	 }
	 
	 @Override
	protected void initialize() {
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

	

}
