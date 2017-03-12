package com.team5910.frc2017.robot;

import com.team5910.frc2017.robot.subsystems.Brasseur;
import com.team5910.frc2017.robot.subsystems.Grimpeur;
import com.team5910.frc2017.robot.subsystems.Indexeur;
import com.team5910.frc2017.robot.subsystems.Intaker;
import com.team5910.frc2017.robot.subsystems.Lanceur;
import com.team5910.frc2017.robot.subsystems.Machoire;
import com.team5910.frc2017.robot.subsystems.Tourelle;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Robot extends Subsystem {
	    
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
    
	@Override
	protected void initDefaultCommand() {
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

