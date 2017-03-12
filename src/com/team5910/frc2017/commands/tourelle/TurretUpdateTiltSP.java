package com.team5910.frc2017.commands.tourelle;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class TurretUpdateTiltSP extends Command {

	double tiltSP = 0.0;
	public TurretUpdateTiltSP(double aTiltSP) {
		requires(RobotControleur.superstructure.tourelle);
		tiltSP = aTiltSP;
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.superstructure.tourelle.setPanSetpoint(tiltSP);
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}


}
