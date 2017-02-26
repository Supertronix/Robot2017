package com.team5910.frc2017.commands.Turret;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurretUpdateTiltSP extends Command {

	double tiltSP = 0.0;
	public TurretUpdateTiltSP(double aTiltSP) {
		requires(Robot.superstructure.tourelle);
		tiltSP = aTiltSP;
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.tourelle.setPanSetpoint(tiltSP);
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}


}
