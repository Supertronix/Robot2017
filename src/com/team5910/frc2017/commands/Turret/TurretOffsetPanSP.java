package com.team5910.frc2017.commands.Turret;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurretOffsetPanSP extends Command {

	double panOffset = 0.0;
	public TurretOffsetPanSP(double aPanOffset) {
		requires(Robot.superstructure.turret);
		panOffset = aPanOffset;
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.turret.offsetPanSP(panOffset);
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
