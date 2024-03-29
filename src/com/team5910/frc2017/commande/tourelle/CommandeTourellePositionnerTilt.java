package com.team5910.frc2017.commande.tourelle;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeTourellePositionnerTilt extends Command {

	double tiltSP = 0.0;
	public CommandeTourellePositionnerTilt(double aTiltSP) {
		requires(RobotControleur.robot.tourelle);
		tiltSP = aTiltSP;
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.tourelle.setTiltPositionMode();
		 RobotControleur.robot.tourelle.setTiltCible(tiltSP);
	}
	 
	@Override
	protected boolean isFinished() {
		return RobotControleur.robot.tourelle.aFiniTiltCible();
	}


}
