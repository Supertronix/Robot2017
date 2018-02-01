package com.team5910.frc2017.commande.roues;

import com.team5910.frc2017.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeConduiteLaterale extends Command {

	double mWantedDistance;

	public CommandeConduiteLaterale(double distance) {
		requires(Robot.roues);
		mWantedDistance = distance; // Distance in feet
	}
	

	@Override
	protected void initialize() {
		Robot.roues.initialiserEncodeur();
		Robot.roues.initialiserGyro();
		Robot.roues.initialiserPID();
		Robot.roues.inverserEncodeur();
		Robot.roues.programmerDistance(mWantedDistance);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.roues.conduireLateralementAvecGyro();
	}
		
	@Override
	protected boolean isFinished() {
		return Robot.roues.estArriveSelonEncodeur();
	}
	
	@Override
	protected void end() {
		Robot.roues.arreter();
		Robot.roues.restaurerEncodeur();
	}

}
