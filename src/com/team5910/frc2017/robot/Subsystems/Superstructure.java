package com.team5910.frc2017.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Superstructure extends Subsystem {
	    
    public Turret turret;
    public Shooter shooter;
    public Intaker intaker;
    public Climber climber;
    public Clamp clamp;
    public Indexer indexer;

    
    public Superstructure()
    {
    	 turret = new Turret();
    	 shooter = new Shooter();
    	 intaker = new Intaker();
    	 climber = new Climber();
    	 clamp = new Clamp();
    	 indexer = new Indexer();
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

