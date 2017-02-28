package com.team5910.frc2017.robot.subsystems;

import edu.wpi.first.wpilibj.PIDOutput;

public class ObserveurGyro implements PIDOutput {

	double gyroPIDOut;
	
	@Override
	public void pidWrite(double output) {
		gyroPIDOut = output;
	}
	
	public double getPIDOut() {
		return gyroPIDOut;
	}
	
	
}