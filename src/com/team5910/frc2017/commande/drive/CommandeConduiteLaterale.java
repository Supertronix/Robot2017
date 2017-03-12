package com.team5910.frc2017.commande.drive;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeConduiteLaterale extends Command {

	double mWantedDistance;

	public CommandeConduiteLaterale(double distance) {
		requires(RobotControleur.drive);
		mWantedDistance = distance; // Distance in feet
	}
	

	@Override
	protected void initialize() {
		RobotControleur.drive.resetEncoders();
		RobotControleur.drive.resetGyro();
		RobotControleur.drive.resetPIDS();
		RobotControleur.drive.reverseEncoder();
		RobotControleur.drive.updateDistanceSetpoint(mWantedDistance);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		RobotControleur.drive.driveLateralWithGyro();
	}
		
	@Override
	protected boolean isFinished() {
		return RobotControleur.drive.drivePIDDone();
	}
	
	@Override
	protected void end() {
		RobotControleur.drive.arreter();
		RobotControleur.drive.undoReverseEncoder();
	}

}
