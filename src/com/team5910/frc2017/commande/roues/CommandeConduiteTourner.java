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
		requires(Robot.roues);
		angleDesire = angle; // Distance in feet
		this.p = RobotMap.Roues.GYRO_KP_ROTATION;
		this.i = RobotMap.Roues.GYRO_KI_ROTATION;
		this.d = 0.0;
	}
	
	public CommandeConduiteTourner(double angle, double P, double I) {
		requires(Robot.roues);
		angleDesire = angle; // Distance in feet
		this.p = P;
		this.i = I;
		this.d = 0.0;
	}
	
	public CommandeConduiteTourner(double angle, double P, double I, double D) {
		requires(Robot.roues);
		angleDesire = angle; // Distance in feet
		this.p = P;
		this.i = I;
		this.d = D;
	}
	

	@Override
	protected void initialize() {
		Robot.roues.initialiserEncodeur();
		Robot.roues.initialiserGyro();
		Robot.roues.initialiserPID();
		Robot.roues.setRotationPID(this.p, this.i, this.d);
		Robot.roues.programmerCibleAvecGyro(-angleDesire); // Invert angle so positive is clockwise
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.roues.tournerAvecGyro();
	}
		
	@Override
	protected boolean isFinished() {
		return Robot.roues.estArriveSelonGyro();
	}
	
	@Override
	protected void end() {
		Robot.roues.arreter();
	}

}