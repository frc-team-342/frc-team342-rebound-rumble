package org.first.team342;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.first.team342.commands.conveyor.ConveyorOffCommand;
import org.first.team342.commands.conveyor.ConveyorReverseCommand;
import org.first.team342.commands.conveyor.ConveyorToggleCommand;
import org.first.team342.commands.drive.DriveWithJoystick;
import org.first.team342.commands.drive.GyroBalanceCommand;
import org.first.team342.commands.elevator.MoveDownCommand;
import org.first.team342.commands.elevator.MoveUpCommand;
import org.first.team342.commands.elevator.SimpleDownCommand;
import org.first.team342.commands.elevator.SimpleUpCommand;
import org.first.team342.commands.ramp.RampDownCommand;
import org.first.team342.commands.ramp.RampUpCommand;
import org.first.team342.commands.thrower.FlywheelReverseCommand;
import org.first.team342.commands.thrower.FlywheelStopCommand;

/**
 * 
 * @author FIRST Team 342
 */
public class OI {

    private Joystick driveController;
    
    private static final OI INSTANCE = new OI();

    private OI() {
        this.driveController = new Joystick(RobotMap.JOYSTICK_DRIVE_CONTROL);
        
        JoystickButton fire = new JoystickButton(driveController, 6);
        JoystickButton conveyorToggle = new JoystickButton(driveController, 3);
        JoystickButton conveyorReverse = new JoystickButton(driveController, 1);
        JoystickButton balance = new JoystickButton(driveController, 10);
        JoystickButton rampDown = new JoystickButton(driveController, 4);
        JoystickButton rampUp = new JoystickButton(driveController, 2);
        JoystickButton elevatorUp = new JoystickButton(driveController, 8);
        JoystickButton elevatorDown = new JoystickButton(driveController, 7);
        
        fire.whileHeld(new FlywheelReverseCommand());
        fire.whenReleased(new FlywheelStopCommand());
        
        rampDown.whileHeld(new RampDownCommand());
//        ramp.whenReleased(new RampUpCommand());
        rampUp.whileHeld(new RampUpCommand());
        
        conveyorToggle.whenPressed(new ConveyorToggleCommand());
        
        conveyorReverse.whileHeld(new ConveyorReverseCommand());
        conveyorReverse.whenReleased(new ConveyorOffCommand());
        
        balance.whileHeld(new GyroBalanceCommand());
        balance.whenReleased(new DriveWithJoystick());
        
//        elevatorUp.whenPressed(new MoveUpCommand());
//        elevatorDown.whenPressed(new MoveDownCommand());
        
        elevatorUp.whileHeld(new SimpleUpCommand());
        elevatorDown.whileHeld(new SimpleDownCommand());
    }

    public static OI getInstance() {
        return INSTANCE;
    }

    public Joystick getDriveController() {
        return driveController;
    }
}