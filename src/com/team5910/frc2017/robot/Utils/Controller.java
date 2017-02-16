package com.team5910.frc2017.robot.Utils;

import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;

public class Controller {
	private static Controller mInstance = new Controller();

    public static Controller getInstance() {
        return mInstance;
    }
    
    private final Joystick mMainDriveStick;
    private final Joystick mAccessoriesStick;
    
    private Controller() {
    	mMainDriveStick = new Joystick(RobotMap.kMainDriveStickId);
    	mAccessoriesStick = new Joystick(RobotMap.mAccessoriesStickId);
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
    public boolean getIntakeButton() {
    	return mMainDriveStick.getRawButton(RobotMap.kIntakeButton);
    }
    
    public boolean getClimberButton() {
    	return mMainDriveStick.getRawButton(RobotMap.kClimberButton);
    }
    
    public boolean getClampButton() {
    	return mMainDriveStick.getRawButton(RobotMap.kClampButton);
    }
    
}
