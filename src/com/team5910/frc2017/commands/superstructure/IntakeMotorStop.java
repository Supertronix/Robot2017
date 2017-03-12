package com.team5910.frc2017.commands.superstructure;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeMotorStop extends Command {

	public IntakeMotorStop() {
		requires(RobotControleur.robot.intaker);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.intaker.stop();
		 new ShufflerMotorStop();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
