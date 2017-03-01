package com.team5910.frc2017.robot.outils;

import com.team5910.frc2017.commands.drive.DriveEncoderReset;
import com.team5910.frc2017.commands.lanceur.ShooterMotorSpeedIncDec;
import com.team5910.frc2017.commands.lanceur.ShooterMotorStart;
import com.team5910.frc2017.commands.lanceur.ShooterMotorStop;
import com.team5910.frc2017.commands.lanceur.ShooterMotorToggle;
import com.team5910.frc2017.commands.superstructure.ClimbMotorStart;
import com.team5910.frc2017.commands.superstructure.ClimbMotorStop;
import com.team5910.frc2017.commands.superstructure.IndexerMotorStart;
import com.team5910.frc2017.commands.superstructure.IndexerMotorStop;
import com.team5910.frc2017.commands.superstructure.IntakeMotorStart;
import com.team5910.frc2017.commands.superstructure.IntakeMotorStop;
import com.team5910.frc2017.commands.superstructure.ShufflerMotorStart;
import com.team5910.frc2017.commands.superstructure.ShufflerMotorStop;
import com.team5910.frc2017.commands.superstructure.ToggleClampState;
import com.team5910.frc2017.commands.tourelle.TurretToggleAutoMan;
import com.team5910.frc2017.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.JoystickBase;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
    
    private final Joystick mMainDriveStick;
    private final Joystick mAccessoriesStick;
    
    public OI() {
    	mMainDriveStick = new Joystick(RobotMap.MANETTE_CONDUITE_PRINCIPALE);
    	mAccessoriesStick = new Joystick(RobotMap.MANETTE_ACCESSOIRE);
    	
    	
    	// MAIN JOYSTICK
    	JoystickButton intakerTrigger = new JoystickButton(mMainDriveStick, RobotMap.BOUTON_INTAKE);
    	intakerTrigger.whenPressed(new IntakeMotorStart());
    	intakerTrigger.whenPressed(new ShufflerMotorStart());
    	intakerTrigger.whenReleased(new IntakeMotorStop());
    	intakerTrigger.whenReleased(new ShufflerMotorStop());
    	
    	JoystickButton climberTrigger = new JoystickButton(mMainDriveStick, RobotMap.BOUTON_GRIMPEUR);
    	climberTrigger.whenPressed(new ClimbMotorStart());
    	climberTrigger.whenReleased(new ClimbMotorStop());
    	
    	JoystickButton clampTrigger = new JoystickButton(mMainDriveStick, RobotMap.BOUTON_MACHOIRE);
    	clampTrigger.whenPressed(new ToggleClampState());
    	
    	JoystickButton gyroResetTrigger = new JoystickButton(mMainDriveStick, RobotMap.BOUTON_GYRO_RESET);
    	gyroResetTrigger.whenPressed(new DriveEncoderReset());
    	
    	// ACCESSORIES JOYSTICK
    	JoystickButton indexerTrigger = new JoystickButton(mAccessoriesStick, RobotMap.BOUTON_INDEXEUR);
    	indexerTrigger.whenPressed(new IndexerMotorStart());
    	indexerTrigger.whenPressed(new ShufflerMotorStart());
    	
    	indexerTrigger.whenReleased(new IndexerMotorStop());    	
    	indexerTrigger.whenReleased(new ShufflerMotorStop());
    	
    	JoystickButton shooterTrigger = new JoystickButton(mAccessoriesStick, RobotMap.BOUTON_LANCEUR);
    	shooterTrigger.whenPressed(new ShooterMotorStart());
    	shooterTrigger.whenReleased(new ShooterMotorStop());    
    	
    	JoystickButton shooterSpeedInc = new JoystickButton(mAccessoriesStick, RobotMap.BOUTON_LANCEUR_VITESSE_INCREMENTE);
    	shooterSpeedInc.whenPressed(new ShooterMotorSpeedIncDec(RobotMap.LANCEUR_VITESSE_DELTA));
    	
    	JoystickButton shooterSpeedDec = new JoystickButton(mAccessoriesStick, RobotMap.BOUTON_LANCEUR_VITESSE_DECREMENTE);
    	shooterSpeedDec.whenPressed(new ShooterMotorSpeedIncDec(-RobotMap.LANCEUR_VITESSE_DELTA));

    	/*JoystickButton turretToggleModeTrigger = new JoystickButton(mAccessoriesStick, RobotMap.kTurretToggleAutoMan);
    	turretToggleModeTrigger.whenPressed(new TurretToggleAutoMan()); */   
    
    }
    
 // DRIVER CONTROLS
    public double getLeftDriveX() {
        return RobotMap.INVERSION_CONDUITE_X_GAUCHE ? -mMainDriveStick.getRawAxis(RobotMap.CONDUITE_X_GAUCHE): mMainDriveStick.getRawAxis(RobotMap.CONDUITE_X_GAUCHE);
    }
    
    public double getLeftDriveY() {
        return RobotMap.INVERSION_CONDUITE_X_GAUCHE ? -mMainDriveStick.getRawAxis(RobotMap.CONDUITE_Y_GAUCHE): mMainDriveStick.getRawAxis(RobotMap.CONDUITE_Y_GAUCHE);
    }
    
    public double getRightDriveX() {
        return RobotMap.INVERSION_CONDUITE_X_DROITE ? -mMainDriveStick.getRawAxis(RobotMap.CONDUITE_X_DROITE): mMainDriveStick.getRawAxis(RobotMap.CONDUITE_X_DROITE);
    }
    
    public double getRightDriveY() {
        return RobotMap.INVERSION_CONDUITE_Y_DROITE ? -mMainDriveStick.getRawAxis(RobotMap.CONDUITE_Y_DROITE): mMainDriveStick.getRawAxis(RobotMap.CONDUITE_Y_DROITE);
    }
    
// ACCESSORIES
    public boolean getIntakeButton() {
    	return mMainDriveStick.getRawButton(RobotMap.BOUTON_INTAKE);
    }
    
    public boolean getClimberButton() {
    	return mMainDriveStick.getRawButton(RobotMap.BOUTON_GRIMPEUR);
    }
    
    public boolean getClampButton() {
    	return mMainDriveStick.getRawButton(RobotMap.BOUTON_MACHOIRE);
    }
    public double getPanAxis() {
        return RobotMap.INVERSION_TOURELLE_PAN_AXE ? -mAccessoriesStick.getRawAxis(RobotMap.TOURELLE_PAN_AXE): mAccessoriesStick.getRawAxis(RobotMap.TOURELLE_PAN_AXE);
    }
    public double getTiltAxis() {
        return RobotMap.INVERSION_TOURELLE_TILT_AXE ? -mAccessoriesStick.getRawAxis(RobotMap.TOURELLE_TILT_AXE): mAccessoriesStick.getRawAxis(RobotMap.TOURELLE_TILT_AXE);
    }
    
}
