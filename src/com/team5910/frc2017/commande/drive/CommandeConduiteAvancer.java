package com.team5910.frc2017.commande.drive;

import com.team5910.frc2017.robot.Robot;
import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeConduiteAvancer extends Command {

	protected double distanceDesiree;
	protected double p;
	protected double i;
	protected double d;
	
	public CommandeConduiteAvancer(double distance) {
		requires(Robot.drive);
		distanceDesiree = distance; // Distance in feet
		this.p = RobotMap.DISTANCE_KP;
		this.i = RobotMap.DISTANCE_KI;
		this.d = 0.0;
	}
	public CommandeConduiteAvancer(double distance, double driveP, double driveI) {
		requires(Robot.drive);
		distanceDesiree = distance; // Distance in feet
		this.p = driveP;
		this.i = driveI;
		this.d = 0.0;
	}
	public CommandeConduiteAvancer(double distance, double driveP, double driveI, double driveD) {
		requires(Robot.drive);
		distanceDesiree = distance; // Distance in feet
		this.p = driveP;
		this.i = driveI;
		this.d = driveD;
	}
	

	@Override
	protected void initialize() {
		Robot.drive.resetEncoders();
		Robot.drive.resetGyro();
		Robot.drive.resetPIDS();
		Robot.drive.setGyroPIDStandardValues();
		Robot.drive.setDistancePIDValues(this.p, this.i, this.d);
		Robot.drive.updateDistanceSetpoint(Robot.drive.getEncoderDistance() + distanceDesiree);
		System.out.println("Valeur encodeur" + Robot.drive.getEncoderDistance());
		Robot.drive.updateGyroSetpoint(0.0);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.drive.driveStraightWithGyro();
		//Robot.drive.driveStraight(); // Test
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