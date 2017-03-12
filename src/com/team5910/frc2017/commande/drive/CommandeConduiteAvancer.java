package com.team5910.frc2017.commande.drive;

import com.team5910.frc2017.robot.RobotControleur;
import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandeConduiteAvancer extends Command {

	protected double distanceDesiree;
	protected double p;
	protected double i;
	protected double d;
	
	public CommandeConduiteAvancer(double distance) {
		requires(RobotControleur.drive);
		distanceDesiree = distance; // Distance in feet
		this.p = RobotMap.DISTANCE_KP;
		this.i = RobotMap.DISTANCE_KI;
		this.d = 0.0;
	}
	public CommandeConduiteAvancer(double distance, double driveP, double driveI) {
		requires(RobotControleur.drive);
		distanceDesiree = distance; // Distance in feet
		this.p = driveP;
		this.i = driveI;
		this.d = 0.0;
	}
	public CommandeConduiteAvancer(double distance, double driveP, double driveI, double driveD) {
		requires(RobotControleur.drive);
		distanceDesiree = distance; // Distance in feet
		this.p = driveP;
		this.i = driveI;
		this.d = driveD;
	}
	

	@Override
	protected void initialize() {
		RobotControleur.drive.resetEncoders();
		RobotControleur.drive.resetGyro();
		RobotControleur.drive.resetPIDS();
		RobotControleur.drive.setGyroPIDStandardValues();
		RobotControleur.drive.setDistancePIDValues(this.p, this.i, this.d);
		RobotControleur.drive.updateDistanceSetpoint(RobotControleur.drive.getEncoderDistance() + distanceDesiree);
		System.out.println("Valeur encodeur" + RobotControleur.drive.getEncoderDistance());
		RobotControleur.drive.updateGyroSetpoint(0.0);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		//Robot.drive.driveStraightWithGyro();
		RobotControleur.drive.driveStraight(); // Test
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