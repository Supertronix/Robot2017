package com.team5910.frc2017.robot.trajet;

import com.team5910.frc2017.commande.drive.CommandeConduiteAvancer;
import com.team5910.frc2017.commande.drive.CommandeConduiteAvancerAvecPrecision;
import com.team5910.frc2017.commande.drive.CommandeConduiteLaterale;
import com.team5910.frc2017.commande.drive.CommandeConduiteTourner;
import com.team5910.frc2017.commande.lanceur.CommandeLanceurDemarrer;
import com.team5910.frc2017.commande.lanceur.CommandeLanceurSpinUp;
import com.team5910.frc2017.commande.tourelle.CommandeTourelleChercherCible;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeR3 extends CommandGroup {

public CommandeR3() {
	
	addSequential(new CommandeConduiteAvancer(6.6, 0.09, 0.00045));
	//addSequential(new DriveDistance(-2, 0.18, 0.00045));
	//addSequential(new DriveRotate(-90, 0.008, 0.0002));
}

}
