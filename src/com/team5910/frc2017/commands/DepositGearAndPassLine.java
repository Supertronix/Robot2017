package com.team5910.frc2017.commands;

import com.team5910.frc2017.commands.Drive.DriveDistance;
import com.team5910.frc2017.commands.Drive.DriveRotate;
import com.team5910.frc2017.commands.Drive.LateralDriveDistance;
import com.team5910.frc2017.commands.Shooter.ShooterMotorStart;
import com.team5910.frc2017.commands.Shooter.SpinUpShooter;
import com.team5910.frc2017.commands.Turret.FindTargetAndLock;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DepositGearAndPassLine extends CommandGroup {

public DepositGearAndPassLine() {
	//addParallel(new FindTargetAndLock()); // Turret will try to detect target and will lock
	//addSequential(new DriveDistance(3)); // Drive forward 5 feet
	//addSequential(new DriveDistance(10)); // Drive forward 5 feet
	//addSequential(new DriveRotate(180)); // Drive left 1 feet
	//addSequential(new DriveDistance(10)); // Drive forward 5 feet
	//addSequential(new DriveDistance(-90)); // Drive forward 5 feet
	//addSequential(new DriveRotate(-90)); // Drive left 1 feet
	//addSequential(new DriveRotate(360)); // Drive left 1 feet
	//addSequential(new DriveDistance(6)); // Drive forward 5 feet
	//addSequential(new DriveDistance(-5)); // Drive forward 5 feet
	//addSequential(new DriveDistance(-0.3, 5)); // Drive forward 5 feet speed 0.5
	//addSequential(new DriveDistance(0.3, -5)); // Drive forward 5 feet speed 0.5
	/*addSequential(new DriveDistance(0.5, -1)); // Drive backward 1 feet speed 0.5
	addSequential(new LateralDriveDistance(0.5, 5)); // Righ lateral drive 5 feet speed 0.5 
	addParallel(new SpinUpShooter()); // Pre-start the shooter so it take its full speed
	addSequential(new DriveDistance(0.5, 5)); // Drive forward 5 feet speed 0.5
	addSequential(new ShooterMotorStart()); // Shoot*/
	
}

}
