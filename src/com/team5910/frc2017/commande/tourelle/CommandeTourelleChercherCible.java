package com.team5910.frc2017.commande.tourelle;

import com.team5910.frc2017.robot.Robot;
import com.team5910.frc2017.robot.RobotControleur;
import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.soussysteme.Tourelle;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandeTourelleChercherCible extends Command {

	public CommandeTourelleChercherCible() {
		requires(RobotControleur.robot.tourelle);
	 }
	 
	 @Override
	protected void initialize() {
		 if(!RobotControleur.robot.tourelle.visionData.trouvee)
			 RobotControleur.robot.tourelle.gotoPanOppositeSP();
	}
	 
	 @Override
	protected void execute() {
		if(RobotControleur.robot.tourelle.visionData.trouvee) return;
		
		double pan = RobotControleur.manette.getPanAxe();
		if (Math.abs(pan) > 0.2)
		{
			if (pan > 0)
				RobotControleur.robot.tourelle.setPanCible(RobotMap.TOURELLE_PAN_LIMITE_MAXIMUM);
			else
				RobotControleur.robot.tourelle.setPanCible(RobotMap.TOURELLE_PAN_LIMITE_MINIMUM);		
		}
		
		 SmartDashboard.putNumber("PANAXIS", RobotControleur.manette.getPanAxe());
		 
		
		if (RobotControleur.robot.tourelle.aFiniPanCible())
		{
			RobotControleur.robot.tourelle.gotoPanOppositeSP();
		}
	}
	 
	@Override
	protected boolean isFinished() {
		return false;
	}

	

}
