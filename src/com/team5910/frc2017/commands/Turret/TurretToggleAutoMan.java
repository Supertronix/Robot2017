package com.team5910.frc2017.commands.Turret;

import com.team5910.frc2017.robot.Robot;
import com.team5910.frc2017.robot.Subsystems.Turret;

import edu.wpi.first.wpilibj.command.Command;

public class TurretToggleAutoMan extends Command {

	public TurretToggleAutoMan() {
		requires(Robot.superstructure.turret);
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.turret.toggleAutoMan();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
