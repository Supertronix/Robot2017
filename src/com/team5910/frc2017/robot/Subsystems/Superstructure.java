package com.team5910.frc2017.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Superstructure extends Subsystem {
	
	private static Superstructure instance_ = new Superstructure();
	
	public static Superstructure getInstance() {
        return instance_;
    }
	
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
    
    Turret mTurret = new Turret();
    Intaker mIntaker = new Intaker();
    Climber mClimber = new Climber();
    Clamp mClamp = new Clamp();
    
	@Override
	protected void initDefaultCommand() {
	}

	public void stopAll() {
		mClimber.stop();
	}
	
	public void zeroSensors() {
		
	}
	
	public void intakeButtonEnabled() {
		mIntaker.intake();
	}
	
	public void intakeButtonDisabled() {
		mIntaker.stop();
	}

	public void climberButtonEnabled() {
		mClimber.climb();
		mTurret.stop();
		mIntaker.stop();
	}
	
	public void climberButtonDisabled() {
		mClimber.stop();
	}

	public void clampButtonEnabled() {
		mClamp.toggleAsked();
		
	}

}

