package com.team5910.frc2017.commands.tourelle;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class TurretUpdatePanSP extends Command {

	double panSP = 0.0;
	public TurretUpdatePanSP(double aPanSP) {
		requires(RobotControleur.superstructure.tourelle);
		panSP = aPanSP;
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.superstructure.tourelle.setPanSetpoint(panSP);
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
