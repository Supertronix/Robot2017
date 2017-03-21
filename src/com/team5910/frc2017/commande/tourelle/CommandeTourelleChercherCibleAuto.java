package com.team5910.frc2017.commande.tourelle;

import com.team5910.frc2017.robot.Robot;
import com.team5910.frc2017.robot.RobotControleur;
import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.soussysteme.Tourelle;
import com.team5910.frc2017.robot.soussysteme.Tourelle.SystemState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandeTourelleChercherCibleAuto extends Command {
	
	public CommandeTourelleChercherCibleAuto() {
		requires(RobotControleur.robot.tourelle);
	 }
	 
	 @Override
	protected void initialize() {
		RobotControleur.robot.tourelle.setAutoState();
		if(!RobotControleur.robot.tourelle.visionData.trouvee && RobotControleur.robot.tourelle.visionData.positionX <= 0.1)
			 RobotControleur.robot.tourelle.gotoPanOppositeSP();
	}
	 
	 @Override
	protected void execute() {
		if(RobotControleur.robot.tourelle.visionData.trouvee && Math.abs(RobotControleur.robot.tourelle.visionData.positionX) <= 0.1) return;
		
		if (RobotControleur.robot.tourelle.panSPdone())
		{
			RobotControleur.robot.tourelle.gotoPanOppositeSP();
		}
		
	}
	 
	@Override
	protected boolean isFinished() {
		if(RobotControleur.robot.tourelle.visionData.trouvee && Math.abs(RobotControleur.robot.tourelle.visionData.positionX) <= 0.1)
		{
			SmartDashboard.putString("AUTOSTATE", "FINISH");
			RobotControleur.robot.tourelle.setState(SystemState.AUTO_LOCK);
			return true;
		}
		else
		{
			return false;
		}
	}

	

}
