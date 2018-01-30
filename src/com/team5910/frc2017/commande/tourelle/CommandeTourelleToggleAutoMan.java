package com.team5910.frc2017.commande.tourelle;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeTourelleToggleAutoMan extends Command {

	public CommandeTourelleToggleAutoMan() {
		requires(RobotControleur.robot.tourelle);
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.tourelle.toggleAutoMan();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
