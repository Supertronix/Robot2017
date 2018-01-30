package com.team5910.frc2017.commande;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;

public class CommandeBrasseurDuree extends Command {
	
	double dureeMicroseconde;
	double startTime;

	public CommandeBrasseurDuree(double dureeSeconde) {
		requires(RobotControleur.robot.brasseur);
		 dureeMicroseconde = dureeSeconde * 1000000;
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.brasseur.shuffle();
		 startTime = Utility.getFPGATime();

	}
	 
	@Override
	protected boolean isFinished() {
		if(Utility.getFPGATime() >= startTime + dureeMicroseconde){
			RobotControleur.robot.brasseur.stop();
			return true;

		}
		else {
			
			return false;
		}
	}

}
