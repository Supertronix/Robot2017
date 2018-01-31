package com.team5910.frc2017.commande;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;

public class CommandeIndexeurDuree extends Command {
	
	double dureeMicroseconde;
	double startTime;

	public CommandeIndexeurDuree(double dureeSeconde) {
		requires(RobotControleur.robot.indexeur);
		 dureeMicroseconde = dureeSeconde * 1000000;
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.indexeur.index();
		 startTime = Utility.getFPGATime();

	}
	 
	@Override
	protected boolean isFinished() {
		if(Utility.getFPGATime() >= startTime + dureeMicroseconde){
			RobotControleur.robot.indexeur.arreter();
			return true;

		}
		else{
			return false;
		}
	}

}
