package com.team5910.frc2017.robot.trajet;

import com.team5910.frc2017.commande.CommandeBrasseurDuree;
import com.team5910.frc2017.commande.CommandeIndexeurDuree;
import com.team5910.frc2017.commande.lanceur.CommandeLanceurDuree;
import com.team5910.frc2017.commande.roues.CommandeConduiteAvancer;
import com.team5910.frc2017.commande.roues.CommandeConduiteTourner;
import com.team5910.frc2017.commande.tourelle.CommandeTourelleChercherCibleAndHold;
import com.team5910.frc2017.commande.tourelle.CommandeTourellePositionnerTilt;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeB3 extends CommandGroup {

public CommandeB3() {
	// public CommandeConduiteDistance(double distance, double driveP, double driveI)
	// public CommandeConduiteTourner(double angle, double P, double I)
	addSequential(new CommandeConduiteAvancer(5.7, 0.08, 0.00065));
	addSequential(new CommandeConduiteTourner(-60, 0.008, 0.0002));
	addSequential(new CommandeConduiteAvancer(6.7, 0.08, 0.00065));
	addSequential(new CommandeConduiteAvancer(-5, 0.10, 0.00065));
	addSequential(new CommandeConduiteTourner(60, 0.005, 0.00011));
	addSequential(new CommandeConduiteAvancer(10, 0.06, 0.00058));

}

}
