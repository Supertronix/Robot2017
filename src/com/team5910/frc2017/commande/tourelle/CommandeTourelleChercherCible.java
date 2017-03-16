package com.team5910.frc2017.commande.tourelle;

import com.team5910.frc2017.robot.Robot;
import com.team5910.frc2017.robot.RobotControleur;
import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.soussysteme.Tourelle;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeTourelleChercherCible extends Command {

	public CommandeTourelleChercherCible() {
		requires(RobotControleur.robot.tourelle);
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.tourelle.gotoPanOppositeSP();
	}
	 
	 @Override
	protected void execute() {
		if (RobotControleur.robot.tourelle.panSPdone())
		{
			RobotControleur.robot.tourelle.gotoPanOppositeSP();
		}
	}
	 
	@Override
	protected boolean isFinished() {
		return false;
	}

	

}
