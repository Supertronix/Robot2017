package com.team5910.frc2017.commande.tourelle;

import com.team5910.frc2017.robot.Robot;
import com.team5910.frc2017.robot.RobotControleur;
import com.team5910.frc2017.robot.RobotMap.Vision;
import com.team5910.frc2017.robot.soussysteme.Tourelle;
import com.team5910.frc2017.robot.soussysteme.Tourelle.SystemState;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandeTourelleChercherCibleAndHold extends Command {
	
	double dureeMicroseconde;
	double startDetectedTime = Double.MAX_VALUE;
	boolean isHolding = false;
	
	public CommandeTourelleChercherCibleAndHold(double dureeSeconde) {
		requires(RobotControleur.robot.tourelle);
		dureeMicroseconde = dureeSeconde * 1000000;
	 }
	 
	 @Override
	protected void initialize() {
		RobotControleur.robot.tourelle.setAutoState();
		if(!RobotControleur.robot.tourelle.visionData.trouvee && RobotControleur.robot.tourelle.visionData.positionX <= Vision.VISION_THS)
			 RobotControleur.robot.tourelle.gotoPanOppositeSP();
	}
	 
	 @Override
	protected void execute() {

		if(RobotControleur.robot.tourelle.visionData.trouvee && Math.abs(RobotControleur.robot.tourelle.visionData.positionX) <= Vision.VISION_THS && !isHolding)
		{
			isHolding = true;
			startDetectedTime = Utility.getFPGATime();
			return;
		}
		else if (RobotControleur.robot.tourelle.visionData.trouvee && Math.abs(RobotControleur.robot.tourelle.visionData.positionX) <= Vision.VISION_THS && isHolding)
		{
			return;
		}
		else if (RobotControleur.robot.tourelle.visionData.trouvee && Math.abs(RobotControleur.robot.tourelle.visionData.positionX) > Vision.VISION_THS && isHolding)
		{
			isHolding = false;
			startDetectedTime = Double.MAX_VALUE;
			return;
		}
			
		if (RobotControleur.robot.tourelle.panSPdone())
		{
			RobotControleur.robot.tourelle.gotoPanOppositeSP();
		}
		
	}
	 
	@Override
	protected boolean isFinished() {
		if(RobotControleur.robot.tourelle.visionData.trouvee && Math.abs(RobotControleur.robot.tourelle.visionData.positionX) <= Vision.VISION_THS && (Utility.getFPGATime() >= startDetectedTime + dureeMicroseconde))
		{
			RobotControleur.robot.tourelle.setState(SystemState.DISABLED);
			return true;

		}
		else
		{
			return false;
		}
	}

	

}
