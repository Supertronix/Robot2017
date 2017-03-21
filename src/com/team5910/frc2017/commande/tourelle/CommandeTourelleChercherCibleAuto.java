package com.team5910.frc2017.commande.tourelle;

import com.team5910.frc2017.robot.Robot;
import com.team5910.frc2017.robot.RobotControleur;
import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.soussysteme.Tourelle;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandeTourelleChercherCibleAuto extends Command {

	public CommandeTourelleChercherCibleAuto() {
		requires(RobotControleur.robot.tourelle);
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.robot.tourelle.gotoPanOppositeSP();
	}
	 
	 @Override
	protected void execute() {
		 double pan = RobotControleur.oi.getPanAxe();
		if (Math.abs(pan) > 0.2)
		{
			if (pan > 0)
				RobotControleur.robot.tourelle.setPanSetpoint(RobotMap.TOURELLE_PAN_LIMITE_MAXIMUM);
			else
				RobotControleur.robot.tourelle.setPanSetpoint(RobotMap.TOURELLE_PAN_LIMITE_MINIMUM);		
		}
		
		 
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