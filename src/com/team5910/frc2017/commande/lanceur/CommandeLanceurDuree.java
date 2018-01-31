package com.team5910.frc2017.commande.lanceur;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class CommandeLanceurDuree extends Command {

	private double startTime;
	private double dureeMicroSecondes;
	
	public CommandeLanceurDuree(double dureeSecondes) {
		requires(RobotControleur.robot.lanceur);
		this.dureeMicroSecondes = dureeSecondes * 1000000;
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.lanceur.lancer();
		 startTime = Utility.getFPGATime();
	}
	 
	@Override
	protected boolean isFinished() {
		if (Utility.getFPGATime() >= startTime + dureeMicroSecondes)
		{
			RobotControleur.robot.lanceur.arreter();
			return true;
		}
		else
		{
			return false;
		}
		 
	}

}
