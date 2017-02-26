package com.team5910.frc2017.commands.Turret;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurretUpdatePanSP extends Command {

	double panSP = 0.0;
	public TurretUpdatePanSP(double aPanSP) {
		requires(Robot.superstructure.tourelle);
		panSP = aPanSP;
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.tourelle.setPanSetpoint(panSP);
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
