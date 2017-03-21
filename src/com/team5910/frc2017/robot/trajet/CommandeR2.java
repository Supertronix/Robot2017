package com.team5910.frc2017.robot.trajet;

import com.team5910.frc2017.commande.CommandeBrasseurDuree;
import com.team5910.frc2017.commande.CommandeIndexeurDemarrer;
import com.team5910.frc2017.commande.CommandeIndexeurDuree;
import com.team5910.frc2017.commande.drive.CommandeConduiteAvancer;
import com.team5910.frc2017.commande.drive.CommandeConduiteTourner;
import com.team5910.frc2017.commande.lanceur.CommandeLanceurDemarrer;
import com.team5910.frc2017.commande.lanceur.CommandeLanceurDuree;
import com.team5910.frc2017.commande.tourelle.CommandeTourelleChangerEtat;
import com.team5910.frc2017.commande.tourelle.CommandeTourelleChercherCible;
import com.team5910.frc2017.commande.tourelle.CommandeTourelleChercherCibleAndHold;
import com.team5910.frc2017.commande.tourelle.CommandeTourelleChercherCibleAuto;
import com.team5910.frc2017.commande.tourelle.CommandeTourellePositionnerTilt;
import com.team5910.frc2017.robot.soussysteme.Tourelle.SystemState;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeR2 extends CommandGroup {

public CommandeR2() {
	addSequential(new CommandeConduiteAvancer(6.9, 0.075, 0.00065));
	addSequential(new CommandeConduiteAvancer(-2, 0.18, 0.00045));
	addSequential(new CommandeConduiteTourner(-90, 0.008, 0.0002));
	addSequential(new CommandeConduiteAvancer(-5, 0.17, 0.00045));
	addSequential(new CommandeTourellePositionnerTilt(-520));
	addSequential(new CommandeTourelleChercherCibleAndHold(1.5));
	addParallel(new CommandeLanceurDuree(10));
	addParallel(new CommandeBrasseurDuree(10));
	addParallel(new CommandeIndexeurDuree(10));
}

}
