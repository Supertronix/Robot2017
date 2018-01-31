package com.team5910.frc2017.robot;

public interface RobotMap {
	
	public interface Roues
	{
		// PWM Outputs
		public static final int ROUE_AVANT_GAUCHE = 10; // SP 1
		public static final int ROUE_ARRIERE_GAUCHE = 14; // SP 2
		public static final int ROUE_AVANT_DROIT= 13; // SP 3
		public static final int ROUE_ARRIERE_DROIT = 17; // SP 4
		
		public static final boolean INVERSION_ROUE_AVANT_GAUCHE = true;
		public static final boolean INVERSION_ROUE_ARRIERE_GAUCHE = true;
		public static final boolean INVERSION_ROUE_AVANT_DROIT = false;
		public static final boolean INVERSION_ROUE_ARRIERE_DROIT= false;		
		
		// Encoders
		//public static final int kFLWheelEncoderA = 16;
		//public static final int kFLWheelEncoderB = 17;
		
		public static final int ROUE_ENCODEUR_A = 0;  // bleu
		public static final int ROUE_ENCODEUR_B = 1;  // jaune
		
		public static final boolean INVERSION_ROUE_ENCODEUR = true;
		public static final float ENCODEUR_ROUE_DISTANCE_PULSION = 0.0085f;
		
		/*public static final int kRLWheelEncoderA = 12;
		public static final int kRLWheelEncoderB = 13;
		
		public static final int kFRWheelEncoderA = 14;
		public static final int kFRWheelEncoderB = 15;*/
		
		// Kp = Proportional gain
		// Ki = Integral gain
		// Kd = Derivative gain
		public static final double DISTANCE_KP = 0.18; //0.11;
		public static final double DISTANCE_KI = 0.00045; //0.00045;
		public static final float DISTANCE_TOLERANCE = 0.083f;
		
		public static final double GYRO_KP = 0.02; //0.03
		public static final double GYRO_KI = 0.0;
		
		public static final double GYRO_KP_ROTATION = 0.00075; // + haut = plus aggressif
		public static final double GYRO_KI_ROTATION = 0.000085; // + bas = plus aggressif
		public static final boolean GYRO_INVERSE = true;		
	}
	
	public static final int AVALEUR_MOTEUR = 11; // SP 6
	public static final boolean INVERSION_AVALEUR_MOTEUR = false;
	
	public static final int INDEXEUR_MOTEUR = 15; // SP 7
	public static final boolean INVERSION_INDEXEUR_MOTEUR = true;
	
	public static final int BRASSEUR_GAUCHE = 16; // SP 8
	public static final boolean INVERSION_BRASSEUR_GAUCHE = false;
	
	public static final int MACHOIRE_GAUCHE_SERVO = 1; // Servo 1
	public static final int MACHOIRE_DROITE_SERVO = 0; // Servo 2
	
	public static final double MACHOIRE_GAUCHE_FERMEE = 0.0;
	public static final double MACHOIRE_DROITE_FERMEE = 0.0;
	public static final double MACHOIRE_GAUCHE_OUVERTE = 1.0;
	public static final double MACHOIRE_DROITE_OUVERTE = 1.0;
	
	// CAN Bus
	public static final int LANCEUR_MOTEUR_PRINCIPAL = 3;
	public static final int LANCEUR_MOTEUR_ESCLAVE = 0;
	//public static final int TOURELLE_PAN_MOTEUR = 2;
	//public static final int TOURELLE_TILT_MOTEUR = 5;
	public static final int BRASSEUR_DROIT = 4;
	public static final boolean INVERSION_BRASSEUR_DROIT = false;
		

	
	public interface Manette
	{
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
		public static final boolean INVERSION_CONDUITE_Y_DROITE = true;		// Accessories
		public static final int MANETTE_ACCESSOIRE = 1;
		
		public static final int BOUTON_GRIMPEUR = 6;
		public static final int BOUTON_AVALEUR = 5;
		public static final int BOUTON_MACHOIRE = 10;
		public static final int BOUTON_INDEXEUR = 1;
		public static final int BOUTON_GYRO_RESET = 1;
		
		public static final int BOUTON_LANCEUR = 2;	
		public static final int BOUTON_LANCEUR_VITESSE_DECREMENTE = 7;
		public static final int BOUTON_LANCEUR_VITESSE_INCREMENTE= 8;
		public static final int BOUTON_TOURELLE_TOGGLE_AUTO_MANUEL = 11;
		
	}
	
	public static final int GRIMPEUR_MOTEUR = 12; // SP 5
	public static final boolean INVERSION_GRIMPEUR_MOTEUR = false;	
	
	public interface Tourelle
	{

		public static final int TOURELLE_PAN_AXE = 2;
		public static final boolean INVERSION_TOURELLE_PAN_AXE = false;	
		public static final int TOURELLE_TILT_AXE = 1;
		public static final boolean INVERSION_TOURELLE_TILT_AXE = false;
		
		// Turret Settings
		public static final double TOURELLE_PAN_LIMITE_MINIMUM = -995;
		public static final double TOURELLE_PAN_LIMITE_MAXIMUM = -30;
		public static final double TOURELLE_TILT_LIMITE_MINIMUM = -680;
		public static final double TOURELLE_TILT_LIMITE_MAXIMUM = -450;
		
		public static final double TOURELLE_PAN_DEFAUT = -500;
		public static final double TOURELLE_TILT_DEFAUT = -720;
		
		public static final double PAN_KP = 50;
		public static final double PAN_KI = 0.0008;
		
		public static final double TILT_KP = 20;
		public static final double TILT_KI = 0;
		
		public static final int TOURELLE_PAN_MOTEUR = 2; // can bus
		public static final int TOURELLE_TILT_MOTEUR = 5; // can bus
	}
	
	public static final double LANCEUR_VITESSE_DELTA = 0.1;
	
	// DRIVE AUTO PID
	
	public interface Vision
	{
		public static final int RASPBERRY_PORT = 5808;
		public static final int RASPBERRY_PIN = 25;
		double VISION_THS = 0.13;
	}
	public static final int RAYON_VERT_DIO = 12;
			
	public static final int INTERRUPTEUR_DIGITAL_0 = 2;
	public static final int INTERRUPTEUR_DIGITAL_1 = 3;
	public static final int INTERRUPTEUR_DIGITAL_2 = 4;
	public static final int INTERRUPTEUR_DIGITAL_3 = 5;
	
	public static final int AUTO_EXPIRE = 5; // in seconds


}

