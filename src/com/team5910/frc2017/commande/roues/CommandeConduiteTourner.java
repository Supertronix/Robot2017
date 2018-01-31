package com.team5910.frc2017.commande.roues;

import com.team5910.frc2017.robot.Robot;
import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeConduiteTourner extends Command {

	protected double angleDesire;
	protected double p;
	protected double i;
	protected double d;
	
	public CommandeConduiteTourner(double angle) {
		requires(Robot.drive);
		angleDesire = angle; // Distance in feet
		this.p = RobotMap.GYRO_KP_ROTATION;
		this.i = RobotMap.GYRO_KI_ROTATION;
		this.d = 0.0;
	}
	
	public CommandeConduiteTourner(double angle, double P, double I) {
		requires(Robot.drive);
		angleDesire = angle; // Distance in feet
		this.p = P;
		this.i = I;
		this.d = 0.0;
	}
	
	public CommandeConduiteTourner(double angle, double P, double I, double D) {
		requires(Robot.drive);
		angleDesire = angle; // Distance in feet
		this.p = P;
		this.i = I;
		this.d = D;
	}
	

	@Override
	protected void initialize() {
		Robot.drive.initialiserEncodeur();
		Robot.drive.initialiserGyro();
		Robot.drive.initialiserPID();
		Robot.drive.setRotationPID(this.p, this.i, this.d);
		Robot.drive.programmerCibleAvecGyro(-angleDesire); // Invert angle so positive is clockwise
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.drive.tournerAvecGyro();
	}
		
	@Override
	protected boolean isFinished() {
		return Robot.drive.estArriveSelonGyro();
	}
	
	@Override
	protected void end() {
		Robot.drive.arreter();
	}

}