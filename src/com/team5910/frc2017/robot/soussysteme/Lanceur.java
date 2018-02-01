package com.team5910.frc2017.robot.soussysteme;

import com.ctre.CANTalon;
import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.outil.Calculateur;

import edu.wpi.first.wpilibj.command.Subsystem;


public class Lanceur extends Subsystem implements RobotMap.Lanceur{
	
	CANTalon lanceurMoteurPrincipal = new CANTalon(LANCEUR_MOTEUR_PRINCIPAL);
	CANTalon lanceurMoteurEsclave = new CANTalon(LANCEUR_MOTEUR_ESCLAVE);
	
	double vitesseLanceur = 1.0;
	
	public Lanceur() {
		lanceurMoteurEsclave.changeControlMode(CANTalon.TalonControlMode.Follower);
		lanceurMoteurEsclave.set(lanceurMoteurPrincipal.getDeviceID());
		
		/*lanceurMoteurPrincipal.changeControlMode(TalonControlMode.Speed);
		lanceurMoteurPrincipal.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		lanceurMoteurPrincipal.reverseSensor(true);
		lanceurMoteurPrincipal.configEncoderCodesPerRev(1024);
		lanceurMoteurPrincipal.configNominalOutputVoltage(+0.0f, -0.0f);
		lanceurMoteurPrincipal.configPeakOutputVoltage(+12.0f, -12.0f);
		lanceurMoteurPrincipal.setProfile(0);
		lanceurMoteurPrincipal.setF(0.1097);
		lanceurMoteurPrincipal.setP(0.22);
		lanceurMoteurPrincipal.setI(0); 
		lanceurMoteurPrincipal.setD(0);
		lanceurMoteurPrincipal.enable();*/			
	}

    public void initDefaultCommand() {
    }
    
    public void lancer() {
    	lanceurMoteurPrincipal.set(vitesseLanceur);
    }

	public void arreter() {
		lanceurMoteurPrincipal.set(0);
	}
	public void inverser() {
		if (lanceurMoteurPrincipal.get() > 0)
			lanceurMoteurPrincipal.set(0);
		else
			lanceurMoteurPrincipal.set(vitesseLanceur);
	}
	
	public void ajusterVitesse(double delta) {
		vitesseLanceur = Calculateur.clamp(vitesseLanceur + delta, 0.0, 1.0);
		
		if (lanceurMoteurPrincipal.get() > 0)
			lanceurMoteurPrincipal.set(vitesseLanceur);
	}
	
}

