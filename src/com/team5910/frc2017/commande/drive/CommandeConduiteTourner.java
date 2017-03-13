package com.team5910.frc2017.commande.drive;

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
		this.p = RobotMap.GYRO_KP_ROTATEONLY;
		this.i = RobotMap.GYRO_KI_ROTATEONLY;
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
		Robot.drive.resetEncoders();
		Robot.drive.resetGyro();
		Robot.drive.resetPIDS();
		Robot.drive.setRotatePIDValues(this.p, this.i, this.d);
		Robot.drive.updateGyroSetpoint(-angleDesire); // Invert angle so positive is clockwise
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