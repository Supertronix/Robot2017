package com.team5910.frc2017.commands;

import com.team5910.frc2017.commands.drive.CommandeConduiteDistance;
import com.team5910.frc2017.commands.drive.CommandeConduiteDistancePrecision;
import com.team5910.frc2017.commands.drive.CommandeConduiteTourner;
import com.team5910.frc2017.commands.drive.CommandeConduiteLaterale;
import com.team5910.frc2017.commands.lanceur.ShooterMotorStart;
import com.team5910.frc2017.commands.lanceur.SpinUpShooter;
import com.team5910.frc2017.commands.tourelle.FindTargetAndLock;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeR2 extends CommandGroup {

public CommandeR2() {
	//addParallel(new ShooterMotorStart());
	addSequential(new CommandeConduiteDistance(6.6, 0.09, 0.00045));
	addSequential(new CommandeConduiteDistance(-2, 0.18, 0.00045));
	addSequential(new CommandeConduiteTourner(-90, 0.008, 0.0002));
}

}
