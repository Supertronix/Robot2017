package com.team5910.frc2017.commande.tourelle;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeTourellePositionnerPan extends Command {

	double panSP = 0.0;
	public CommandeTourellePositionnerPan(double aPanSP) {
		requires(RobotControleur.robot.tourelle);
		panSP = aPanSP;
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.tourelle.setPanSetpoint(panSP);
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
