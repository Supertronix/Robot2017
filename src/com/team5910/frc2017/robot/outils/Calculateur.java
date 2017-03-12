package com.team5910.frc2017.robot.outils;

public class Calculateur {
	public static double clamp(double val, double min, double max) 
	{
	    return Math.max(min, Math.min(max, val));
	}

}
