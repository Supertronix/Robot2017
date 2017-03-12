package com.team5910.frc2017.robot;

import com.team5910.frc2017.robot.soussysteme.Brasseur;
import com.team5910.frc2017.robot.soussysteme.Grimpeur;
import com.team5910.frc2017.robot.soussysteme.Indexeur;
import com.team5910.frc2017.robot.soussysteme.Intaker;
import com.team5910.frc2017.robot.soussysteme.Lanceur;
import com.team5910.frc2017.robot.soussysteme.Machoire;
import com.team5910.frc2017.robot.soussysteme.Tourelle;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Robot {
	    
    public Tourelle tourelle;
    public Lanceur lanceur;
    public Intaker intaker;
    public Grimpeur grimpeur;
    public Machoire machoire;
    public Indexeur indexeur;
    public Brasseur brasseur;
    
    public Robot()
    {
    	 tourelle = new Tourelle();
    	 lanceur = new Lanceur();
    	 intaker = new Intaker();
    	 grimpeur = new Grimpeur();
    	 machoire = new Machoire();
    	 indexeur = new Indexeur();
    	 brasseur = new Brasseur();
    }

	public void stopAll() {
		tourelle.stop();
		lanceur.stop();
		intaker.stop();
		grimpeur.stop();
		indexeur.stop();
	}
	
	public void zeroSensors() {
	}
	
	public void intakeButtonEnabled() {
		intaker.intake();
	}
	
	public void intakeButtonDisabled() {
		intaker.stop();
	}

	public void climberButtonEnabled() {
		grimpeur.climb();
		tourelle.stop();
		intaker.stop();
	}
	
	public void climberButtonDisabled() {
		grimpeur.stop();
	}

	public void clampButtonEnabled() {
		machoire.toggleAsked();
		
	}

}

