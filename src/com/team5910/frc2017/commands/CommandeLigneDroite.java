package com.team5910.frc2017.commands;

import com.team5910.frc2017.commands.drive.CommandeConduiteDistance;
import com.team5910.frc2017.commands.drive.CommandeConduiteDistancePrecision;
import com.team5910.frc2017.commands.drive.CommandeConduiteTourner;
import com.team5910.frc2017.commands.drive.CommandeConduiteLaterale;
import com.team5910.frc2017.commands.lanceur.ShooterMotorStart;
import com.team5910.frc2017.commands.lanceur.SpinUpShooter;
import com.team5910.frc2017.commands.tourelle.FindTargetAndLock;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandeLigneDroite extends CommandGroup {

public CommandeLigneDroite() {
	// public CommandeConduiteDistance(double distance, double driveP, double driveI)
	// public CommandeConduiteTourner(double angle, double P, double I)
	addSequential(new CommandeConduiteDistance(SmartDashboard.getNumber("DISTANCE"), SmartDashboard.getNumber("P"), SmartDashboard.getNumber("I")));
	
}

}
