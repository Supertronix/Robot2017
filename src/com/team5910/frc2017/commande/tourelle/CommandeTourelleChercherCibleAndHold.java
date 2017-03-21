package com.team5910.frc2017.commande.tourelle;

import com.team5910.frc2017.robot.Robot;
import com.team5910.frc2017.robot.RobotControleur;
import com.team5910.frc2017.robot.RobotMap;
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
		if(!RobotControleur.robot.tourelle.visionData.trouvee && RobotControleur.robot.tourelle.visionData.positionX <= 0.1)
			 RobotControleur.robot.tourelle.gotoPanOppositeSP();
	}
	 
	 @Override
	protected void execute() {

		if(RobotControleur.robot.tourelle.visionData.trouvee && Math.abs(RobotControleur.robot.tourelle.visionData.positionX) <= 0.1 && !isHolding)
		{
			isHolding = true;
			startDetectedTime = Utility.getFPGATime();
			SmartDashboard.putString("AUTOSTATE", "EXECUTE START HOLDING");
			return;
		}
		else if (RobotControleur.robot.tourelle.visionData.trouvee && Math.abs(RobotControleur.robot.tourelle.visionData.positionX) <= 0.1 && isHolding)
		{
			SmartDashboard.putString("AUTOSTATE", "EXECUTE IS HOLDING");
			return;
		}
		else if (RobotControleur.robot.tourelle.visionData.trouvee && Math.abs(RobotControleur.robot.tourelle.visionData.positionX) > 0.1 && isHolding)
		{
			SmartDashboard.putString("AUTOSTATE", "EXECUTE STOP HOLDING");
			isHolding = false;
			startDetectedTime = Double.MAX_VALUE;
			return;
		}
			
		SmartDashboard.putString("AUTOSTATE", "SCANNING");
		if (RobotControleur.robot.tourelle.panSPdone())
		{
			RobotControleur.robot.tourelle.gotoPanOppositeSP();
		}
		
	}
	 
	@Override
	protected boolean isFinished() {
		if(RobotControleur.robot.tourelle.visionData.trouvee && Math.abs(RobotControleur.robot.tourelle.visionData.positionX) <= 0.1 && (Utility.getFPGATime() >= startDetectedTime + dureeMicroseconde))
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
