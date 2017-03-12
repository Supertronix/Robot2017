package com.team5910.frc2017.commande.tourelle;

import com.team5910.frc2017.robot.RobotControleur;
import com.team5910.frc2017.robot.soussysteme.Tourelle;

import edu.wpi.first.wpilibj.command.Command;

public class TurretToggleAutoMan extends Command {

	public TurretToggleAutoMan() {
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
