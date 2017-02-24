package com.team5910.frc2017.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Superstructure extends Subsystem {
	    
    public Tourelle turret;
    public Lanceur shooter;
    public Intaker intaker;
    public Grimpeur climber;
    public Machoire clamp;
    public Indexeur indexer;
    public Brasseur shuffler;
    
    public Superstructure()
    {
    	 turret = new Tourelle();
    	 shooter = new Lanceur();
    	 intaker = new Intaker();
    	 climber = new Grimpeur();
    	 clamp = new Machoire();
    	 indexer = new Indexeur();
    	 shuffler = new Brasseur();
    }
    
	@Override
	protected void initDefaultCommand() {
	}

	public void stopAll() {
		turret.stop();
		shooter.stop();
		intaker.stop();
		climber.stop();
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
		climber.climb();
		turret.stop();
		intaker.stop();
	}
	
	public void climberButtonDisabled() {
		climber.stop();
	}

	public void clampButtonEnabled() {
		clamp.toggleAsked();
		
	}

}

