package com.team5910.frc2017.robot.interaction;

import com.team5910.frc2017.robot.trajet.CommandeTrajetB1;
import com.team5910.frc2017.robot.trajet.CommandeTrajetB2;
import com.team5910.frc2017.robot.trajet.CommandeTrajetB3;
import com.team5910.frc2017.robot.trajet.CommandeImmobile;
import com.team5910.frc2017.robot.trajet.CommandeTrajetR1;
import com.team5910.frc2017.robot.trajet.CommandeTrajetR2;
import com.team5910.frc2017.robot.trajet.CommandeTrajetR3;
import com.team5910.frc2017.robot.trajet.CommandeTrajetWTF;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SelecteurModeAutonome {

    SendableChooser selecteur;
    
    public SelecteurModeAutonome()
    {
		// http://wpilib.screenstepslive.com/s/3120/m/7932/l/81109-choosing-an-autonomous-program-from-smartdashboard
		selecteur = new SendableChooser();
		selecteur.addDefault("No move", new CommandeImmobile());
		selecteur.addObject("R1", new CommandeTrajetR1());
		selecteur.addObject("R2", new CommandeTrajetR2());
		selecteur.addObject("R3", new CommandeTrajetR3());	
		selecteur.addObject("B1", new CommandeTrajetB1());
		selecteur.addObject("B2", new CommandeTrajetB2());
		selecteur.addObject("B3", new CommandeTrajetB3());	
		selecteur.addObject("WTF",new CommandeTrajetWTF());
		SmartDashboard.putData("Autonomous mode chooser", selecteur);    	
    }
    
    public Command lireChoix()
    {
    	return (Command)selecteur.getSelected();
    }


}
