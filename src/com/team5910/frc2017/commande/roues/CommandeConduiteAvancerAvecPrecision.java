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
		Robot.drive.resetEncoders();
		Robot.drive.resetGyro();
		Robot.drive.resetPIDS();
		Robot.drive.setDistancePIDPrecisionValues();
		Robot.drive.programmerDistance(Robot.drive.getEncoderDistance() + mWantedDistance);
		Robot.drive.programmerCibleGyro(0.0);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.drive.driveStraightWithGyro();
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