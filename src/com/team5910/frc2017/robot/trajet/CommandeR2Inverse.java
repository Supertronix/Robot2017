package com.team5910.frc2017.robot.trajet;

import com.team5910.frc2017.commande.drive.CommandeConduiteAvancer;
import com.team5910.frc2017.commande.drive.CommandeConduiteTourner;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeR2Inverse extends CommandGroup {

public CommandeR2Inverse() {
	//addParallel(new ShooterMotorStart());
	addSequential(new CommandeConduiteTourner(90, 0.008, 0.0002));
	addSequential(new CommandeConduiteAvancer(-4.5, 0.09, 0.00065));
	
	
}

}