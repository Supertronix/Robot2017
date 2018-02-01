package com.team5910.frc2017.robot.trajet;

import com.team5910.frc2017.commande.CommandeBrasseurDuree;
import com.team5910.frc2017.commande.CommandeIndexeurDemarrer;
import com.team5910.frc2017.commande.CommandeIndexeurDuree;
import com.team5910.frc2017.commande.lanceur.CommandeLanceurDemarrer;
import com.team5910.frc2017.commande.lanceur.CommandeLanceurDuree;
import com.team5910.frc2017.commande.roues.CommandeConduiteAvancer;
import com.team5910.frc2017.commande.roues.CommandeConduiteTourner;
import com.team5910.frc2017.commande.tourelle.CommandeTourelleChangerEtat;
import com.team5910.frc2017.commande.tourelle.CommandeTourelleChercherCible;
import com.team5910.frc2017.commande.tourelle.CommandeTourelleChercherCibleAndHold;
import com.team5910.frc2017.commande.tourelle.CommandeTourelleChercherCibleAuto;
import com.team5910.frc2017.commande.tourelle.CommandeTourellePositionnerTilt;
import com.team5910.frc2017.robot.soussysteme.Tourelle.EtatControle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeTrajetR2 extends CommandGroup {

public CommandeTrajetR2() {
	addSequential(new CommandeConduiteAvancer(7.3, 0.070, 0.00065)); //0.075
	addSequential(new CommandeConduiteAvancer(-2, 0.12, 0.00045)); //.18
	addSequential(new CommandeConduiteTourner(-90, 0.008, 0.0002));
	addSequential(new CommandeConduiteAvancer(-5, 0.17, 0.00045));
	addSequential(new CommandeTourellePositionnerTilt(-500)); // negative on top old 520
	addSequential(new CommandeTourelleChercherCibleAndHold(1));
	addParallel(new CommandeLanceurDuree(10));
	addParallel(new CommandeBrasseurDuree(10));
	addParallel(new CommandeIndexeurDuree(10));
}

}
