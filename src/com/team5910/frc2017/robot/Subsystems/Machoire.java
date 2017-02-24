package com.team5910.frc2017.robot.Subsystems;

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
    
	Machoire() {
		LeftClampServo = new Servo(RobotMap.kLeftClampServoId);
		RightClampServo = new Servo(RobotMap.kRightClampServoId);
		clampState = ClampState.CLOSED;
		lastToggleTime = 0.0;
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

	public void toggleAsked() {
		if (Timer.getFPGATimestamp() > lastToggleTime + 1)
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
		LeftClampServo.set(RobotMap.kLeftClampClosedPos);
		RightClampServo.set(RobotMap.kRightClampClosedPos);
		clampState = ClampState.CLOSED;
		
	}
	
	private void openClamp()
	{
		LeftClampServo.set(RobotMap.kLeftClampOpenPos);
		RightClampServo.set(RobotMap.kRightClampOpenPos);
		clampState = ClampState.OPEN;
	}
	
	
}

