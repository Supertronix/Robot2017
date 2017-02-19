package com.team5910.frc2017.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Superstructure extends Subsystem {
	
	/**
     * Drives actual state, all outputs should be dictated by this state
     */
	
    private enum SystemState {
        FIRING_AIM, // The shooter is firing in auto aim mode
        FIRING_MANUAL, // The shooter is firing in batter mode
    }
    
    /**
     * Drives state changes. Outputs should not be decided based on this enum.
     */
    public enum WantedState {
    }
    
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

