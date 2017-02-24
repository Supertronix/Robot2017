package com.team5910.frc2017.robot;

public interface RobotMap {
	
	// PWM Outputs
	public static final int DRIVE_AVANT_GAUCHE = 10; // SP 1
	public static final int DRIVE_ARRIERE_GAUCHE = 14; // SP 2
	public static final int DRIVE_AVANT_DROIT= 13; // SP 3
	public static final int DRIVE_ARRIERE_DROIT = 17; // SP 4
	
	public static final boolean kRevertFLDrive = true;
	public static final boolean kRevertRLDrive = true;
	public static final boolean kRevertFRDrive = false;
	public static final boolean kRevertRRDrive = false;
	
	public static final int kClimbDriveOut = 12; // SP 5
	public static final boolean kRevertClimbDrive = false;
	
	public static final int kIntakerDriveOut = 11; // SP 6
	public static final boolean kRevertIntakeDrive = false;
	
	public static final int kIndexerDriveOut = 15; // SP 7
	public static final boolean kRevertIndexerDrive = true;
	
	public static final int kIndexerShuffleLeftDriveOut = 16; // SP 8
	public static final boolean kRevertIndexerShuffleLeftDrive = false;
	
	public static final int kLeftClampServoId = 1; // Servo 1
	public static final int kRightClampServoId = 0; // Servo 2
	
	public static final double kLeftClampClosedPos = 0.0;
	public static final double kRightClampClosedPos = 0.0;
	public static final double kLeftClampOpenPos = 1.0;
	public static final double kRightClampOpenPos = 1.0;
	
	// CAN Bus
	public static final int kShooterDriveMainId = 3;
	public static final int ShooterDriveSlaveId = 0;
	public static final int kTurretPanDriveId = 2;
	public static final int kTurretTiltDriveId = 1;
	
	public static final int kIndexerShuffleRightDriveId = 4;
	public static final boolean kRevertIndexerShuffleRightDrive = true;
	
	// Encoders
	//public static final int kFLWheelEncoderA = 16;
	//public static final int kFLWheelEncoderB = 17;
	
	public static final int kRRWheelEncoderA = 0;
	public static final int kRRWheelEncoderB = 1;
	
	public static final boolean kRevertRRWheelEncoder = true;
	
	/*public static final int kRLWheelEncoderA = 12;
	public static final int kRLWheelEncoderB = 13;
	
	public static final int kFRWheelEncoderA = 14;
	public static final int kFRWheelEncoderB = 15;*/
	
	
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
	
	public static final int kIndexerButton = 1;
	public static final int kTurretPanAxis = 2;
	public static final boolean kRevertTurretPanAxis = false;
	
	public static final int kTurretTiltAxis = 1;
	public static final boolean kRevertTurretTiltAxis = false;
	public static final int kShootButton = 2;
	
	public static final int kTurretToggleAutoMan = 5;
	
	public static final int kShooterSpeedDecButton = 11;
	public static final int kShooterSpeedIncButton = 12;
	
	public static final double kShooterSpeedIncDecValue = 0.1;
	
	// Experimentations
	public static final int kGyroResetButton = 1;
	
	// Turret Settings
	public static final double kPanLowSPLimit = 0;
	public static final double kPanHighSPLimit = 1024;
	
	public static final double kTiltLowSPLimit = 0;
	public static final double kTiltHighSPLimit = 1024;
	
	// DRIVE AUTO PID
	public static final double kGyroKp = 0.03;
	public static final double kGyroKi = 0.0;
	
	public static final double kGyroFullRotateKp = 0.0005; // + haut = plus aggressif
	public static final double kGyroFullRotateKi = 0.000085; // + bas = plus aggressif
	
	public static final double kDistanceKp = 0.11;
	public static final double kDistanceKi = 0.00045;


}

