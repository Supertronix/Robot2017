package com.team5910.frc2017.robot.trajet;

import com.team5910.frc2017.commande.drive.CommandeConduiteAvancer;
import com.team5910.frc2017.commande.drive.CommandeConduiteTourner;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeR1 extends CommandGroup {

public CommandeR1() {
	// public CommandeConduiteDistance(double distance, double driveP, double driveI)
	// public CommandeConduiteTourner(double angle, double P, double I)
	addSequential(new CommandeConduiteAvancer(5.5, 0.08, 0.00065));
	addSequential(new CommandeConduiteTourner(-60, 0.008, 0.0002));
	addSequential(new CommandeConduiteAvancer(6.7, 0.08, 0.00065));
	addSequential(new CommandeConduiteAvancer(-3.5, 0.16, 0.00045));
	addSequential(new CommandeConduiteTourner(150, 0.005, 0.00011));
	addSequential(new CommandeConduiteAvancer(5.5, 0.08, 0.00058));
	
}

}
