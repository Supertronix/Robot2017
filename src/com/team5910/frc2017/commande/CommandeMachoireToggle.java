package com.team5910.frc2017.commande;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeMachoireToggle extends Command {

	public CommandeMachoireToggle() {
		requires(RobotControleur.robot.machoire);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.machoire.demanderBascule();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
