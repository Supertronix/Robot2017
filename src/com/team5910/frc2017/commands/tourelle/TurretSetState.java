package com.team5910.frc2017.commands.tourelle;

import com.team5910.frc2017.robot.RobotControleur;
import com.team5910.frc2017.robot.subsystems.Tourelle;

import edu.wpi.first.wpilibj.command.Command;

public class TurretSetState extends Command {

	Tourelle.SystemState state;
	public TurretSetState(Tourelle.SystemState aState) {
		requires(RobotControleur.superstructure.tourelle);
		state = aState;
	 }
	 
	 @Override
	protected void initialize() {
		 RobotControleur.superstructure.tourelle.setState(state);
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
