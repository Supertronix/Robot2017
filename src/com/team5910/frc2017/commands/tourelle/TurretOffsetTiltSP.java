package com.team5910.frc2017.commands.tourelle;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurretOffsetTiltSP extends Command {

	double tiltOffset = 0.0;
	public TurretOffsetTiltSP(double aTiltOffset) {
		requires(Robot.superstructure.tourelle);
		tiltOffset = aTiltOffset;
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.tourelle.offsetTiltSP(tiltOffset);
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
