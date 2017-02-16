package com.team5910.frc2017.robot.Subsystems;

import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.Utils.ADXRS450_Supertronix;
import com.team5910.frc2017.robot.Utils.Utilities;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	
	private static Drive instance_ = new Drive();
	
	public static Drive getInstance() {
        return instance_;
    }
	
    public enum DriveControlState {
        OPEN_LOOP, PATH_FOLLOWING_CONTROL
    }
    
    VictorSP FLDrive; // Front left
	VictorSP RLDrive; // Rear Left
	VictorSP FRDrive; // Front right
	VictorSP RRDrive; // Rear right
	
	ADXRS450_Supertronix gyro;
	
	private DriveControlState driveControlState_;
	
	private Drive() {
		 FLDrive = new VictorSP(RobotMap.kFLDriveOut);
		 RLDrive = new VictorSP(RobotMap.kRLDriveOut);
		 FRDrive = new VictorSP(RobotMap.kFRDriveOut);
		 RRDrive = new VictorSP(RobotMap.kRRDriveOut);
		 
		 gyro = new ADXRS450_Supertronix();
	 }
	 
	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void manualDrive(double FLDriveSP, double FRDriveSP, double RLDriveSP, double RRDriveSP) {
		FLDrive.set(FLDriveSP);
		FRDrive.set(FRDriveSP);
	    RLDrive.set(RLDriveSP);
		RRDrive.set(RRDriveSP);
		
	}

	public void stop() {
		FLDrive.set(0);
		FRDrive.set(0);
	    RLDrive.set(0);
		RRDrive.set(0);
	}
	
	public void zeroSensors() {
		gyro.reset();
		
	}
}

