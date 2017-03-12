package com.team5910.frc2017.commands.superstructure;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeMotorStop extends Command {

	public IntakeMotorStop() {
		requires(RobotControleur.superstructure.intaker);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.superstructure.intaker.stop();
		 new ShufflerMotorStop();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
