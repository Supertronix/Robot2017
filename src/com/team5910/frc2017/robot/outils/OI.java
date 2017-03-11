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
    
    private final Joystick manetteConduitePrincipale;
    private final Joystick manetteAccessoire;
    
    public OI() {
    	manetteConduitePrincipale = new Joystick(RobotMap.MANETTE_CONDUITE_PRINCIPALE);
    	manetteAccessoire = new Joystick(RobotMap.MANETTE_ACCESSOIRE);
    	
    	
    	// MAIN JOYSTICK
    	JoystickButton actionIntaker = new JoystickButton(manetteConduitePrincipale, RobotMap.BOUTON_INTAKE);
    	actionIntaker.whenPressed(new IntakeMotorStart());
    	actionIntaker.whenPressed(new ShufflerMotorStart());
    	actionIntaker.whenReleased(new IntakeMotorStop());
    	actionIntaker.whenReleased(new ShufflerMotorStop());
    	
    	JoystickButton actionGrimpeur = new JoystickButton(manetteConduitePrincipale, RobotMap.BOUTON_GRIMPEUR);
    	actionGrimpeur.whenPressed(new ClimbMotorStart());
    	actionGrimpeur.whenReleased(new ClimbMotorStop());
    	
    	JoystickButton actionMachoire = new JoystickButton(manetteConduitePrincipale, RobotMap.BOUTON_MACHOIRE);
    	actionMachoire.whenPressed(new ToggleClampState());
    	
    	JoystickButton actionGyroReset = new JoystickButton(manetteConduitePrincipale, RobotMap.BOUTON_GYRO_RESET);
    	actionGyroReset.whenPressed(new DriveEncoderReset());
    	
    	// ACCESSORIES JOYSTICK
    	JoystickButton actionIndexeur = new JoystickButton(manetteAccessoire, RobotMap.BOUTON_INDEXEUR);
    	actionIndexeur.whenPressed(new IndexerMotorStart());
    	actionIndexeur.whenPressed(new ShufflerMotorStart());
    	
    	actionIndexeur.whenReleased(new IndexerMotorStop());    	
    	actionIndexeur.whenReleased(new ShufflerMotorStop());
    	
    	JoystickButton actionLanceur = new JoystickButton(manetteAccessoire, RobotMap.BOUTON_LANCEUR);
    	actionLanceur.whenPressed(new ShooterMotorStart());
    	actionLanceur.whenReleased(new ShooterMotorStop());    
    	
    	JoystickButton actionLanceurAcceleration = new JoystickButton(manetteAccessoire, RobotMap.BOUTON_LANCEUR_VITESSE_INCREMENTE);
    	actionLanceurAcceleration.whenPressed(new ShooterMotorSpeedIncDec(RobotMap.LANCEUR_VITESSE_DELTA));
    	
    	JoystickButton actionLanceurDeceleration = new JoystickButton(manetteAccessoire, RobotMap.BOUTON_LANCEUR_VITESSE_DECREMENTE);
    	actionLanceurDeceleration.whenPressed(new ShooterMotorSpeedIncDec(-RobotMap.LANCEUR_VITESSE_DELTA));

    	/*JoystickButton turretToggleModeTrigger = new JoystickButton(mAccessoriesStick, RobotMap.kTurretToggleAutoMan);
    	turretToggleModeTrigger.whenPressed(new TurretToggleAutoMan()); */   
    
    }
    
 // DRIVER CONTROLS
    public double getConduiteGaucheX() {
        return RobotMap.INVERSION_CONDUITE_X_GAUCHE ? -manetteConduitePrincipale.getRawAxis(RobotMap.CONDUITE_X_GAUCHE): manetteConduitePrincipale.getRawAxis(RobotMap.CONDUITE_X_GAUCHE);
    }
    
    public double getConduiteGaucheY() {
        return RobotMap.INVERSION_CONDUITE_Y_GAUCHE ? -manetteConduitePrincipale.getRawAxis(RobotMap.CONDUITE_Y_GAUCHE): manetteConduitePrincipale.getRawAxis(RobotMap.CONDUITE_Y_GAUCHE);
    }
    
    public double getConduiteDroiteX() {
        return RobotMap.INVERSION_CONDUITE_X_DROITE ? -manetteConduitePrincipale.getRawAxis(RobotMap.CONDUITE_X_DROITE): manetteConduitePrincipale.getRawAxis(RobotMap.CONDUITE_X_DROITE);
    }
    
    public double getConduiteDroiteY() {
        return RobotMap.INVERSION_CONDUITE_Y_DROITE ? -manetteConduitePrincipale.getRawAxis(RobotMap.CONDUITE_Y_DROITE): manetteConduitePrincipale.getRawAxis(RobotMap.CONDUITE_Y_DROITE);
    }
    
// ACCESSORIES
    public boolean getIntakeButton() {
    	return manetteConduitePrincipale.getRawButton(RobotMap.BOUTON_INTAKE);
    }
    
    public boolean getClimberButton() {
    	return manetteConduitePrincipale.getRawButton(RobotMap.BOUTON_GRIMPEUR);
    }
    
    public boolean getClampButton() {
    	return manetteConduitePrincipale.getRawButton(RobotMap.BOUTON_MACHOIRE);
    }
    public double getPanAxe() {
        return RobotMap.INVERSION_TOURELLE_PAN_AXE ? -manetteAccessoire.getRawAxis(RobotMap.TOURELLE_PAN_AXE): manetteAccessoire.getRawAxis(RobotMap.TOURELLE_PAN_AXE);
    }
    public double getTiltAxe() {
        return RobotMap.INVERSION_TOURELLE_TILT_AXE ? -manetteAccessoire.getRawAxis(RobotMap.TOURELLE_TILT_AXE): manetteAccessoire.getRawAxis(RobotMap.TOURELLE_TILT_AXE);
    }
    
}
