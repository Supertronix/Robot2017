package com.team5910.frc2017.robot.interaction;

import com.team5910.frc2017.robot.trajet.CommandeB1;
import com.team5910.frc2017.robot.trajet.CommandeB2;
import com.team5910.frc2017.robot.trajet.CommandeB3;
import com.team5910.frc2017.robot.trajet.CommandeImmobile;
import com.team5910.frc2017.robot.trajet.CommandeR1;
import com.team5910.frc2017.robot.trajet.CommandeR2;
import com.team5910.frc2017.robot.trajet.CommandeR3;
import com.team5910.frc2017.robot.trajet.CommandeWTF;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SelecteurModeAutonome {

    SendableChooser selecteur;
    
    public SelecteurModeAutonome()
    {
		// http://wpilib.screenstepslive.com/s/3120/m/7932/l/81109-choosing-an-autonomous-program-from-smartdashboard
		selecteur = new SendableChooser();
		selecteur.addDefault("No move", new CommandeImmobile());
		selecteur.addObject("R1", new CommandeR1());
		selecteur.addObject("R2", new CommandeR2());
		selecteur.addObject("R3", new CommandeR3());	
		selecteur.addObject("B1", new CommandeB1());
		selecteur.addObject("B2", new CommandeB2());
		selecteur.addObject("B3", new CommandeB3());	
		selecteur.addObject("WTF",new CommandeWTF());
		SmartDashboard.putData("Autonomous mode chooser", selecteur);    	
    }

}
