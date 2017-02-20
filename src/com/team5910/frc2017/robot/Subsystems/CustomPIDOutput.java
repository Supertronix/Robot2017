package com.team5910.frc2017.robot.Subsystems;

import edu.wpi.first.wpilibj.PIDOutput;

public class CustomPIDOutput implements PIDOutput {

	double distancePIDOut;
	
	@Override
	public void pidWrite(double output) {
		distancePIDOut = output;
	}
	
	public double getPIDOut() {
		return distancePIDOut;
	}
	
	
}
