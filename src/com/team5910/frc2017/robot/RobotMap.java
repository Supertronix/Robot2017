package com.team5910.frc2017.robot;

public class RobotMap {
	
	// PWM Outputs
	public static final int kFLDriveOut = 0;
	public static final int kRLDriveOut = 1;	
	public static final int kFRDriveOut = 2;	
	public static final int kRRDriveOut = 3;
	
	public static final int kClimbDriveOut = 4;	
	public static final boolean kRevertClimbDrive = false;
	
	public static final int kIntakerDriveOut = 5;
	public static final boolean kRevertIntakeDrive = true;
	
	public static final int kLeftClampServoId = 8; // Servo 1
	public static final int kRightClampServoId = 9; // Servo 2
	
	public static final double kLeftClampClosedPos = 0.0;
	public static final double kRightClampClosedPos = 0.0;
	public static final double kLeftClampOpenPos = 1.0;
	public static final double kRightClampOpenPos = 1.0;
	
	// CAN Bus
	public static final int kShooterDriveMainId = 0;
	public static final int ShooterDriveSlaveId = 1;
	public static final int kTurretPanDriveId = 4;
	public static final int kTurretTiltDriveId = 5;
	
	// Joystick MAP
	// Main 
	public static final int kMainDriveStickId = 0;
	public static final int kLeftDriveXAxis = 0;
	public static final int kLeftDriveYAxis = 1;
	public static final int kRightDriveXAxis = 4;
	public static final int kRightDriveYAxis = 5;
	
	public static final boolean kRevertLeftDriveXAxis = false;
	public static final boolean kRevertLeftDriveYAxis = true;
	public static final boolean kRevertRightDriveXAxis = false;
	public static final boolean kRevertRightDriveYAxis = true;
	
	
	// Accessories
	public static final int mAccessoriesStickId = 1;
	
	public static final int kClimberButton = 6;
	public static final int kIntakeButton = 5;
	public static final int kClampButton = 10;

}

