package com.team5910.frc2017.robot.soussysteme;

import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Machoire extends Subsystem {
	
	Servo verrouMachoireGauche;
	Servo verrouMachoireDroite;
        
	public Machoire() {
		verrouMachoireGauche = new Servo(RobotMap.MACHOIRE_GAUCHE_SERVO);
		verrouMachoireDroite = new Servo(RobotMap.MACHOIRE_DROITE_SERVO);
		etatVerrou = EtatVerrou.FERME;
		timestampDerniereBascule = 0.0;
	}
	
	// sinon [javac] C:\supertronix\Supertronix5910\src\com\team5910\frc2017\robot\soussysteme\Machoire.java:9: error: Machoire is not abstract and does not override abstract method initDefaultCommand() in Subsystem
	@Override
	protected void initDefaultCommand() {
		fermer();
	}

	public void demanderBascule() {
		if (Timer.getFPGATimestamp() > (timestampDerniereBascule + 1))
		{
			basculer();
			timestampDerniereBascule = Timer.getFPGATimestamp();
		}
	}
	
	
    private enum EtatVerrou {
        FERME,
        OUVERT, 
    }
    double timestampDerniereBascule;
    
    EtatVerrou etatVerrou;

	private void basculer() // toggle
	{
		switch (etatVerrou) {
			case FERME:
				ouvrir();
				break;
			case OUVERT:
				fermer();
				break;
		}
	}
	
	private void fermer()
	{
		verrouMachoireGauche.set(RobotMap.MACHOIRE_GAUCHE_FERMEE);
		verrouMachoireDroite.set(RobotMap.MACHOIRE_DROITE_FERMEE);
		etatVerrou = EtatVerrou.FERME;
		
	}
	
	private void ouvrir()
	{
		verrouMachoireGauche.set(RobotMap.MACHOIRE_GAUCHE_OUVERTE);
		verrouMachoireDroite.set(RobotMap.MACHOIRE_DROITE_OUVERTE);
		etatVerrou = EtatVerrou.OUVERT;
	}
	
	
}

