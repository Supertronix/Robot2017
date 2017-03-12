package com.team5910.frc2017.commande.tourelle;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class FindTargetAndLock extends Command {

	public FindTargetAndLock() {
		requires(RobotControleur.robot.tourelle);
		 
	 }
	 
	 @Override
	protected void initialize() {
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

	

}
