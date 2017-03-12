package com.team5910.frc2017.robot.soussysteme;

import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.interaction.AffichageStation;
import com.team5910.frc2017.robot.outil.ADXRS450;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Subsystem {
	
    public enum DriveControlState {
        OPEN_LOOP, PATH_FOLLOWING_CONTROL
    }
    
    // SP pour Speed Controller
    // http://wpilib.screenstepslive.com/s/4485/m/13809/l/599702-driving-motors-with-speed-controller-objects-victors-talons-and-jaguars 
    VictorSP roueAvantGauche; // Front left
	VictorSP roueArriereGauche; // Rear Left
	VictorSP roueAvantDroite; // Front right
	VictorSP roueArriereDroite; // Rear right
	
	ADXRS450 gyro;
	Encoder encodeurRoues;
	
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
			
		 encodeurRoues = new Encoder(RobotMap.ROUE_ENCODEUR_A, RobotMap.ROUE_ENCODEUR_B);
		 encodeurRoues.setReverseDirection(RobotMap.INVERSION_ROUE_ENCODEUR);
		 encodeurRoues.setDistancePerPulse(RobotMap.ENCODEUR_ROUE_DISTANCE_PULSION);
		 encodeurRoues.setPIDSourceType(PIDSourceType.kDisplacement);
		 
		 distancePIDOut = new CustomPIDOutput();
		 pidDistance = new PIDController(RobotMap.DISTANCE_KP, RobotMap.DISTANCE_KI, 0, encodeurRoues, distancePIDOut);
		 pidDistance.setSetpoint(0);
		 pidDistance.setAbsoluteTolerance(RobotMap.DISTANCE_TOLERANCE);
		 pidDistance.enable();

		 gyro = new ADXRS450();
		 gyro.setPIDSourceType(PIDSourceType.kDisplacement);
		 gyroPIDOut = new CustomPIDOutput();
		 
		 pidGyro = new PIDController(-RobotMap.GYRO_KP, RobotMap.GYRO_KI_ROTATEONLY, 0, gyro, gyroPIDOut);
		 pidGyro.setSetpoint(0.0f);
		 pidGyro.setAbsoluteTolerance(3);
		 pidGyro.enable();
		 
	 }
	 
	@Override
	protected void initDefaultCommand() {
		
	}

	public void manualDrive(double vitesseAvantGauche, double vitesseAvantDroite, double vitesseArriereGauche, double vitesseArriereDroite) {
		// PWMSpeedController.set 
		// https://team2168.org/javadoc/edu/wpi/first/wpilibj/PWMSpeedController.html
		// The PWM value is set using a range of -1.0 to 1.0, appropriately scaling the value for the FPGA
		roueAvantGauche.set(vitesseAvantGauche);
		roueAvantDroite.set(vitesseAvantDroite);
	    roueArriereGauche.set(vitesseArriereGauche);
		roueArriereDroite.set(vitesseArriereDroite);
	}

	public void arreter() {
		roueAvantGauche.set(0);
		roueAvantDroite.set(0);
	    roueArriereGauche.set(0);
		roueArriereDroite.set(0);
	}
	
	public void zeroSensors() {
		gyro.reset();
		encodeurRoues.reset();
		resetPIDS();
	}
	
	public void resetGyro() {
		gyro.reset();
	}
	
	public double gyroAngle() {
		return gyro.getAngle();
	}
	
	public void resetEncoders() {
		encodeurRoues.reset();
	}

	public void resetPIDS() {
		pidGyro.reset();
		pidDistance.reset();
		pidGyro.enable();
		pidDistance.enable();
	}

	public double getEncoderDistance() {
		return encodeurRoues.getDistance();
	}

	public void driveStraightWithGyro() {
		if (RobotMap.GYRO_UPSIDEDOWN)
		{
			roueAvantGauche.set(distancePIDOut.getPIDOut() + gyroPIDOut.getPIDOut());
			roueAvantDroite.set(distancePIDOut.getPIDOut() - gyroPIDOut.getPIDOut());
			roueArriereGauche.set(distancePIDOut.getPIDOut() +  gyroPIDOut.getPIDOut());
			roueArriereDroite.set(distancePIDOut.getPIDOut() - gyroPIDOut.getPIDOut());
		}
		else
		{
			roueAvantGauche.set(distancePIDOut.getPIDOut() - gyroPIDOut.getPIDOut());
			roueAvantDroite.set(distancePIDOut.getPIDOut() + gyroPIDOut.getPIDOut());
			roueArriereGauche.set(distancePIDOut.getPIDOut() -  gyroPIDOut.getPIDOut());
			roueArriereDroite.set(distancePIDOut.getPIDOut() + gyroPIDOut.getPIDOut());
		}
		
		/*roueAvantGauche.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)  + gyroPIDOut.getPIDOut());
		roueAvantDroite.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)   - gyroPIDOut.getPIDOut());
	    roueArriereGauche.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)   +  gyroPIDOut.getPIDOut());
		roueArriereDroite.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)  - gyroPIDOut.getPIDOut());*/
	}
	
	public void driveStraight() {
		if (RobotMap.GYRO_UPSIDEDOWN)
		{
			roueAvantGauche.set(distancePIDOut.getPIDOut());
			roueAvantDroite.set(distancePIDOut.getPIDOut());
			roueArriereGauche.set(distancePIDOut.getPIDOut());
			roueArriereDroite.set(distancePIDOut.getPIDOut());
		}
		else
		{
			roueAvantGauche.set(distancePIDOut.getPIDOut());
			roueAvantDroite.set(distancePIDOut.getPIDOut());
			roueArriereGauche.set(distancePIDOut.getPIDOut());
			roueArriereDroite.set(distancePIDOut.getPIDOut());
		}
		
		/*roueAvantGauche.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)  + gyroPIDOut.getPIDOut());
		roueAvantDroite.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)   - gyroPIDOut.getPIDOut());
	    roueArriereGauche.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)   +  gyroPIDOut.getPIDOut());
		roueArriereDroite.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)  - gyroPIDOut.getPIDOut());*/
	}
	
	public void driveLateralWithGyro() {
		roueAvantGauche.set(-distancePIDOut.getPIDOut() - gyroPIDOut.getPIDOut());
		roueAvantDroite.set(distancePIDOut.getPIDOut() - gyroPIDOut.getPIDOut());
	    roueArriereGauche.set(distancePIDOut.getPIDOut() +  gyroPIDOut.getPIDOut());
		roueArriereDroite.set(-distancePIDOut.getPIDOut() + gyroPIDOut.getPIDOut());
	}
	
	public void rotateWithGyro() {
		if (RobotMap.GYRO_UPSIDEDOWN)
		{
			roueAvantGauche.set(-gyroPIDOut.getPIDOut());
			roueAvantDroite.set(gyroPIDOut.getPIDOut());
		    roueArriereGauche.set(-gyroPIDOut.getPIDOut());
			roueArriereDroite.set(gyroPIDOut.getPIDOut());
		}
		else
		{
			roueAvantGauche.set(gyroPIDOut.getPIDOut());
			roueAvantDroite.set(-gyroPIDOut.getPIDOut());
		    roueArriereGauche.set(gyroPIDOut.getPIDOut());
			roueArriereDroite.set(-gyroPIDOut.getPIDOut());
		}
	}
	
	public void updateDistanceSetpoint(double setPoint) {
		pidDistance.setSetpoint(setPoint);	
	}
	
	public void updateGyroSetpoint(double setPoint) {
		pidGyro.setSetpoint(setPoint);	
	}
	
	public void reverseEncoder() {
		 encodeurRoues.setReverseDirection(!RobotMap.INVERSION_ROUE_ENCODEUR);
	}
	
	public void undoReverseEncoder() {
		 encodeurRoues.setReverseDirection(RobotMap.INVERSION_ROUE_ENCODEUR);
	}
	
	public boolean drivePIDDone()
	{
		return pidDistance.onTarget();
	}
	
	public boolean gyroPIDDone() {
		return pidGyro.onTarget();
	}
	public void setDistancePIDNormalValues()
	{
		pidDistance.setPID(RobotMap.DISTANCE_KP, RobotMap.DISTANCE_KI, 0);
	}
	
	public void setDistancePIDPrecisionValues()
	{
		pidDistance.setPID(RobotMap.DISTANCE_KP/2, RobotMap.DISTANCE_KI, 0);
	}
	public void setGyroPIDStandardValues()
	{
		pidGyro.setPID(-RobotMap.GYRO_KP, RobotMap.GYRO_KI, 0);
	}
	
	public void setGyroDefaultPIDRotateValues()
	{
		pidGyro.setPID(RobotMap.GYRO_KP_ROTATEONLY, RobotMap.GYRO_KI_ROTATEONLY, 0);
	}
	
	public void setDistancePIDValues(double P, double I)
	{
		pidDistance.setPID(P, I, 0);
	}
	public void setDistancePIDValues(double P, double I, double D)
	{
		pidDistance.setPID(P, I, D);
	}
	
	public void setRotatePIDValues(double P, double I)
	{
		pidGyro.setPID(P, I, 0);
	}
	public void setRotatePIDValues(double P, double I, double D)
	{
		pidGyro.setPID(P, I, D);
	}
	public void updateDashboard()
	{
		SmartDashboard.putNumber(AffichageStation.DRIVE_ENCODEUR_DISTANCE, encodeurRoues.getDistance());
		SmartDashboard.putNumber(AffichageStation.DRIVE_GYRO, gyro.getAngle());
		SmartDashboard.putNumber(AffichageStation.DRIVE_GYRO_PID, gyroPIDOut.getPIDOut());
		SmartDashboard.putNumber(AffichageStation.DRIVE_DISTANCE_PID, distancePIDOut.getPIDOut());
	}
}

