package com.team5910.frc2017.commands;

import com.team5910.frc2017.commands.drive.DriveDistance;
import com.team5910.frc2017.commands.drive.DriveDistancePrecision;
import com.team5910.frc2017.commands.drive.DriveRotate;
import com.team5910.frc2017.commands.drive.LateralDriveDistance;
import com.team5910.frc2017.commands.lanceur.ShooterMotorStart;
import com.team5910.frc2017.commands.lanceur.SpinUpShooter;
import com.team5910.frc2017.commands.tourelle.FindTargetAndLock;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class R1Commands extends CommandGroup {

public R1Commands() {
	addSequential(new DriveDistance(5.5, 0.08, 0.00058));
	addSequential(new DriveRotate(60, 0.008, 0.0002));
	addSequential(new DriveDistance(6.5, 0.08, 0.00045));
	addSequential(new DriveDistance(-2, 0.18, 0.00045));
	addSequential(new DriveRotate(-150, 0.005, 0.00011));
	addSequential(new DriveDistance(6.5, 0.08, 0.00058));
	
}

}
