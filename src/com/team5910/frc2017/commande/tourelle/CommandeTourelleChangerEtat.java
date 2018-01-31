package com.team5910.frc2017.commande.tourelle;

import com.team5910.frc2017.robot.RobotControleur;
import com.team5910.frc2017.robot.soussysteme.Tourelle;

import edu.wpi.first.wpilibj.command.Command;

public class CommandeTourelleChangerEtat extends Command {

	Tourelle.EtatControle state;
	public CommandeTourelleChangerEtat(Tourelle.EtatControle aState) {
		requires(RobotControleur.robot.tourelle);
		state = aState;
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.tourelle.setEtatControle(state);
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
