package com.team5910.frc2017.commands.tourelle;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class TurretOffsetPanSP extends Command {

	double panOffset = 0.0;
	public TurretOffsetPanSP(double aPanOffset) {
		requires(RobotControleur.superstructure.tourelle);
		panOffset = aPanOffset;
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.superstructure.tourelle.offsetPanSP(panOffset);
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
