package com.team5910.frc2017.commands.superstructure;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShufflerMotorStop extends Command {

	public ShufflerMotorStop() {
		requires(Robot.superstructure.brasseur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.brasseur.stop();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
