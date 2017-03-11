package com.team5910.frc2017.commands.drive;

import com.team5910.frc2017.robot.Robot;
import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandeConduiteTourner extends Command {

	double mWantedAngle;
	double mP;
	double mI;
	double mD;
	
	public CommandeConduiteTourner(double angle) {
		requires(Robot.drive);
		mWantedAngle = angle; // Distance in feet
		mP = RobotMap.GYRO_KP_ROTATEONLY;
		mI = RobotMap.GYRO_KI_ROTATEONLY;
		mD = 0.0;
	}
	
	public CommandeConduiteTourner(double angle, double P, double I) {
		requires(Robot.drive);
		mWantedAngle = angle; // Distance in feet
		mP = P;
		mI = I;
		mD = 0.0;
	}
	
	public CommandeConduiteTourner(double angle, double P, double I, double D) {
		requires(Robot.drive);
		mWantedAngle = angle; // Distance in feet
		mP = P;
		mI = I;
		mD = D;
	}
	

	@Override
	protected void initialize() {
		Robot.drive.resetEncoders();
		Robot.drive.resetGyro();
		Robot.drive.resetPIDS();
		Robot.drive.setRotatePIDValues(mP, mI, mD);
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
		Robot.drive.arreter();
	}

}