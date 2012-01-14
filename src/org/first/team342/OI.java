package org.first.team342;

import edu.wpi.first.wpilibj.Joystick;

public class OI {

    private Joystick driveController;
    
    private static final OI INSTANCE = new OI();

    private OI() {
        this.driveController = new Joystick(RobotMap.JOYSTICK_DRIVE_CONTROL);
    }

    public static OI getInstance() {
        return INSTANCE;
    }

    public Joystick getDriveController() {
        return driveController;
    }
}
