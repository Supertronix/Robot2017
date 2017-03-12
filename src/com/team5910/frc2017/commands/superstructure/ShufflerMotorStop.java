package com.team5910.frc2017.commands.superstructure;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class ShufflerMotorStop extends Command {

	public ShufflerMotorStop() {
		requires(RobotControleur.robot.brasseur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.brasseur.stop();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
