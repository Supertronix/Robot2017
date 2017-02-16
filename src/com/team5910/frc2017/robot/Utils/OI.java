package com.team5910.frc2017.robot.Utils;

import com.team5910.frc2017.commands.Intake;
import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
    
    private final Joystick mMainDriveStick;
    private final Joystick mAccessoriesStick;
    
    public OI() {
    	mMainDriveStick = new Joystick(RobotMap.kMainDriveStickId);
    	mAccessoriesStick = new Joystick(RobotMap.mAccessoriesStickId);
    	
    	JoystickButton intakerTrigger = new JoystickButton(mMainDriveStick, RobotMap.kIntakeButton);
    	intakerTrigger.whenPressed(new Intake());
    }
    
 // DRIVER CONTROLS
    public double getLeftDriveX() {
        return RobotMap.kRevertLeftDriveXAxis ? -mMainDriveStick.getRawAxis(RobotMap.kLeftDriveXAxis): mMainDriveStick.getRawAxis(RobotMap.kLeftDriveXAxis);
    }
    
    public double getLeftDriveY() {
        return RobotMap.kRevertLeftDriveYAxis ? -mMainDriveStick.getRawAxis(RobotMap.kLeftDriveYAxis): mMainDriveStick.getRawAxis(RobotMap.kLeftDriveYAxis);
    }
    
    public double getRightDriveX() {
        return RobotMap.kRevertRightDriveXAxis ? -mMainDriveStick.getRawAxis(RobotMap.kRightDriveXAxis): mMainDriveStick.getRawAxis(RobotMap.kRightDriveXAxis);
    }
    
    public double getRightDriveY() {
        return RobotMap.kRevertRightDriveYAxis ? -mMainDriveStick.getRawAxis(RobotMap.kRightDriveYAxis): mMainDriveStick.getRawAxis(RobotMap.kRightDriveYAxis);
    }
    
// ACCESSORIES
    /*public boolean getIntakeButton() {
    	return mMainDriveStick.getRawButton(RobotMap.kIntakeButton);
    }*/
    
    public boolean getClimberButton() {
    	return mMainDriveStick.getRawButton(RobotMap.kClimberButton);
    }
    
    public boolean getClampButton() {
    	return mMainDriveStick.getRawButton(RobotMap.kClampButton);
    }
    
}
