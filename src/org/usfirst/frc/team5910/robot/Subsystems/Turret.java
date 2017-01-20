package org.usfirst.frc.team5910.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

enum state {
	INIT,
	CALIBRATION,
	MANUALCTRL,
	VISIONCTRL,
	AUTOSCAN,
	FINAL	
}
/**
 *
 */
public class Turret extends Subsystem {
private state TurretState;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

