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
		requires(Robot.roues);
		distanceDesiree = distance; // Distance en pied
		this.p = RobotMap.DISTANCE_KP;
		this.i = RobotMap.DISTANCE_KI;
		this.d = 0.0;
	}
	public CommandeConduiteAvancer(double distance, double driveP, double driveI) {
		requires(Robot.roues);
		distanceDesiree = distance; // Distance en pied
		this.p = driveP;
		this.i = driveI;
		this.d = 0.0;
	}
	public CommandeConduiteAvancer(double distance, double driveP, double driveI, double driveD) {
		requires(Robot.roues);
		distanceDesiree = distance; // Distance en pied
		this.p = driveP;
		this.i = driveI;
		this.d = driveD;
	}
	

	@Override
	protected void initialize() {
		Robot.roues.initialiserEncodeur();
		Robot.roues.initialiserGyro();
		Robot.roues.initialiserPID();
		Robot.roues.setGyroPIDStandard();
		Robot.roues.setDistancePID(this.p, this.i, this.d);
		Robot.roues.programmerDistance(Robot.roues.getDistanceSelonEncodeur() + distanceDesiree);
		System.out.println("Valeur encodeur" + Robot.roues.getDistanceSelonEncodeur());
		Robot.roues.programmerCibleAvecGyro(0.0);
		startTime = Utility.getFPGATime();
	}
	
	@Override
	protected void execute() {
		Robot.roues.conduireDroitAvecGyro();
	}
		
	@Override
	protected boolean isFinished() {
		return (Robot.roues.estArriveSelonEncodeur() || (Utility.getFPGATime() >= startTime + RobotMap.AUTO_EXPIRE * 1000000));
	}
	
	@Override
	protected void end() {
		Robot.roues.arreter();
	}
}