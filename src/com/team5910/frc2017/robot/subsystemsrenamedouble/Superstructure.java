package com.team5910.frc2017.robot.subsystemsrenamedouble;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Superstructure extends Subsystem {
	    
    public Tourelle tourelle;
    public Lanceur lanceur;
    public Intaker intaker;
    public Grimpeur grimpeur;
    public Machoire machoire;
    public Indexeur indexer;
    public Brasseur brasseur;
    
    public Superstructure()
    {
    	 tourelle = new Tourelle();
    	 lanceur = new Lanceur();
    	 intaker = new Intaker();
    	 grimpeur = new Grimpeur();
    	 machoire = new Machoire();
    	 indexer = new Indexeur();
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
		indexer.stop();
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

