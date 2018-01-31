package com.team5910.frc2017.commande.roues;

import com.team5910.frc2017.robot.Robot;
import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;

public class CommandeConduiteAvancer extends Command {

	protected double distanceDesiree;
	protected double p;
	protected double i;
	protected double d;
	private double startTime;
	
	public CommandeConduiteAvancer(double distance) {
		requires(Robot.drive);
		distanceDesiree = distance; // Distance en pied
		this.p = RobotMap.DISTANCE_KP;
		this.i = RobotMap.DISTANCE_KI;
		this.d = 0.0;
	}
	public CommandeConduiteAvancer(double distance, double driveP, double driveI) {
		requires(Robot.drive);
		distanceDesiree = distance; // Distance en pied
		this.p = driveP;
		this.i = driveI;
		this.d = 0.0;
	}
	public CommandeConduiteAvancer(double distance, double driveP, double driveI, double driveD) {
		requires(Robot.drive);
		distanceDesiree = distance; // Distance en pied
		this.p = driveP;
		this.i = driveI;
		this.d = driveD;
	}
	

	@Override
	protected void initialize() {
		Robot.drive.resetEncoders();
		Robot.drive.resetGyro();
		Robot.drive.resetPIDS();
		Robot.drive.setGyroPIDStandard();
		Robot.drive.setDistancePID(this.p, this.i, this.d);
		Robot.drive.programmerDistance(Robot.drive.getEncoderDistance() + distanceDesiree);
		System.out.println("Valeur encodeur" + Robot.drive.getEncoderDistance());
		Robot.drive.programmerCibleGyro(0.0);
		startTime = Utility.getFPGATime();
	}
	
	@Override
	protected void execute() {
		Robot.drive.driveStraightWithGyro();
	}
		
	@Override
	protected boolean isFinished() {
		return (Robot.drive.estArriveSelonEncodeur() || (Utility.getFPGATime() >= startTime + RobotMap.AUTO_EXPIRE * 1000000));
	}
	
	@Override
	protected void end() {
		Robot.drive.arreter();
	}
}