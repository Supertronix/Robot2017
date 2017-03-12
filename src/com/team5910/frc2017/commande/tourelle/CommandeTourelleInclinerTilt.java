package com.team5910.frc2017.commande.tourelle;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeTourelleInclinerTilt extends Command {

	double tiltOffset = 0.0;
	public CommandeTourelleInclinerTilt(double aTiltOffset) {
		requires(RobotControleur.robot.tourelle);
		tiltOffset = aTiltOffset;
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.tourelle.offsetTiltSP(tiltOffset);
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
