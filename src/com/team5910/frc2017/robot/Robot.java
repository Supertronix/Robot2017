package com.team5910.frc2017.robot;

import com.team5910.frc2017.robot.soussysteme.Brasseur;
import com.team5910.frc2017.robot.soussysteme.Roues;
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
	// Subsystems
	public static Roues drive;
    
    public Robot()
    {
		this.drive = new Roues();    	
		this.tourelle = new Tourelle();
		this.lanceur = new Lanceur();
		this.intaker = new Intaker();
		this.grimpeur = new Grimpeur();
		this.machoire = new Machoire();
		this.indexeur = new Indexeur();
		this.brasseur = new Brasseur();
    }

	public void stopAll() {
		tourelle.stop();
		lanceur.arreter();
		intaker.stop();
		grimpeur.arreter();
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
		grimpeur.grimper();
		tourelle.stop();
		intaker.stop();
	}
	
	public void climberButtonDisabled() {
		grimpeur.arreter();
	}

	public void clampButtonEnabled() {
		machoire.demanderBascule();
		
	}

}

