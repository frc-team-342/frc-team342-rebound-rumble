
package org.first.team342;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
    private static Joystick driveController = new Joystick(RobotMap.JOYSTICK_DRIVE_CONTROL);
    private static OI instance = new OI();
    // Process operator interface input here.
    private OI(){
    }
    public static OI getInstance(){
        return instance;
    }
    public Joystick getDriveController(){
        return driveController;
    }
}

