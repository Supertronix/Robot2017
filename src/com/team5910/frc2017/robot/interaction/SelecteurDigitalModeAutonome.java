package com.team5910.frc2017.robot.interaction;

import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.trajet.CommandeImmobile;
import com.team5910.frc2017.robot.trajet.CommandeTrajetB1;
import com.team5910.frc2017.robot.trajet.CommandeTrajetB2;
import com.team5910.frc2017.robot.trajet.CommandeTrajetB3;
import com.team5910.frc2017.robot.trajet.CommandeTrajetR1;
import com.team5910.frc2017.robot.trajet.CommandeTrajetR2;
import com.team5910.frc2017.robot.trajet.CommandeTrajetR3;
import com.team5910.frc2017.robot.trajet.CommandeTrajetWTF;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SelecteurDigitalModeAutonome {

    DigitalInput interrupteur0 = new DigitalInput(RobotMap.INTERRUPTEUR_DIGITAL_0);
    DigitalInput interrupteur1 = new DigitalInput(RobotMap.INTERRUPTEUR_DIGITAL_1);
    DigitalInput interrupteur2 = new DigitalInput(RobotMap.INTERRUPTEUR_DIGITAL_2);
    DigitalInput interrupteur3 = new DigitalInput(RobotMap.INTERRUPTEUR_DIGITAL_3);

    Command commandeAutonome;
    
    public Command lireChoix()
    {
		if (interrupteur0.get() == true) //RED
		{
			if (interrupteur1.get() == true && interrupteur2.get() == true && interrupteur3.get() == true) //WTF
			{
				commandeAutonome = new CommandeTrajetWTF();
			}
			else if (interrupteur1.get() == false && interrupteur2.get() == false && interrupteur3.get() == false) // No move
			{
				commandeAutonome = new CommandeImmobile();
			}
			else if (interrupteur1.get() == true) //R1
			{
				commandeAutonome = new CommandeTrajetR1();
			}
			else if (interrupteur2.get() == true) //R2
			{
				commandeAutonome = new CommandeTrajetR2();
			}
			else if (interrupteur3.get() == true) //R3
			{
				commandeAutonome = new CommandeTrajetR3();
			}
		}
		else // Blue
		{
			if (interrupteur1.get() == true && interrupteur2.get() == true && interrupteur3.get() == true) //WTF
			{
				commandeAutonome = new CommandeTrajetWTF();
			}
			else if (interrupteur1.get() == false && interrupteur2.get() == false && interrupteur3.get() == false) // No move
			{
				commandeAutonome = new CommandeImmobile();
			}
			else if (interrupteur1.get() == true) //B1
			{
				commandeAutonome = new CommandeTrajetB1();
			}
			else if (interrupteur2.get() == true) //B2
			{
				commandeAutonome = new CommandeTrajetB2();
			}
			else if (interrupteur3.get() == true) //B3
			{
				commandeAutonome = new CommandeTrajetB3();
			}
		}
		return commandeAutonome;

    }
    
    
	public void afficherChoix() 
	{
		String trajetChoisi = "no Command selected";
		if (interrupteur0.get() == true) //Rouge
		{
			if (interrupteur1.get() == true && interrupteur2.get() == true && interrupteur3.get() == true) //WTF
			{
				trajetChoisi = " WTF";
			}
			else if (interrupteur1.get() == false && interrupteur2.get() == false && interrupteur3.get() == false) // No move
			{
				trajetChoisi = " No move";
			}
			else if (interrupteur1.get() == true)
			{
				trajetChoisi = " R1";
			}
			else if (interrupteur2.get() == true)
			{
				trajetChoisi = " R2";
			}
			else if (interrupteur3.get() == true)
			{
				trajetChoisi = " R3";
			}
		}
		else // Bleu
		{
			if (interrupteur1.get() == true && interrupteur2.get() == true && interrupteur3.get() == true) //WTF
			{
				trajetChoisi = " WTF";
			}
			else if (interrupteur1.get() == false && interrupteur2.get() == false && interrupteur3.get() == false) // No move
			{
				trajetChoisi = " No move";
			}
			else if (interrupteur1.get() == true)
			{
				trajetChoisi = " B1";
			}
			else if (interrupteur2.get() == true)
			{
				trajetChoisi = "B2";
			}
			else if (interrupteur3.get() == true)
			{
				trajetChoisi = " B3";	
			}
			
		}
		SmartDashboard.putString("TRAJET CHOISI", trajetChoisi);
		//SmartDashboard.putData("Autonomous mode chooser", selecteur);
		Timer.delay(0.2);

	}
	
}
