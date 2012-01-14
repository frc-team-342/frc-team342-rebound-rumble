package org.first.team342;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.first.team342.commands.thrower.FlyWheelForwardCommand;
import org.first.team342.commands.thrower.FlyWheelReverseCommand;
import org.first.team342.commands.thrower.FlyWheelStopCommand;

public class OI {

    private Joystick driveController;
    
    private static final OI INSTANCE = new OI();

    private OI() {
        this.driveController = new Joystick(RobotMap.JOYSTICK_DRIVE_CONTROL);
        
        JoystickButton throwerForward = new JoystickButton(driveController, 1);
        JoystickButton throwerReverse = new JoystickButton(driveController, 2);
        
        throwerForward.whileHeld(new FlyWheelForwardCommand());
        throwerForward.whenReleased(new FlyWheelStopCommand());
        
        throwerReverse.whileHeld(new FlyWheelReverseCommand());
        throwerReverse.whenReleased(new FlyWheelStopCommand());
        
    }

    public static OI getInstance() {
        return INSTANCE;
    }

    public Joystick getDriveController() {
        return driveController;
    }
}
