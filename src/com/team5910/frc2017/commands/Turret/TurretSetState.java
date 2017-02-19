package com.team5910.frc2017.commands.Turret;

import com.team5910.frc2017.robot.Robot;
import com.team5910.frc2017.robot.Subsystems.Turret;

import edu.wpi.first.wpilibj.command.Command;

public class TurretSetState extends Command {

	Turret.SystemState state;
	public TurretSetState(Turret.SystemState aState) {
		requires(Robot.superstructure.turret);
		state = aState;
	 }
	 
	 @Override
	protected void initialize() {
		 Robot.superstructure.turret.setState(state);
	}
	 
	@Override
	protected boolean isFinished() {
		return true;
	}

}
