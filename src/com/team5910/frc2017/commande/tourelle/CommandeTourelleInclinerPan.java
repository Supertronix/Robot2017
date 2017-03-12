package com.team5910.frc2017.commande.tourelle;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeTourelleInclinerPan extends Command {

	double panOffset = 0.0;
	public CommandeTourelleInclinerPan(double aPanOffset) {
		requires(RobotControleur.robot.tourelle);
		panOffset = aPanOffset;
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.tourelle.offsetPanSP(panOffset);
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
