package org.first.team342;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.first.team342.commands.conveyor.ConveyorOffCommand;
import org.first.team342.commands.conveyor.ConveyorReverseCommand;
import org.first.team342.commands.conveyor.ConveyorToggleCommand;
import org.first.team342.commands.drive.DriveWithJoystick;
import org.first.team342.commands.drive.GyroBalanceCommand;
import org.first.team342.commands.ramp.RampDownCommand;
import org.first.team342.commands.ramp.RampUpCommand;
import org.first.team342.commands.thrower.FlyWheelForwardCommand;
import org.first.team342.commands.thrower.FlyWheelStopCommand;

public class OI {

    private Joystick driveController;
    
    private static final OI INSTANCE = new OI();

    private OI() {
        this.driveController = new Joystick(RobotMap.JOYSTICK_DRIVE_CONTROL);
        
        JoystickButton fire = new JoystickButton(driveController, 6);
        JoystickButton conveyorToggle = new JoystickButton(driveController, 3);
        JoystickButton conveyorReverse = new JoystickButton(driveController, 1);
        JoystickButton balance = new JoystickButton(driveController, 10);
        JoystickButton ramp = new JoystickButton(driveController, 4);
        
        fire.whileHeld(new FlyWheelForwardCommand());
        fire.whenReleased(new FlyWheelStopCommand());
        
        ramp.whileHeld(new RampDownCommand());
        ramp.whenReleased(new RampUpCommand());
        
        conveyorToggle.whenPressed(new ConveyorToggleCommand());
        
        conveyorReverse.whileHeld(new ConveyorReverseCommand());
        conveyorReverse.whenReleased(new ConveyorOffCommand());
        
        balance.whileHeld(new GyroBalanceCommand());
        balance.whenReleased(new DriveWithJoystick());
    }

    public static OI getInstance() {
        return INSTANCE;
    }

    public Joystick getDriveController() {
        return driveController;
    }
}
