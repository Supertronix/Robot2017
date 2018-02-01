package com.team5910.frc2017.robot.soussysteme;

import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.interaction.AffichageStation;
import com.team5910.frc2017.robot.soussysteme.materiel.GyroADXRS450;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Roues extends Subsystem implements RobotMap.Roues{
	
	// Pour Encodeur et Gyro
	public class SortiePID implements PIDOutput {

		double distanceSortiePID;
		
		@Override
		public void pidWrite(double sortie) {
			distanceSortiePID = sortie;
		}
		
		public double getPIDOut() {
			return distanceSortiePID;
		}
	}	
	
	/*
    public enum DriveControlState {
        OPEN_LOOP, PATH_FOLLOWING_CONTROL
    }*/
    
    // SP pour Speed Controller
    // http://wpilib.screenstepslive.com/s/4485/m/13809/l/599702-driving-motors-with-speed-controller-objects-victors-talons-and-jaguars 
    VictorSP roueAvantGauche; 
	VictorSP roueArriereGauche; 
	VictorSP roueAvantDroite; 
	VictorSP roueArriereDroite; 
	
	GyroADXRS450 gyro;
	Encoder encodeurRoues;
	
	PIDController pidGyro;
	SortiePID gyroSortiePID;
	
	PIDController pidDistance;
	SortiePID distanceSortiePID;
	
	//private DriveControlState driveControlState;
	
	public Roues() {
		 roueAvantGauche = new VictorSP(ROUE_AVANT_GAUCHE);
		 roueArriereGauche = new VictorSP(ROUE_ARRIERE_GAUCHE);
		 roueAvantDroite = new VictorSP(ROUE_AVANT_DROIT);
		 roueArriereDroite = new VictorSP(ROUE_ARRIERE_DROIT);
		 	
		 roueAvantGauche.setInverted(INVERSION_ROUE_AVANT_GAUCHE); // TRUE
		 roueArriereGauche.setInverted(INVERSION_ROUE_ARRIERE_GAUCHE); // TRUE
		 roueAvantDroite.setInverted(INVERSION_ROUE_AVANT_DROIT);
		 roueArriereDroite.setInverted(INVERSION_ROUE_ARRIERE_DROIT);
			
		 encodeurRoues = new Encoder(ROUE_ENCODEUR_A, ROUE_ENCODEUR_B);
		 encodeurRoues.setReverseDirection(INVERSION_ROUE_ENCODEUR);
		 encodeurRoues.setDistancePerPulse(ENCODEUR_ROUE_DISTANCE_PULSION);
		 encodeurRoues.setPIDSourceType(PIDSourceType.kDisplacement);
		 
		 distanceSortiePID = new SortiePID();
		 pidDistance = new PIDController(DISTANCE_KP, DISTANCE_KI, 0, encodeurRoues, distanceSortiePID);
		 pidDistance.setSetpoint(0);
		 pidDistance.setAbsoluteTolerance(DISTANCE_TOLERANCE);
		 pidDistance.enable();

		 gyro = new GyroADXRS450();
		 gyro.setPIDSourceType(PIDSourceType.kDisplacement);
		 gyroSortiePID = new SortiePID();
		 
		 pidGyro = new PIDController(-GYRO_KP, GYRO_KI_ROTATION, 0, gyro, gyroSortiePID);
		 pidGyro.setSetpoint(0.0f);
		 pidGyro.setAbsoluteTolerance(3);
		 pidGyro.enable();
		 
	 }
	 
	@Override
	protected void initDefaultCommand() {
		
	}

	public void conduire(double vitesseAvantGauche, double vitesseAvantDroite, double vitesseArriereGauche, double vitesseArriereDroite) {
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
		initialiserPID();
	}
	
	public void initialiserGyro() {
		gyro.reset();
	}
	
	public double getAngleSelonGyro() {
		return gyro.getAngle();
	}
	
	public void initialiserEncodeur() {
		encodeurRoues.reset();
	}

	public void initialiserPID() {
		pidGyro.reset();
		pidDistance.reset();
		pidGyro.enable();
		pidDistance.enable();
	}

	public double getDistanceSelonEncodeur() {
		return encodeurRoues.getDistance();
	}

	public void conduireDroitAvecGyro() {
		if (GYRO_INVERSE)
		{
			roueAvantGauche.set(distanceSortiePID.getPIDOut() + gyroSortiePID.getPIDOut());
			roueAvantDroite.set(distanceSortiePID.getPIDOut() - gyroSortiePID.getPIDOut());
			roueArriereGauche.set(distanceSortiePID.getPIDOut() +  gyroSortiePID.getPIDOut());
			roueArriereDroite.set(distanceSortiePID.getPIDOut() - gyroSortiePID.getPIDOut());
		}
		else
		{
			roueAvantGauche.set(distanceSortiePID.getPIDOut() - gyroSortiePID.getPIDOut());
			roueAvantDroite.set(distanceSortiePID.getPIDOut() + gyroSortiePID.getPIDOut());
			roueArriereGauche.set(distanceSortiePID.getPIDOut() -  gyroSortiePID.getPIDOut());
			roueArriereDroite.set(distanceSortiePID.getPIDOut() + gyroSortiePID.getPIDOut());
		}
		SmartDashboard.putNumber("ENCODER VALUE", encodeurRoues.getDistance());
		/*roueAvantGauche.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)  + gyroPIDOut.getPIDOut());
		roueAvantDroite.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)   - gyroPIDOut.getPIDOut());
	    roueArriereGauche.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)   +  gyroPIDOut.getPIDOut());
		roueArriereDroite.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)  - gyroPIDOut.getPIDOut());*/
	}
	
	public void conduireDroit() {
		if (GYRO_INVERSE)
		{
			roueAvantGauche.set(distanceSortiePID.getPIDOut());
			roueAvantDroite.set(distanceSortiePID.getPIDOut());
			roueArriereGauche.set(distanceSortiePID.getPIDOut());
			roueArriereDroite.set(distanceSortiePID.getPIDOut());
		}
		else
		{
			roueAvantGauche.set(distanceSortiePID.getPIDOut());
			roueAvantDroite.set(distanceSortiePID.getPIDOut());
			roueArriereGauche.set(distanceSortiePID.getPIDOut());
			roueArriereDroite.set(distanceSortiePID.getPIDOut());
		}
		/*roueAvantGauche.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)  + gyroPIDOut.getPIDOut());
		roueAvantDroite.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)   - gyroPIDOut.getPIDOut());
	    roueArriereGauche.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)   +  gyroPIDOut.getPIDOut());
		roueArriereDroite.set(0.4 * Utilities.clamp((1.5 - ((pidDistance.getSetpoint() - pidDistance.getError())/pidDistance.getSetpoint())),-1, 1)  - gyroPIDOut.getPIDOut());*/
	}
	
	public void conduireLateralementAvecGyro() {
		roueAvantGauche.set(-distanceSortiePID.getPIDOut() - gyroSortiePID.getPIDOut());
		roueAvantDroite.set(distanceSortiePID.getPIDOut() - gyroSortiePID.getPIDOut());
	    roueArriereGauche.set(distanceSortiePID.getPIDOut() +  gyroSortiePID.getPIDOut());
		roueArriereDroite.set(-distanceSortiePID.getPIDOut() + gyroSortiePID.getPIDOut());
	}
	
	public void tournerAvecGyro() {
		if (GYRO_INVERSE)
		{
			roueAvantGauche.set(-gyroSortiePID.getPIDOut());
			roueAvantDroite.set(gyroSortiePID.getPIDOut());
		    roueArriereGauche.set(-gyroSortiePID.getPIDOut());
			roueArriereDroite.set(gyroSortiePID.getPIDOut());
		}
		else
		{
			roueAvantGauche.set(gyroSortiePID.getPIDOut());
			roueAvantDroite.set(-gyroSortiePID.getPIDOut());
		    roueArriereGauche.set(gyroSortiePID.getPIDOut());
			roueArriereDroite.set(-gyroSortiePID.getPIDOut());
		}
	}
	
	public void programmerDistance(double distance) {
		pidDistance.setSetpoint(distance);	
	}
	
	public boolean estArriveSelonEncodeur()
	{
		return pidDistance.onTarget();
	}	
	
	public void inverserEncodeur() {
		 encodeurRoues.setReverseDirection(!INVERSION_ROUE_ENCODEUR);
	}
	
	public void restaurerEncodeur() {
		 encodeurRoues.setReverseDirection(INVERSION_ROUE_ENCODEUR);
	}
		
	public void programmerCibleAvecGyro(double cible) {
		pidGyro.setSetpoint(cible);	
	}
	
	public boolean estArriveSelonGyro() {
		return pidGyro.onTarget();
	}

	public void setDistancePIDNormal()
	{
		pidDistance.setPID(DISTANCE_KP, DISTANCE_KI, 0);
	}
	
	public void setDistancePIDPrecision()
	{
		pidDistance.setPID(DISTANCE_KP/2, DISTANCE_KI, 0);
	}
	public void setGyroPIDStandard()
	{
		pidGyro.setPID(-GYRO_KP, GYRO_KI, 0);
	}
	
	public void setRotationPIDStandard()
	{
		pidGyro.setPID(GYRO_KP_ROTATION, GYRO_KI_ROTATION, 0);
	}
	
	public void setDistancePID(double P, double I)
	{
		pidDistance.setPID(P, I, 0);
	}
	public void setDistancePID(double P, double I, double D)
	{
		pidDistance.setPID(P, I, D);
	}
	
	public void setRotationPID(double P, double I)
	{
		pidGyro.setPID(P, I, 0);
	}
	public void setRotationPID(double P, double I, double D)
	{
		pidGyro.setPID(P, I, D);
	}
	
	// todo refactor en-dehors de ce modele
	public void afficherDashboard()
	{
		SmartDashboard.putNumber(AffichageStation.DRIVE_ENCODEUR_DISTANCE, encodeurRoues.getDistance());
		SmartDashboard.putNumber(AffichageStation.DRIVE_GYRO, gyro.getAngle());
		SmartDashboard.putNumber(AffichageStation.DRIVE_GYRO_PID, gyroSortiePID.getPIDOut());
		SmartDashboard.putNumber(AffichageStation.DRIVE_DISTANCE_PID, distanceSortiePID.getPIDOut());
	}
}

