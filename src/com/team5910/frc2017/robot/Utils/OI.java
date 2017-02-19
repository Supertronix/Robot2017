package com.team5910.frc2017.robot.Utils;

import com.team5910.frc2017.commands.Drive.DriveEncoderReset;
import com.team5910.frc2017.commands.Shooter.ShooterMotorStart;
import com.team5910.frc2017.commands.Shooter.ShooterMotorStop;
import com.team5910.frc2017.commands.Superstructure.ClimbMotorStart;
import com.team5910.frc2017.commands.Superstructure.ClimbMotorStop;
import com.team5910.frc2017.commands.Superstructure.IndexerMotorStart;
import com.team5910.frc2017.commands.Superstructure.IndexerMotorStop;
import com.team5910.frc2017.commands.Superstructure.IntakeMotorStart;
import com.team5910.frc2017.commands.Superstructure.IntakeMotorStop;
import com.team5910.frc2017.commands.Superstructure.ToggleClampState;
import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
    
    private final Joystick mMainDriveStick;
    private final Joystick mAccessoriesStick;
    
    public OI() {
    	mMainDriveStick = new Joystick(RobotMap.kMainDriveStickId);
    	mAccessoriesStick = new Joystick(RobotMap.mAccessoriesStickId);
    	
    	
    	// MAIN JOYSTICK
    	JoystickButton intakerTrigger = new JoystickButton(mMainDriveStick, RobotMap.kIntakeButton);
    	intakerTrigger.whenPressed(new IntakeMotorStart());
    	intakerTrigger.whenReleased(new IntakeMotorStop());
    	
    	JoystickButton climberTrigger = new JoystickButton(mMainDriveStick, RobotMap.kClimberButton);
    	climberTrigger.whenPressed(new ClimbMotorStart());
    	climberTrigger.whenReleased(new ClimbMotorStop());
    	
    	JoystickButton clampTrigger = new JoystickButton(mMainDriveStick, RobotMap.kClampButton);
    	clampTrigger.whenPressed(new ToggleClampState());
    	
    	JoystickButton gyroResetTrigger = new JoystickButton(mMainDriveStick, RobotMap.kGyroResetButton);
    	gyroResetTrigger.whenPressed(new DriveEncoderReset());
    	
    	// ACCESSORIES JOYSTICK
    	JoystickButton indexerTrigger = new JoystickButton(mAccessoriesStick, RobotMap.kIndexerButton);
    	indexerTrigger.whenPressed(new IndexerMotorStart());
    	indexerTrigger.whenReleased(new IndexerMotorStop());    	
    	
    	JoystickButton shooterTrigger = new JoystickButton(mAccessoriesStick, RobotMap.kShootButton);
    	indexerTrigger.whenPressed(new ShooterMotorStart());
    	indexerTrigger.whenReleased(new ShooterMotorStop());    	
    	
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
