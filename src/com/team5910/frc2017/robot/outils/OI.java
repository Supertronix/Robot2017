package com.team5910.frc2017.robot.outils;

import com.team5910.frc2017.commands.driverenamedouble.DriveEncoderReset;
import com.team5910.frc2017.commands.lanceur.ShooterMotorSpeedIncDec;
import com.team5910.frc2017.commands.lanceur.ShooterMotorStart;
import com.team5910.frc2017.commands.lanceur.ShooterMotorStop;
import com.team5910.frc2017.commands.lanceur.ShooterMotorToggle;
import com.team5910.frc2017.commands.superstructurerenamedouble.ClimbMotorStart;
import com.team5910.frc2017.commands.superstructurerenamedouble.ClimbMotorStop;
import com.team5910.frc2017.commands.superstructurerenamedouble.IndexerMotorStart;
import com.team5910.frc2017.commands.superstructurerenamedouble.IndexerMotorStop;
import com.team5910.frc2017.commands.superstructurerenamedouble.IntakeMotorStart;
import com.team5910.frc2017.commands.superstructurerenamedouble.IntakeMotorStop;
import com.team5910.frc2017.commands.superstructurerenamedouble.ShufflerMotorStart;
import com.team5910.frc2017.commands.superstructurerenamedouble.ShufflerMotorStop;
import com.team5910.frc2017.commands.superstructurerenamedouble.ToggleClampState;
import com.team5910.frc2017.commands.tourellerenamedouble.TurretToggleAutoMan;
import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.JoystickBase;
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
    	intakerTrigger.whenPressed(new ShufflerMotorStart());
    	intakerTrigger.whenReleased(new IntakeMotorStop());
    	intakerTrigger.whenReleased(new ShufflerMotorStop());
    	
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
    	indexerTrigger.whenPressed(new ShufflerMotorStart());
    	
    	indexerTrigger.whenReleased(new IndexerMotorStop());    	
    	indexerTrigger.whenReleased(new ShufflerMotorStop());
    	
    	JoystickButton shooterTrigger = new JoystickButton(mAccessoriesStick, RobotMap.kShootButton);
    	shooterTrigger.whenPressed(new ShooterMotorStart());
    	shooterTrigger.whenReleased(new ShooterMotorStop());    
    	
    	JoystickButton shooterSpeedInc = new JoystickButton(mAccessoriesStick, RobotMap.kShooterSpeedIncButton);
    	shooterSpeedInc.whenPressed(new ShooterMotorSpeedIncDec(RobotMap.kShooterSpeedIncDecValue));
    	
    	JoystickButton shooterSpeedDec = new JoystickButton(mAccessoriesStick, RobotMap.kShooterSpeedDecButton);
    	shooterSpeedDec.whenPressed(new ShooterMotorSpeedIncDec(-RobotMap.kShooterSpeedIncDecValue));

    	/*JoystickButton turretToggleModeTrigger = new JoystickButton(mAccessoriesStick, RobotMap.kTurretToggleAutoMan);
    	turretToggleModeTrigger.whenPressed(new TurretToggleAutoMan()); */   
    
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
    public double getPanAxis() {
        return RobotMap.kRevertTurretPanAxis ? -mAccessoriesStick.getRawAxis(RobotMap.kTurretPanAxis): mAccessoriesStick.getRawAxis(RobotMap.kTurretPanAxis);
    }
    public double getTiltAxis() {
        return RobotMap.kRevertTurretTiltAxis ? -mAccessoriesStick.getRawAxis(RobotMap.kTurretTiltAxis): mAccessoriesStick.getRawAxis(RobotMap.kTurretTiltAxis);
    }
    
}
