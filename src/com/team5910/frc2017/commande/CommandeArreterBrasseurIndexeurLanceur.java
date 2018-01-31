package com.team5910.frc2017.commande;

import com.team5910.frc2017.robot.RobotControleur;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeArreterBrasseurIndexeurLanceur extends Command {

	public CommandeArreterBrasseurIndexeurLanceur() {
		requires(RobotControleur.robot.brasseur);
		requires(RobotControleur.robot.indexeur);
		requires(RobotControleur.robot.lanceur);
		 
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.brasseur.stop();
		 RobotControleur.robot.indexeur.stop();
		 RobotControleur.robot.lanceur.arreter();
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
