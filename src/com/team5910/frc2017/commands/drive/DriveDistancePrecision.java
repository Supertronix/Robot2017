package com.team5910.frc2017.commands.drive;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveDistancePrecision extends Command {

	double mWantedDistance;
	
	public DriveDistancePrecision(double distance) {
		requires(Robot.drive);
		mWantedDistance = distance; // Distance in feet
	}
	

	@Override
	protected void initialize() {
		Robot.drive.resetEncoders();
		Robot.drive.resetGyro();
		Robot.drive.resetPIDS();
		Robot.drive.setDistancePIDPrecisionValues();
		Robot.drive.updateDistanceSetpoint(Robot.drive.getEncoderDistance() + mWantedDistance);
		Robot.drive.updateGyroSetpoint(0.0);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.drive.driveStraightWithGyro();
	}
		
	@Override
	protected boolean isFinished() {
		return Robot.drive.drivePIDDone();
	}
	
	@Override
	protected void end() {
		Robot.drive.arreter();
	}

}