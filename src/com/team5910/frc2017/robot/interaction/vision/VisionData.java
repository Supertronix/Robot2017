package com.team5910.frc2017.robot.interaction.vision;

public class VisionData {
	boolean trouvee;
	double positionX;
	double distanceAvecRobot;
	
	long whenRecieved;
	/*Boolean seen;
	Double x, y, xlimit, xLimit, missing;*/

	@Override
	public String toString() {
		return ""; /*"VisionData [imageHeight=" + imageHeight + ", imageWidth="
				+ imageWidth + ", seen=" + seen + ", x=" + x + ", y=" + y
				+ ", xlimit=" + xlimit + ", xLimit=" + xLimit
				+ ", missing=" + missing + ", when=" + when + "]";*/
	}

	/*public double getImageWidth() {
		return imageWidth;
	}

	public long getWhenRecieved() {
		return whenRecieved;
	}

	public double getX() {
		return x;
	}
	
	public boolean isSeen()
	{
		return seen;
	}*/
}
