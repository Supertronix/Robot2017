package com.team5910.frc2017.robot.interaction;

import com.team5910.frc2017.commande.CommandeBrasseurArreter;
import com.team5910.frc2017.commande.CommandeBrasseurDemarrer;
import com.team5910.frc2017.commande.CommandeGrimpeurArreter;
import com.team5910.frc2017.commande.CommandeGrimpeurDemarrer;
import com.team5910.frc2017.commande.CommandeIndexeurArreter;
import com.team5910.frc2017.commande.CommandeIndexeurDemarrer;
import com.team5910.frc2017.commande.CommandeIntakerArreter;
import com.team5910.frc2017.commande.CommandeIntakerDemarrer;
import com.team5910.frc2017.commande.CommandeMachoireToggle;
import com.team5910.frc2017.commande.lanceur.CommandeLanceurAccelerer;
import com.team5910.frc2017.commande.lanceur.CommandeLanceurArreter;
import com.team5910.frc2017.commande.lanceur.CommandeLanceurDemarrer;
import com.team5910.frc2017.commande.roues.CommandeConduiteEncodeurZero;
import com.team5910.frc2017.commande.tourelle.CommandeTourelleChangerEtat;
import com.team5910.frc2017.commande.tourelle.CommandeTourelleToggleAutoMan;
import com.team5910.frc2017.robot.RobotControleur;
import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.soussysteme.Tourelle;
import com.team5910.frc2017.robot.soussysteme.Tourelle.EtatControle;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Manette implements RobotMap.Manette{
    
    private final Joystick manetteConduitePrincipale;
    private final Joystick manetteAccessoire;
    
    public Manette() {
    	manetteConduitePrincipale = new Joystick(MANETTE_CONDUITE_PRINCIPALE);
    	manetteAccessoire = new Joystick(MANETTE_ACCESSOIRE);
    	
    	// MAIN JOYSTICK
    	JoystickButton actionIntaker = new JoystickButton(manetteConduitePrincipale, BOUTON_AVALEUR);
    	actionIntaker.whenPressed(new CommandeIntakerDemarrer());
    	actionIntaker.whenPressed(new CommandeBrasseurDemarrer());
    	actionIntaker.whenReleased(new CommandeIntakerArreter());
    	actionIntaker.whenReleased(new CommandeBrasseurArreter());
    	
    	JoystickButton actionGrimpeur = new JoystickButton(manetteConduitePrincipale, BOUTON_GRIMPEUR);
    	actionGrimpeur.whenPressed(new CommandeGrimpeurDemarrer());
    	actionGrimpeur.whenReleased(new CommandeGrimpeurArreter());
    	
    	JoystickButton actionMachoire = new JoystickButton(manetteConduitePrincipale, BOUTON_MACHOIRE);
    	actionMachoire.whenPressed(new CommandeMachoireToggle());
    	
    	JoystickButton actionGyroReset = new JoystickButton(manetteConduitePrincipale, BOUTON_GYRO_RESET);
    	actionGyroReset.whenPressed(new CommandeConduiteEncodeurZero());
    	
    	// ACCESSORIES JOYSTICK
    	JoystickButton actionIndexeur = new JoystickButton(manetteAccessoire, BOUTON_INDEXEUR);
    	actionIndexeur.whenPressed(new CommandeIndexeurDemarrer());
    	actionIndexeur.whenPressed(new CommandeBrasseurDemarrer());
    	
    	actionIndexeur.whenReleased(new CommandeIndexeurArreter());    	
    	actionIndexeur.whenReleased(new CommandeBrasseurArreter());
    	
    	JoystickButton actionLanceur = new JoystickButton(manetteAccessoire, BOUTON_LANCEUR);
    	actionLanceur.whenPressed(new CommandeLanceurDemarrer());
    	actionLanceur.whenReleased(new CommandeLanceurArreter());    
    	
    	JoystickButton actionLanceurAcceleration = new JoystickButton(manetteAccessoire, BOUTON_LANCEUR_VITESSE_INCREMENTE);
    	actionLanceurAcceleration.whenPressed(new CommandeLanceurAccelerer(RobotMap.LANCEUR_VITESSE_DELTA));
    	
    	JoystickButton actionLanceurDeceleration = new JoystickButton(manetteAccessoire, BOUTON_LANCEUR_VITESSE_DECREMENTE);
    	actionLanceurDeceleration.whenPressed(new CommandeLanceurAccelerer(-RobotMap.LANCEUR_VITESSE_DELTA));

    	JoystickButton turretToggleModeEnable = new JoystickButton(manetteAccessoire, BOUTON_TOURELLE_TOGGLE_AUTO_MANUEL);
    	turretToggleModeEnable.whenPressed(new CommandeTourelleChangerEtat(EtatControle.RECHERCHE));  
    	turretToggleModeEnable.whenReleased(new CommandeTourelleChangerEtat(EtatControle.MANUEL)); 
    
    }
    
 // DRIVER CONTROLS
    public double getConduiteGaucheX() {
        return INVERSION_CONDUITE_X_GAUCHE ? -manetteConduitePrincipale.getRawAxis(CONDUITE_X_GAUCHE): manetteConduitePrincipale.getRawAxis(CONDUITE_X_GAUCHE);
    }
    
    public double getConduiteGaucheY() {
        return INVERSION_CONDUITE_Y_GAUCHE ? -manetteConduitePrincipale.getRawAxis(CONDUITE_Y_GAUCHE): manetteConduitePrincipale.getRawAxis(CONDUITE_Y_GAUCHE);
    }
    
    public double getConduiteDroiteX() {
        return INVERSION_CONDUITE_X_DROITE ? -manetteConduitePrincipale.getRawAxis(CONDUITE_X_DROITE): manetteConduitePrincipale.getRawAxis(CONDUITE_X_DROITE);
    }
    
    public double getConduiteDroiteY() {
        return INVERSION_CONDUITE_Y_DROITE ? -manetteConduitePrincipale.getRawAxis(CONDUITE_Y_DROITE): manetteConduitePrincipale.getRawAxis(CONDUITE_Y_DROITE);
    }
    
// ACCESSORIES
    public boolean getBoutonAvaleur() {
    	return manetteConduitePrincipale.getRawButton(BOUTON_AVALEUR);
    }
    
    public boolean getBoutonGrimpeur() {
    	return manetteConduitePrincipale.getRawButton(BOUTON_GRIMPEUR);
    }
    
    public boolean getBoutonMachoire() {
    	return manetteConduitePrincipale.getRawButton(BOUTON_MACHOIRE);
    }
    public double getPanAxe() {
        return RobotMap.Tourelle.INVERSION_TOURELLE_PAN_AXE ? -manetteAccessoire.getRawAxis(com.team5910.frc2017.robot.RobotMap.Tourelle.TOURELLE_PAN_AXE): manetteAccessoire.getRawAxis(com.team5910.frc2017.robot.RobotMap.Tourelle.TOURELLE_PAN_AXE);
    }
    public double getTiltAxe() {
        return RobotMap.Tourelle.INVERSION_TOURELLE_TILT_AXE ? -manetteAccessoire.getRawAxis(RobotMap.Tourelle.TOURELLE_TILT_AXE): manetteAccessoire.getRawAxis(RobotMap.Tourelle.TOURELLE_TILT_AXE);
    }
    
}
