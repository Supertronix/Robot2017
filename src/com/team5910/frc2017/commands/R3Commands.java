package com.team5910.frc2017.commands;

import com.team5910.frc2017.commands.drive.DriveDistance;
import com.team5910.frc2017.commands.drive.DriveDistancePrecision;
import com.team5910.frc2017.commands.drive.DriveRotate;
import com.team5910.frc2017.commands.drive.LateralDriveDistance;
import com.team5910.frc2017.commands.lanceur.ShooterMotorStart;
import com.team5910.frc2017.commands.lanceur.SpinUpShooter;
import com.team5910.frc2017.commands.tourelle.FindTargetAndLock;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class R3Commands extends CommandGroup {

public R3Commands() {
	
	addSequential(new DriveDistance(6.6, 0.09, 0.00045));
	//addSequential(new DriveDistance(-2, 0.18, 0.00045));
	//addSequential(new DriveRotate(-90, 0.008, 0.0002));
}

}
