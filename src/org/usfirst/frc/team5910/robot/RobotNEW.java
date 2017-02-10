package org.usfirst.frc.team5910.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class RobotNEW extends IterativeRobot {
	VictorSP FLwheel = new VictorSP(0); // Front left
	VictorSP RLwheel = new VictorSP(1); // Rear Left
	VictorSP FRwheel = new VictorSP(2); // Front right
	VictorSP RRwheel = new VictorSP(3); // Rear right
	VictorSP climber = new VictorSP(4);
	
	/* int leftFrontPolarity = -1; // These variables flip the sign value of
     int leftBackPolarity = -1; // the motors in the situation that they are
     int rightFrontPolarity = 1; // flipped
     int rightBackPolarity = 1;*/
     
     
	CANTalon shooter = new CANTalon(0);
	
	//RobotDrive myRobot = new RobotDrive(FLwheel, RLwheel, FRwheel, RRwheel);
	Joystick stick = new Joystick(0);

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		/*SmartDashboard.putNumber("driveCalib", 0.0);
		SmartDashboard.putNumber("x", 0.0);
		SmartDashboard.putNumber("y", 0.0);
		SmartDashboard.putNumber("twist", 0.0);
		
		SmartDashboard.putNumber("P", 0.3);
		SmartDashboard.putNumber("I", 0.0);
		SmartDashboard.putNumber("D", 0.0);
		
		SmartDashboard.putNumber("error", 0.0);
		SmartDashboard.putNumber("command", 0.0);
		
		SmartDashboard.putNumber("shooterSpeetSET", 0.0);
		SmartDashboard.putNumber("shooterVelocityMES", 0.0);
		
		SmartDashboard.putNumber("testDrive", 0.0);*/
		
		SmartDashboard.putNumber("x1", 0.0);
		SmartDashboard.putNumber("y1", 0.0);
		SmartDashboard.putNumber("x2", 0.0);
		SmartDashboard.putNumber("y2", 0.0);
		
		SmartDashboard.putNumber("FL", 0.0);
		SmartDashboard.putNumber("RL", 0.0);
		// NORMAL DRIVE
		FLwheel.setInverted(true);
		RLwheel.setInverted(true);
		
		// NEW DRIVE
		//FRwheel.setInverted(true);
		//RRwheel.setInverted(true);
		
		//shooter.changeControlMode(TalonControlMode.Current);
		//shooter.changeControlMode(TalonControlMode.Voltage);
		
		shooter.changeControlMode(TalonControlMode.Speed);
		shooter.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooter.reverseSensor(true);
		shooter.configEncoderCodesPerRev(1024);
		shooter.configNominalOutputVoltage(+0.0f, -0.0f);
		shooter.configPeakOutputVoltage(+12.0f, -12.0f);
		shooter.setProfile(0);
		shooter.setF(0.1097);
		shooter.setP(0.22);
		shooter.setI(0); 
		shooter.setD(0);
		shooter.enable();

	}

	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	@Override
	public void autonomousInit() {
	
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
	}

	/**
	 * This function is called once each time the robot enters tele-operated
	 * mode
	 */
	@Override
	public void teleopInit() {
	}


	public static double clamp(double val, double min, double max) 
	{
	    return Math.max(min, Math.min(max, val));
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		/*if (stick.getRawButton(6))
		{
			climber.set(1.0f);
		}
		else
		{
			climber.set(0.0f);
		}*/
		
		double x1 = 0;
		double y1 = 0;
		double x2 = 0;
		double y2 = 0;
		
		if (Math.abs(stick.getRawAxis(0)) > .2)
			x1 = stick.getRawAxis(0);
       
        if (Math.abs(stick.getRawAxis(1)) > .2)
        	y1 = -stick.getRawAxis(1);
       
        if (Math.abs(stick.getRawAxis(4)) > .2)
            x2 = stick.getRawAxis(4);
        
        if (Math.abs(stick.getRawAxis(5)) > .2)
            y2 = -stick.getRawAxis(5);
        
        double x = (x1 + x2)/ 2;
        
        FLwheel.set(clamp(x + y1, -1, 1));
        FRwheel.set(clamp(y2 - x, -1, 1));
     	RLwheel.set(clamp(y1 - x, -1, 1));
     	RRwheel.set(clamp(x + y2, -1, 1));
        
		
		/*if (stick.getRawButton(4)) // Y
		{
			FLwheel.set(0.5);
            FRwheel.set(1);
         	RLwheel.set(1);
         	RRwheel.set(0.5);
		}
		else if (stick.getRawButton(2)) // B
		{
			FLwheel.set(0);
            FRwheel.set(1);
         	RLwheel.set(1);
         	RRwheel.set(0);
		}
		else if (stick.getRawButton(1)) // A
		{
			FLwheel.set(-0.5);
            FRwheel.set(1);
         	RLwheel.set(1);
         	RRwheel.set(-0.5);	
		}
		else if (stick.getRawButton(6)) // trigger
		{
			FLwheel.set(1);
            FRwheel.set(1);
         	RLwheel.set(1);
         	RRwheel.set(1);	
		}
		else
		{
			FLwheel.set(0);
            FRwheel.set(0);
         	RLwheel.set(0);
         	RRwheel.set(0);
		}*/
		/* Simple drive
        SmartDashboard.putNumber("Z", stick.getRawAxis(0));
		SmartDashboard.putNumber("X", stick.getRawAxis(4));
		SmartDashboard.putNumber("Y", stick.getRawAxis(1));
		double x = 0;
		double y = 0;
		double twist = 0;
	
         if (Math.abs(stick.getRawAxis(0)) > .2)
             twist = stick.getRawAxis(0); // z-axis threshold
        
         if (Math.abs(stick.getRawAxis(1)) > .2)
             y = (stick.getRawAxis(1)); // y-axis threshold
        
         if (Math.abs(stick.getRawAxis(4)) > .2)
             x = stick.getRawAxis(4); // x-axis threshold
         
         myRobot.mecanumDrive_Cartesian(x,y,twist,0);
         */
		
		
		/* NEW DRIVE */
		/*double tankLeft = 0;
		double tankRight = 0;
		double slide = 0;
		
		if (Math.abs(stick.getRawAxis(1)) > .2)
			tankLeft = stick.getRawAxis(1) * -1; 
       
        if (Math.abs(stick.getRawAxis(5)) > .2)
        	tankRight = stick.getRawAxis(5) * -1;
       
        if (Math.abs(stick.getRawAxis(0)) > .2)
        	slide = stick.getRawAxis(0);
		
        
        // y-axis motion
        if (Math.abs(slide) > Math.abs(tankLeft) || Math.abs(slide) > Math.abs(tankRight))
        {
        	FLwheel.set(slide);
        	FRwheel.set(slide* -1);
        	RLwheel.set(slide* -1);
        	RRwheel.set(slide);
        	//Negative FR et RL va a droite
        	//Negative RR et FL va a gauche
        }
        else
        {
      	
        	FLwheel.set(tankLeft);
            FRwheel.set(tankRight);
         	RLwheel.set(tankLeft);
         	RRwheel.set(tankRight);
        }
        */
     	
     	/* END NEW DRIVE */
     	
		/*double x = stick.getRawAxis(4);
		double y = stick.getRawAxis(1);
		double twist = stick.getRawAxis(0);*/
		
		//double deadzone = 0.5;
		/*double x = 0; // x-axis motion-right (+), left (-)
        double y = 0; // y-axis motion-forward (+), backward (-)
        double z = 0; // z-axis motion-clockwise (+), counterclockwise (-)*/
         
		
		
         // y-axis motion
         /*if (Math.abs(y) > Math.abs(x) && Math.abs(y) > Math.abs(z))  //Activates if y is largest
         {
        	FLwheel.set(y * leftFrontPolarity);
         	FRwheel.set(y * rightFrontPolarity);
         	RLwheel.set(y * leftBackPolarity);
         	RRwheel.set(y * rightBackPolarity);
         }
         // x-axis motion
         else if (Math.abs(x) > Math.abs(y) && Math.abs(x) > Math.abs(z)) //Activates if x is largest
         {
        	 if (x > 0)
        	 {
        		 if (Math.abs(x) >= 0.5)
        		 {
        			 FLwheel.set(x * leftFrontPolarity * 1.1);
        	         FRwheel.set(x * rightFrontPolarity * -0.95);
        	         RLwheel.set(x * leftBackPolarity * -1.1);
        	         RRwheel.set(x * rightBackPolarity);
        		 }
        		 else if (Math.abs(x) >= 0.35)
        		 {
        			 FLwheel.set(x * leftFrontPolarity * 0.9);
        	         FRwheel.set(x * rightFrontPolarity * -0.9);
        	         RLwheel.set(x * leftBackPolarity * -1.1);
        	         RRwheel.set(x * rightBackPolarity);
        		 }
        	 }
        	 else if (x < 0)
        	 {
        		 if (Math.abs(x) >= 0.5)
        		 {
        			 FLwheel.set(x * leftFrontPolarity * 1.05);
        	         FRwheel.set(x * rightFrontPolarity * -1);
        	         RLwheel.set(x * leftBackPolarity * -1);
        	         RRwheel.set(x * rightBackPolarity);
        		 }
        		 else if (Math.abs(x) >= 0.35)
        		 {
        			 FLwheel.set(x * leftFrontPolarity * 1.1);
        	         FRwheel.set(x * rightFrontPolarity * -1);
        	         RLwheel.set(x * leftBackPolarity * -1.1);
        	         RRwheel.set(x * rightBackPolarity);
        		 }
        	 }
        	
         }

         // z-axis motion
         else if (Math.abs(z) > Math.abs(y) && Math.abs(z) > Math.abs(x)) {
        	 FLwheel.set(z * leftFrontPolarity);
        	 FRwheel.set(z * rightFrontPolarity * -1);
        	 RLwheel.set(z * leftBackPolarity);
        	 RRwheel.set(z * rightBackPolarity * -1);
         }
         // Otherwise sticks are not pushed
         else {
        	 FLwheel.set(0);
        	 FRwheel.set(0);
        	 RLwheel.set(0);
        	 RRwheel.set(0);
         }*/
         
        /*SmartDashboard.putNumber("FLwheel", FLwheel.get());
 		SmartDashboard.putNumber("FRwheel", FRwheel.get());
 		SmartDashboard.putNumber("RLwheel", RLwheel.get());
 		SmartDashboard.putNumber("RRwheel", RRwheel.get());*/
 		
 		/* FLwheel.set(SmartDashboard.getNumber("testDrive", 0.0));
    	 FRwheel.set(SmartDashboard.getNumber("testDrive", 0.0));
    	 RLwheel.set(SmartDashboard.getNumber("testDrive", 0.0));
    	 RRwheel.set(SmartDashboard.getNumber("testDrive", 0.0));*/
	
		//Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
		
		/*shooter.set(SmartDashboard.getNumber("shooterSpeetSET", 0.0));
		shooter.setPID(SmartDashboard.getNumber("P", 0.0), SmartDashboard.getNumber("I", 0.0), SmartDashboard.getNumber("D", 0.0));
		
		SmartDashboard.putNumber("shooterVelocityMES", shooter.getSpeed());
		
		SmartDashboard.putNumber("error", shooter.getClosedLoopError());
		SmartDashboard.putNumber("command", shooter.getOutputVoltage());*/
		
		

		
		/* if(Math.abs(y) > deadzone || Math.abs(x) > deadzone)
		    {
			 	FRwheel.set((y - x)/2);
			 	FLwheel.set((-y - x)/2);
			 	RRwheel.set((-y - x)/2);
			 	RLwheel.set((y - x)/2);
		    }
		    if(Math.abs(twist) > deadzone)
		    {
		        //rotate
		    	FRwheel.set((-twist)/2);
		    	FLwheel.set((-twist)/2);
		    	RRwheel.set((twist)/2);
		    	RLwheel.set((twist)/2);
		    }*/
		    
		/*if ((x < .05) && (x > -.05))
		{
			x = 0.0f;
		}
		if ((y < .05) && (y > -.05))
		{
			y = 0.0f;
		}
		if ((twist < .05) && (twist > -.05))
		{
			twist = 0.0f;
		}*/
		/*SmartDashboard.putNumber("x", x);
		SmartDashboard.putNumber("y", y);
		SmartDashboard.putNumber("twist", twist);*/
	
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
