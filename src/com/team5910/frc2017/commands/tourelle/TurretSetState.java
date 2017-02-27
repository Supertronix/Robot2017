package com.team5910.frc2017.commands.tourelle;

import com.team5910.frc2017.robot.Robot;
import com.team5910.frc2017.robot.subsystems.Tourelle;

import edu.wpi.first.wpilibj.command.Command;

public class TurretSetState extends Command {

	Tourelle.SystemState state;
	public TurretSetState(Tourelle.SystemState aState) {
		requires(Robot.superstructure.tourelle);
		state = aState;
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.tourelle.setState(state);
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
