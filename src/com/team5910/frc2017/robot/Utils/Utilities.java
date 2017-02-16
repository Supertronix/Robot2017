package com.team5910.frc2017.robot.Utils;

public class Utilities {
	public static double clamp(double val, double min, double max) 
	{
	    return Math.max(min, Math.min(max, val));
	}

}
