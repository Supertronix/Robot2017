package com.team5910.frc2017.commands.drive;

import com.team5910.frc2017.robot.Robot;
import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandeConduiteDistance extends Command {

	double mWantedDistance;
	double mP;
	double mI;
	double mD;
	
	public CommandeConduiteDistance(double distance) {
		requires(Robot.drive);
		mWantedDistance = distance; // Distance in feet
		mP = RobotMap.DISTANCE_KP;
		mI = RobotMap.DISTANCE_KI;
		mD = 0.0;
	}
	public CommandeConduiteDistance(double distance, double driveP, double driveI) {
		requires(Robot.drive);
		mWantedDistance = distance; // Distance in feet
		mP = driveP;
		mI = driveI;
		mD = 0.0;
	}
	public CommandeConduiteDistance(double distance, double driveP, double driveI, double driveD) {
		requires(Robot.drive);
		mWantedDistance = distance; // Distance in feet
		mP = driveP;
		mI = driveI;
		mD = driveD;
	}
	

	@Override
	protected void initialize() {
		Robot.drive.resetEncoders();
		Robot.drive.resetGyro();
		Robot.drive.resetPIDS();
		Robot.drive.setGyroPIDStandardValues();
		Robot.drive.setDistancePIDValues(mP, mI, mD);
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