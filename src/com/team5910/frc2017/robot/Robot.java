package com.team5910.frc2017.robot;

import com.team5910.frc2017.robot.soussysteme.Brasseur;
import com.team5910.frc2017.robot.soussysteme.Roues;
import com.team5910.frc2017.robot.soussysteme.Grimpeur;
import com.team5910.frc2017.robot.soussysteme.Indexeur;
import com.team5910.frc2017.robot.soussysteme.Avaleur;
import com.team5910.frc2017.robot.soussysteme.Lanceur;
import com.team5910.frc2017.robot.soussysteme.Machoire;
import com.team5910.frc2017.robot.soussysteme.Tourelle;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Robot {
	    
    public Tourelle tourelle;
    public Lanceur lanceur;
    public Avaleur intaker;
    public Grimpeur grimpeur;
    public Machoire machoire;
    public Indexeur indexeur;
    public Brasseur brasseur;
	public static Roues roues; // static pour les requires
    
    public Robot()
    {
		Robot.roues = new Roues(); // static	
		this.tourelle = new Tourelle();
		this.lanceur = new Lanceur();
		this.intaker = new Avaleur();
		this.grimpeur = new Grimpeur();
		this.machoire = new Machoire();
		this.indexeur = new Indexeur();
		this.brasseur = new Brasseur();
    }

	public void arreter() {
		tourelle.arreter();
		lanceur.arreter();
		intaker.arreter();
		grimpeur.arreter();
		indexeur.arreter();
	}
	
	public void initialiserCapteurs() {
	}
}

