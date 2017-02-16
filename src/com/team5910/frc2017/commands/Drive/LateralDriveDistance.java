package com.team5910.frc2017.commands.Drive;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LateralDriveDistance extends Command {

	double mSpeed;
	double mDistance;

	
	public LateralDriveDistance(double speed, double distance) {
		mSpeed = speed;
		mDistance = distance; // Distance in feet
		requires(Robot.drive);
	}
	

	@Override
	protected void initialize() {
		Robot.drive.resetEncoders();
		Robot.drive.resetGyro();
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		SmartDashboard.putNumber("Encoder Distance", Robot.drive.getEncoderDistance());
		Robot.drive.driveWithGyro(mSpeed);
	}
		
	@Override
	protected boolean isFinished() {
		int myDistance = 0;
		return (Math.abs(Robot.drive.getEncoderDistance()) >= Math.abs(myDistance));
	}
	
	@Override
	protected void end() {
		Robot.drive.stop();
	}

}
