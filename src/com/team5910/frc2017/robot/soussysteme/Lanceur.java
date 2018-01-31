package com.team5910.frc2017.robot.soussysteme;

import com.ctre.CANTalon;
import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.outil.Calculateur;

import edu.wpi.first.wpilibj.command.Subsystem;


public class Lanceur extends Subsystem {
	
	CANTalon lanceurMoteurPrincipal = new CANTalon(RobotMap.LANCEUR_MOTEUR_PRINCIPAL);
	CANTalon lanceurMoteurEsclave = new CANTalon(RobotMap.LANCEUR_MOTEUR_ESCLAVE);
	
	double vitesseLanceur = 1.0;
	
	public Lanceur() {
		lanceurMoteurEsclave.changeControlMode(CANTalon.TalonControlMode.Follower);
		lanceurMoteurEsclave.set(lanceurMoteurPrincipal.getDeviceID());
		
		/*ShooterDriveMain.changeControlMode(TalonControlMode.Speed);
		ShooterDriveMain.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		ShooterDriveMain.reverseSensor(true);
		ShooterDriveMain.configEncoderCodesPerRev(1024);
		ShooterDriveMain.configNominalOutputVoltage(+0.0f, -0.0f);
		ShooterDriveMain.configPeakOutputVoltage(+12.0f, -12.0f);
		ShooterDriveMain.setProfile(0);
		ShooterDriveMain.setF(0.1097);
		ShooterDriveMain.setP(0.22);
		ShooterDriveMain.setI(0); 
		ShooterDriveMain.setD(0);
		ShooterDriveMain.enable();*/			
	}

    public void initDefaultCommand() {
    }
    
    public void shoot() {
    	lanceurMoteurPrincipal.set(vitesseLanceur);
    }

	public void stop() {
		lanceurMoteurPrincipal.set(0);
	}
	public void toggle() {
		if (lanceurMoteurPrincipal.get() > 0)
			lanceurMoteurPrincipal.set(0);
		else
			lanceurMoteurPrincipal.set(vitesseLanceur);
	}
	
	public void incDecSpeed(double aChangeValue) {
		vitesseLanceur = Calculateur.clamp(vitesseLanceur + aChangeValue, 0.0, 1.0);
		
		if (lanceurMoteurPrincipal.get() > 0)
			lanceurMoteurPrincipal.set(vitesseLanceur);
	}
	
}

