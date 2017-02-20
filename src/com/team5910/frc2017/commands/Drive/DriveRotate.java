package com.team5910.frc2017.commands.Drive;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveRotate extends Command {

	double mWantedAngle;
	
	public DriveRotate(double angle) {
		requires(Robot.drive);
		mWantedAngle = angle; // Distance in feet
	}
	

	@Override
	protected void initialize() {
		Robot.drive.resetEncoders();
		Robot.drive.resetGyro();
		Robot.drive.resetPIDS();
		Robot.drive.setGyroPIDRotateValues();
		Robot.drive.updateGyroSetpoint(-mWantedAngle); // Invert angle so positive is clockwise
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.drive.rotateWithGyro();
	}
		
	@Override
	protected boolean isFinished() {
		return Robot.drive.gyroPIDDone();
	}
	
	@Override
	protected void end() {
		Robot.drive.stop();
	}

}