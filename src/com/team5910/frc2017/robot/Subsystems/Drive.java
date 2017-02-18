package com.team5910.frc2017.robot.Subsystems;

import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.Utils.ADXRS450_Supertronix;
import com.team5910.frc2017.robot.Utils.Utilities;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Subsystem {
	
    public enum DriveControlState {
        OPEN_LOOP, PATH_FOLLOWING_CONTROL
    }
    
    VictorSP FLDrive; // Front left
	VictorSP RLDrive; // Rear Left
	VictorSP FRDrive; // Front right
	VictorSP RRDrive; // Rear right
	
	ADXRS450_Supertronix gyro;
	Encoder RRWheelEncoder;
	
	private DriveControlState driveControlState_;
	
	public Drive() {
		 FLDrive = new VictorSP(RobotMap.kFLDriveOut);
		 RLDrive = new VictorSP(RobotMap.kRLDriveOut);
		 FRDrive = new VictorSP(RobotMap.kFRDriveOut);
		 RRDrive = new VictorSP(RobotMap.kRRDriveOut);
		 	
		 FLDrive.setInverted(RobotMap.kRevertFLDrive); // TRUE
		 RLDrive.setInverted(RobotMap.kRevertRLDrive); // TRUE
		 FRDrive.setInverted(RobotMap.kRevertFRDrive);
		 RRDrive.setInverted(RobotMap.kRevertRRDrive);
			
		 gyro = new ADXRS450_Supertronix();
		 RRWheelEncoder = new Encoder(RobotMap.kRRWheelEncoderA, RobotMap.kRRWheelEncoderB);
		 RRWheelEncoder.setDistancePerPulse(1);
	 }
	 
	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void manualDrive(double FLDriveSP, double FRDriveSP, double RLDriveSP, double RRDriveSP) {
		FLDrive.set(FLDriveSP);
		FRDrive.set(FRDriveSP);
	    RLDrive.set(RLDriveSP);
		RRDrive.set(RRDriveSP);
		SmartDashboard.putNumber("EncoderValue", RRWheelEncoder.getDistance());
		
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
	
	public void resetGyro() {
		gyro.reset();
	}
	
	public void resetEncoders() {
		RRWheelEncoder.reset();
	}

	public double getEncoderDistance() {
		return RRWheelEncoder.getDistance();
	}

	public void driveWithGyro(double Speed) {
		FLDrive.set(Speed);
		FRDrive.set(Speed);
	    RLDrive.set(Speed);
		RRDrive.set(Speed);
	}
}

