package com.team5910.frc2017.commands.Drive;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LateralDriveDistance extends Command {

	double mWantedDistance;

	public LateralDriveDistance(double distance) {
		requires(Robot.drive);
		mWantedDistance = distance; // Distance in feet
	}
	

	@Override
	protected void initialize() {
		Robot.drive.resetEncoders();
		Robot.drive.resetGyro();
		Robot.drive.resetPIDS();
		Robot.drive.reverseEncoder();
		Robot.drive.updateDistanceSetpoint(mWantedDistance);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.drive.driveLateralWithGyro();
	}
		
	@Override
	protected boolean isFinished() {
		return Robot.drive.drivePIDDone();
	}
	
	@Override
	protected void end() {
		Robot.drive.stop();
		Robot.drive.undoReverseEncoder();
	}

}
