package com.team5910.frc2017.commands.drive;

import com.team5910.frc2017.robot.RobotControleur;
import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandeConduiteTourner extends Command {

	protected double angleDesire;
	protected double p;
	protected double i;
	protected double d;
	
	public CommandeConduiteTourner(double angle) {
		requires(RobotControleur.drive);
		angleDesire = angle; // Distance in feet
		this.p = RobotMap.GYRO_KP_ROTATEONLY;
		this.i = RobotMap.GYRO_KI_ROTATEONLY;
		this.d = 0.0;
	}
	
	public CommandeConduiteTourner(double angle, double P, double I) {
		requires(RobotControleur.drive);
		angleDesire = angle; // Distance in feet
		this.p = P;
		this.i = I;
		this.d = 0.0;
	}
	
	public CommandeConduiteTourner(double angle, double P, double I, double D) {
		requires(RobotControleur.drive);
		angleDesire = angle; // Distance in feet
		this.p = P;
		this.i = I;
		this.d = D;
	}
	

	@Override
	protected void initialize() {
		RobotControleur.drive.resetEncoders();
		RobotControleur.drive.resetGyro();
		RobotControleur.drive.resetPIDS();
		RobotControleur.drive.setRotatePIDValues(this.p, this.i, this.d);
		RobotControleur.drive.updateGyroSetpoint(-angleDesire); // Invert angle so positive is clockwise
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		RobotControleur.drive.rotateWithGyro();
	}
		
	@Override
	protected boolean isFinished() {
		return RobotControleur.drive.gyroPIDDone();
	}
	
	@Override
	protected void end() {
		RobotControleur.drive.arreter();
	}

}