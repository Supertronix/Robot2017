package com.team5910.frc2017.commands.tourelle;

import com.team5910.frc2017.robot.Robot;
import com.team5910.frc2017.robot.subsystems.Tourelle;

import edu.wpi.first.wpilibj.command.Command;

public class TurretToggleAutoMan extends Command {

	public TurretToggleAutoMan() {
		requires(Robot.superstructure.tourelle);
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.tourelle.toggleAutoMan();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
