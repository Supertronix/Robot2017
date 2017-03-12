package com.team5910.frc2017.commande.drive;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandeConduiteAvancerAvecPrecision extends Command {

	double mWantedDistance;
	
	public CommandeConduiteAvancerAvecPrecision(double distance) {
		requires(RobotControleur.drive);
		mWantedDistance = distance; // Distance in feet
	}
	

	@Override
	protected void initialize() {
		RobotControleur.drive.resetEncoders();
		RobotControleur.drive.resetGyro();
		RobotControleur.drive.resetPIDS();
		RobotControleur.drive.setDistancePIDPrecisionValues();
		RobotControleur.drive.updateDistanceSetpoint(RobotControleur.drive.getEncoderDistance() + mWantedDistance);
		RobotControleur.drive.updateGyroSetpoint(0.0);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		RobotControleur.drive.driveStraightWithGyro();
	}
		
	@Override
	protected boolean isFinished() {
		return RobotControleur.drive.drivePIDDone();
	}
	
	@Override
	protected void end() {
		RobotControleur.drive.arreter();
	}

}