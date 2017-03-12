package com.team5910.frc2017.robot.trajet;

import com.team5910.frc2017.commande.drive.CommandeConduiteAvancer;
import com.team5910.frc2017.robot.interaction.AffichageStation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandeLigneDroite extends CommandGroup {

@SuppressWarnings("deprecation")
public CommandeLigneDroite() {
	// public CommandeConduiteDistance(double distance, double driveP, double driveI)
	// public CommandeConduiteTourner(double angle, double P, double I)
	addSequential(new CommandeConduiteAvancer(SmartDashboard.getNumber(AffichageStation.DRIVE_DISTANCE), SmartDashboard.getNumber(AffichageStation.DRIVE_DISTANCE_P), SmartDashboard.getNumber(AffichageStation.DRIVE_DISTANCE_I)));	
}

}
