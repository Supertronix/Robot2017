package com.team5910.frc2017.commands.Turret;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FindTargetAndLock extends Command {

	public FindTargetAndLock() {
		requires(Robot.superstructure.turret);
		 
	 }
	 
	 @Override
	protected void initialize() {
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

	

}
