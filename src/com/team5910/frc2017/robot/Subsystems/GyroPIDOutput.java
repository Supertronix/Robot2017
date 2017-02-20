package com.team5910.frc2017.robot.Subsystems;

import edu.wpi.first.wpilibj.PIDOutput;

public class GyroPIDOutput implements PIDOutput {

	double gyroPIDOut;
	
	@Override
	public void pidWrite(double output) {
		gyroPIDOut = output;
	}
	
	public double getPIDOut() {
		return gyroPIDOut;
	}
	
	
}
