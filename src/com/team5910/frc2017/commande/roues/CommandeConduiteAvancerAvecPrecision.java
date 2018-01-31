package com.team5910.frc2017.commande.roues;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeConduiteAvancerAvecPrecision extends Command {

	double mWantedDistance;
	
	public CommandeConduiteAvancerAvecPrecision(double distance) {
		requires(Robot.drive);
		mWantedDistance = distance; // Distance in feet
	}
	

	@Override
	protected void initialize() {
		Robot.drive.initialiserEncodeur();
		Robot.drive.initialiserGyro();
		Robot.drive.initialiserPID();
		Robot.drive.setDistancePIDPrecision();
		Robot.drive.programmerDistance(Robot.drive.getDistanceSelonEncodeur() + mWantedDistance);
		Robot.drive.programmerCibleGyro(0.0);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.drive.conduireDroitAvecGyro();
	}
		
	@Override
	protected boolean isFinished() {
		return Robot.drive.estArriveSelonEncodeur();
	}
	
	@Override
	protected void end() {
		Robot.drive.arreter();
	}

}