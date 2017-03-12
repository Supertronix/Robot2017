package com.team5910.frc2017.robot.interaction.vision;

public class VisionData {
	Boolean targetSeen;
	
	Double x, y,missing;
	@Override
	public String toString() {
		/*return "VisionData [imageHeight=" + imageHeight + ", imageWidth="
				+ imageWidth + ", seen=" + seen + ", x=" + x + ", y=" + y
				+ ", xlimit=" + xlimit + ", xLimit=" + xLimit
				+ ", missing=" + missing + ", when=" + when + "]";*/
		
		return "VisionData";
	}
	
	public double getImagex() {
		return x;
		}


}
