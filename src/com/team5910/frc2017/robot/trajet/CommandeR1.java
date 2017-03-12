package com.team5910.frc2017.robot.trajet;

import com.team5910.frc2017.commande.drive.CommandeConduiteAvancer;
import com.team5910.frc2017.commande.drive.CommandeConduiteAvancerAvecPrecision;
import com.team5910.frc2017.commande.drive.CommandeConduiteLaterale;
import com.team5910.frc2017.commande.drive.CommandeConduiteTourner;
import com.team5910.frc2017.commande.lanceur.CommandeLanceurDemarrer;
import com.team5910.frc2017.commande.lanceur.CommandeLanceurSpinUp;
import com.team5910.frc2017.commande.tourelle.CommandeTourelleChercherCible;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeR1 extends CommandGroup {

public CommandeR1() {
	// public CommandeConduiteDistance(double distance, double driveP, double driveI)
	// public CommandeConduiteTourner(double angle, double P, double I)
	addSequential(new CommandeConduiteAvancer(5.5, 0.08, 0.00058));
	addSequential(new CommandeConduiteTourner(-60, 0.008, 0.0002));
	addSequential(new CommandeConduiteAvancer(6.5, 0.08, 0.00045));
	addSequential(new CommandeConduiteAvancer(-2, 0.18, 0.00045));
	addSequential(new CommandeConduiteTourner(150, 0.005, 0.00011));
	addSequential(new CommandeConduiteAvancer(6.5, 0.08, 0.00058));
	
}

}
