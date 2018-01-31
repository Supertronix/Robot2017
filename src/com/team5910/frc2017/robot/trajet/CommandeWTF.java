package com.team5910.frc2017.robot.trajet;

import com.team5910.frc2017.commande.CommandeBrasseurDuree;
import com.team5910.frc2017.commande.CommandeIndexeurDuree;
import com.team5910.frc2017.commande.lanceur.CommandeLanceurDuree;
import com.team5910.frc2017.commande.roues.CommandeConduiteAvancer;
import com.team5910.frc2017.commande.roues.CommandeConduiteTourner;
import com.team5910.frc2017.commande.tourelle.CommandeTourelleChercherCibleAndHold;
import com.team5910.frc2017.commande.tourelle.CommandeTourellePositionnerTilt;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeWTF extends CommandGroup {

public CommandeWTF() {
	addSequential(new CommandeConduiteAvancer(12, 0.06, 0.00065));

	
}

}
