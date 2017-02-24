package com.team5910.frc2017.robot.Subsystems;

import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.Utils.ADXRS450_Supertronix;
import com.team5910.frc2017.robot.Utils.Utilities;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Subsystem {
	
    public enum DriveControlState {
        OPEN_LOOP, PATH_FOLLOWING_CONTROL
    }
    
    VictorSP roueAvantGauche; // Front left
	VictorSP roueArriereGauche; // Rear Left
	VictorSP roueAvantDroite; // Front right
	VictorSP roueArriereDroite; // Rear right
	
	ADXRS450_Supertronix gyro;
	Encoder RRWheelEncoder;
	
	PIDController pidGyro;
	CustomPIDOutput gyroPIDOut;
	
	PIDController pidDistance;
	CustomPIDOutput distancePIDOut;
	
	private DriveControlState driveControlState_;
	
	public Drive() {
		 roueAvantGauche = new VictorSP(RobotMap.DRIVE_AVANT_GAUCHE);
		 roueArriereGauche = new VictorSP(RobotMap.DRIVE_ARRIERE_GAUCHE);
		 roueAvantDroite = new VictorSP(RobotMap.DRIVE_AVANT_DROIT);
		 roueArriereDroite = new VictorSP(RobotMap.DRIVE_ARRIERE_DROIT);
		 	
		 roueAvantGauche.setInverted(RobotMap.INVERSION_DRIVE_AVANT_GAUCHE); // TRUE
		 roueArriereGauche.setInverted(RobotMap.INVERSION_DRIVE_ARRIERE_GAUCHE); // TRUE
		 roueAvantDroite.setInverted(RobotMap.INVERSION_DRIVE_AVANT_DROIT);
		 roueArriereDroite.setInverted(RobotMap.INVERSION_DRIVE_ARRIERE_DROIT);
			
		 RRWheelEncoder = new Encoder(RobotMap.kRRWheelEncoderA, RobotMap.kRRWheelEncoderB);
		 RRWheelEncoder.setReverseDirection(RobotMap.kRevertRRWheelEncoder);
		 RRWheelEncoder.setDistancePerPulse(0.0085);
		 RRWheelEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		 distancePIDOut = new CustomPIDOutput();
		 pidDistance = new PIDController(RobotMap.kDistanceKp, RobotMap.kDistanceKi, 0, RRWheelEncoder, distancePIDOut);
		 pidDistance.setSetpoint(0);
		 pidDistance.setAbsoluteTolerance(0.083);
		 pidDistance.enable();

		 gyro = new ADXRS450_Supertronix();
		 gyro.setPIDSourceType(PIDSourceType.kDisplacement);
		 gyroPIDOut = new CustomPIDOutput();
		 
		 pidGyro = new PIDController(-RobotMap.kGyroKp, RobotMap.kGyroFullRotateKi, 0, gyro, gyroPIDOut);
		 pidGyro.setSetpoint(0.0f);
		 pidGyro.setAbsoluteTolerance(3);
		 pidGyro.enable();
		 
	 }
	 
	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void manualDrive(double FLDriveSP, double FRDriveSP, double RLDriveSP, double RRDriveSP) {
		roueAvantGauche.set(FLDriveSP);
		roueAvantDroite.set(FRDriveSP);
	    roueArriereGauche.set(RLDriveSP);
		roueArriereDroite.set(RRDriveSP);		
	}

	public void arreter() {
		roueAvantGauche.set(0);
		roueAvantDroite.set(0);
	    roueArriereGauche.set(0);
		roueArriereDroite.set(0);
	}
	
	public void zeroSensors() {
		gyro.reset();
		RRWheelEncoder.reset();
	}
	
	public void resetGyro() {
		gyro.reset();
	}
	
	public double gyroAngle() {
		return gyro.getAngle();
	}
	
	public void resetEncoders() {
		RRWheelEncoder.reset();
	}

	public void resetPIDS() {
		pidGyro.reset();
		pidDistance.reset();
		pidGyro.enable();
		pidDistance.enable();
	}

	public double getEncoderDistance() {
		return RRWheelEncoder.getDistance();
	}

	public void driveStraightWithGyro() {
		/*FLDrive.set(distancePIDOut.getPIDOut() + gyroPIDOut.getPIDOut());
		FRDrive.set(distancePIDOut.getPIDOut() - gyroPIDOut.getPIDOut());
	    RLDrive.set(distancePIDOut.getPIDOut() +  gyroPIDOut.getPIDOut());
		RRDrive.set(distancePIDOut.getPIDOut() - gyroPIDOut.getPIDOut());*/
		
		roueAvantGauche.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)  + gyroPIDOut.getPIDOut());
		roueAvantDroite.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)   - gyroPIDOut.getPIDOut());
	    roueArriereGauche.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)   +  gyroPIDOut.getPIDOut());
		roueArriereDroite.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)  - gyroPIDOut.getPIDOut());
	}
	
	public void driveLateralWithGyro() {
		roueAvantGauche.set(-distancePIDOut.getPIDOut() - gyroPIDOut.getPIDOut());
		roueAvantDroite.set(distancePIDOut.getPIDOut() - gyroPIDOut.getPIDOut());
	    roueArriereGauche.set(distancePIDOut.getPIDOut() +  gyroPIDOut.getPIDOut());
		roueArriereDroite.set(-distancePIDOut.getPIDOut() + gyroPIDOut.getPIDOut());
	}
	
	public void rotateWithGyro() {
		roueAvantGauche.set(-gyroPIDOut.getPIDOut());
		roueAvantDroite.set(gyroPIDOut.getPIDOut());
	    roueArriereGauche.set(-gyroPIDOut.getPIDOut());
		roueArriereDroite.set(gyroPIDOut.getPIDOut());
	}
	
	public void updateDistanceSetpoint(double setPoint) {
		pidDistance.setSetpoint(setPoint);	
	}
	
	public void updateGyroSetpoint(double setPoint) {
		pidGyro.setSetpoint(setPoint);	
	}
	
	public void reverseEncoder() {
		 RRWheelEncoder.setReverseDirection(!RobotMap.kRevertRRWheelEncoder);
	}
	
	public void undoReverseEncoder() {
		 RRWheelEncoder.setReverseDirection(RobotMap.kRevertRRWheelEncoder);
	}
	
	public boolean drivePIDDone()
	{
		return pidDistance.onTarget();
	}
	
	public boolean gyroPIDDone() {
		return pidGyro.onTarget();
	}
	
	public void setGyroPIDStandardValues()
	{
		pidGyro.setPID(-RobotMap.kGyroKp, RobotMap.kGyroKi, 0);
	}
	
	public void setGyroPIDRotateValues()
	{
		pidGyro.setPID(RobotMap.kGyroFullRotateKp, RobotMap.kGyroFullRotateKi, 0);
	}
	
	public void updateDashboard()
	{
		SmartDashboard.putNumber("Encoder distance", RRWheelEncoder.getDistance());
		SmartDashboard.putNumber("Gyro", gyro.getAngle());
		
		SmartDashboard.putNumber("Gyro PID", gyroPIDOut.getPIDOut());
		SmartDashboard.putNumber("Distance PID", distancePIDOut.getPIDOut());
	}
}

