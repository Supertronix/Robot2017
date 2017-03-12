package com.team5910.frc2017.robot.soussysteme;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.team5910.frc2017.robot.RobotMap;
import com.team5910.frc2017.robot.outils.Calculateur;

import edu.wpi.first.wpilibj.command.Subsystem;


public class Lanceur extends Subsystem {
	
	CANTalon ShooterDriveMain = new CANTalon(RobotMap.LANCEUR_MOTEUR_PRINCIPAL);
	CANTalon ShooterDriveSlave = new CANTalon(RobotMap.LANCEUR_MOTEUR_ESCLAVE);
	
	double shooterSpeed = 1.0;
	
	public Lanceur() {
		ShooterDriveSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		ShooterDriveSlave.set(ShooterDriveMain.getDeviceID());
		
		/*ShooterDriveMain.changeControlMode(TalonControlMode.Speed);
		ShooterDriveMain.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		ShooterDriveMain.reverseSensor(true);
		ShooterDriveMain.configEncoderCodesPerRev(1024);
		ShooterDriveMain.configNominalOutputVoltage(+0.0f, -0.0f);
		ShooterDriveMain.configPeakOutputVoltage(+12.0f, -12.0f);
		ShooterDriveMain.setProfile(0);
		ShooterDriveMain.setF(0.1097);
		ShooterDriveMain.setP(0.22);
		ShooterDriveMain.setI(0); 
		ShooterDriveMain.setD(0);
		ShooterDriveMain.enable();*/			
	}

    public void initDefaultCommand() {
    }
    
    public void shoot() {
    	ShooterDriveMain.set(shooterSpeed);
    }

	public void stop() {
		ShooterDriveMain.set(0);
	}
	public void toggle() {
		if (ShooterDriveMain.get() > 0)
			ShooterDriveMain.set(0);
		else
			ShooterDriveMain.set(shooterSpeed);
	}
	
	public void incDecSpeed(double aChangeValue) {
		shooterSpeed = Calculateur.clamp(shooterSpeed + aChangeValue, 0.0, 1.0);
		
		if (ShooterDriveMain.get() > 0)
			ShooterDriveMain.set(shooterSpeed);
	}
	
}

