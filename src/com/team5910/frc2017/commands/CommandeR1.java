package com.team5910.frc2017.commands;

import com.team5910.frc2017.commands.drive.CommandeConduiteDistance;
import com.team5910.frc2017.commands.drive.CommandeConduiteDistancePrecision;
import com.team5910.frc2017.commands.drive.CommandeConduiteTourner;
import com.team5910.frc2017.commands.drive.CommandeConduiteLaterale;
import com.team5910.frc2017.commands.lanceur.ShooterMotorStart;
import com.team5910.frc2017.commands.lanceur.SpinUpShooter;
import com.team5910.frc2017.commands.tourelle.FindTargetAndLock;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeR1 extends CommandGroup {

public CommandeR1() {
	addSequential(new CommandeConduiteDistance(5.5, 0.08, 0.00058));
	addSequential(new CommandeConduiteTourner(-60, 0.008, 0.0002));
	addSequential(new CommandeConduiteDistance(6.5, 0.08, 0.00045));
	addSequential(new CommandeConduiteDistance(-2, 0.18, 0.00045));
	addSequential(new CommandeConduiteTourner(150, 0.005, 0.00011));
	addSequential(new CommandeConduiteDistance(6.5, 0.08, 0.00058));
	
}

}
