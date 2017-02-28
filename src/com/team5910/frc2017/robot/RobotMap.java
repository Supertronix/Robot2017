package com.team5910.frc2017.robot;

public interface RobotMap {
	
	// PWM Outputs
	public static final int DRIVE_AVANT_GAUCHE = 10; // SP 1
	public static final int DRIVE_ARRIERE_GAUCHE = 14; // SP 2
	public static final int DRIVE_AVANT_DROIT= 13; // SP 3
	public static final int DRIVE_ARRIERE_DROIT = 17; // SP 4
	
	public static final boolean INVERSION_DRIVE_AVANT_GAUCHE = true;
	public static final boolean INVERSION_DRIVE_ARRIERE_GAUCHE = true;
	public static final boolean INVERSION_DRIVE_AVANT_DROIT = false;
	public static final boolean INVERSION_DRIVE_ARRIERE_DROIT= false;
	
	public static final int kClimbDriveOut = 12; // SP 5
	public static final boolean kRevertClimbDrive = false;
	
	public static final int kIntakerDriveOut = 11; // SP 6
	public static final boolean kRevertIntakeDrive = false;
	
	public static final int kIndexerDriveOut = 15; // SP 7
	public static final boolean kRevertIndexerDrive = true;
	
	public static final int kIndexerShuffleLeftDriveOut = 16; // SP 8
	public static final boolean kRevertIndexerShuffleLeftDrive = false;
	
	public static final int MACHOIRE_GAUCHE_SERVO = 1; // Servo 1
	public static final int MACHOIRE_DROITE_SERVO = 0; // Servo 2
	
	public static final double MACHOIRE_GAUCHE_FERMEE = 0.0;
	public static final double MACHOIRE_DROITE_FERMEE = 0.0;
	public static final double MACHOIRE_GAUCHE_OUVERTE = 1.0;
	public static final double MACHOIRE_DROITE_OUVERTE = 1.0;
	
	// CAN Bus
	public static final int kShooterDriveMainId = 3;
	public static final int ShooterDriveSlaveId = 0;
	public static final int TOURELLE_PAN_DRIVE = 2;
	public static final int kTurretTiltDriveId = 1;
	
	public static final int kIndexerShuffleRightDriveId = 4;
	public static final boolean kRevertIndexerShuffleRightDrive = true;
	
	// Encoders
	//public static final int kFLWheelEncoderA = 16;
	//public static final int kFLWheelEncoderB = 17;
	
	public static final int ROUE_ENCODEUR_A = 0;
	public static final int ROUE_ENCODEUR_B = 1;
	
	public static final boolean INVERSION_ROUE_ENCODEUR = true;
	public static final float ENCODEUR_ROUE_DISTANCE_PULSION = 0.0085f;
	
	/*public static final int kRLWheelEncoderA = 12;
	public static final int kRLWheelEncoderB = 13;
	
	public static final int kFRWheelEncoderA = 14;
	public static final int kFRWheelEncoderB = 15;*/
	
	
	// Joystick MAP
	// Main 
	public static final int MANETTE_CONDUITE_PRINCIPALE = 0;
	public static final int CONDUITE_X_GAUCHE = 0;
	public static final int CONDUITE_Y_GAUCHE = 1;
	public static final int CONDUITE_X_DROITE = 4;
	public static final int CONDUITE_Y_DROITE = 5;
	
	public static final boolean INVERSION_CONDUITE_X_GAUCHE = false;
	public static final boolean INVERSION_CONDUITE_Y_GAUCHE = true;
	public static final boolean INVERSION_CONDUITE_X_DROITE = false;
	public static final boolean INVERSION_CONDUITE_Y_DROITE = true;
	
	
	// Accessories
	public static final int MANETTE_ACCESSOIRE = 1;
	
	public static final int BOUTON_GRIMPEUR = 6;
	public static final int BOUTON_INTAKE = 5;
	public static final int BOUTON_MACHOIRE = 10;
	public static final int BOUTON_INDEXEUR = 1;
	
	public static final int TOURELLE_PAN_AXE = 2;
	public static final boolean INVERSION_TOURELLE_PAN_AXE = false;	
	public static final int TOURELLE_TILT_AXE = 1;
	public static final boolean INVERSION_TOURELLE_TILT_AXE = false;
	public static final int TOURELLE_TOGGLE_AUTO_MANUEL = 5;
	
	public static final int BOUTON_LANCEUR = 2;	
	public static final int BOUTON_LANCEUR_VITESSE_DECREMENTE = 11;
	public static final int BOUTON_LANCEUR_VITESSE_INCREMENTE= 12;
	
	public static final double LANCEUR_VITESSE_DELTA = 0.1;
	
	// Experimentations
	public static final int BOUTON_GYRO_RESET = 1;
	
	// Turret Settings
	public static final double kPanLowSPLimit = 0;
	public static final double kPanHighSPLimit = 1024;
	
	public static final double kTiltLowSPLimit = 0;
	public static final double kTiltHighSPLimit = 1024;
	
	// DRIVE AUTO PID
	public static final double GYRO_KP = 0.03;
	public static final double GYRO_KI = 0.0;
	
	public static final double GYRO_KP_AGRESSIF = 0.0005; // + haut = plus aggressif
	public static final double GYRO_KI_AGRESSIF = 0.000085; // + bas = plus aggressif
	
	public static final double DISTANCE_KP = 0.11;
	public static final double DISTANCE_KI = 0.00045;
	public static final float DISTANCE_TOLERANCE = 0.083f;
	
	public static final int RASPBERRY_PORT = 3620;
	public static final int RAYON_VERT_DIO = 12;


}

