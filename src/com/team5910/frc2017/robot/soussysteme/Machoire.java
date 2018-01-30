package com.team5910.frc2017.robot.soussysteme;

import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Machoire extends Subsystem {
	
	Servo LeftClampServo;
	Servo RightClampServo;
    
    private enum ClampState {
        CLOSED,
        OPEN, 
    }
    double lastToggleTime;
    
    ClampState clampState;
    
	public Machoire() {
		LeftClampServo = new Servo(RobotMap.MACHOIRE_GAUCHE_SERVO);
		RightClampServo = new Servo(RobotMap.MACHOIRE_DROITE_SERVO);
		clampState = ClampState.CLOSED;
		lastToggleTime = 0.0;
	}
	
	@Override
	protected void initDefaultCommand() {
		closeClamp();
	}

	public void toggleAsked() {
		if (Timer.getFPGATimestamp() > (lastToggleTime + 1))
		{
			toggle();
			lastToggleTime = Timer.getFPGATimestamp();
		}
	}
	
	private void toggle()
	{
		switch (clampState) {
			case CLOSED:
				openClamp();
				break;
			case OPEN:
				closeClamp();
				break;
		}
	}
	
	private void closeClamp()
	{
		LeftClampServo.set(RobotMap.MACHOIRE_GAUCHE_FERMEE);
		RightClampServo.set(RobotMap.MACHOIRE_DROITE_FERMEE);
		clampState = ClampState.CLOSED;
		
	}
	
	private void openClamp()
	{
		LeftClampServo.set(RobotMap.MACHOIRE_GAUCHE_OUVERTE);
		RightClampServo.set(RobotMap.MACHOIRE_DROITE_OUVERTE);
		clampState = ClampState.OPEN;
	}
	
	
}

