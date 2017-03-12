package com.team5910.frc2017.commands.tourelle;

import com.team5910.frc2017.robot.RobotControleur;
import com.team5910.frc2017.robot.subsystems.Tourelle;

import edu.wpi.first.wpilibj.command.Command;

public class TurretToggleAutoMan extends Command {

	public TurretToggleAutoMan() {
		requires(RobotControleur.superstructure.tourelle);
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.superstructure.tourelle.toggleAutoMan();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
