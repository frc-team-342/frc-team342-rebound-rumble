package org.first.team342;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.first.team342.commands.conveyor.ConveyorOffCommand;
import org.first.team342.commands.conveyor.ConveyorReverseCommand;
import org.first.team342.commands.conveyor.ConveyorToggleCommand;
import org.first.team342.commands.drive.DriveWithJoystick;
import org.first.team342.commands.drive.GyroBalanceCommand;
import org.first.team342.commands.elevator.SimpleDown;
import org.first.team342.commands.elevator.SimpleUp;
import org.first.team342.commands.elevator.StopElevatorCommand;
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
        JoystickButton ramp = new JoystickButton(driveController, 4);
        JoystickButton elevatorUp = new JoystickButton(driveController, 8);
        JoystickButton elevatorDown = new JoystickButton(driveController, 7);
        
        fire.whileHeld(new FlywheelReverseCommand());
        fire.whenReleased(new FlywheelStopCommand());
        
        ramp.whileHeld(new RampDownCommand());
        ramp.whenReleased(new RampUpCommand());
        
        conveyorToggle.whenPressed(new ConveyorToggleCommand());
        
        conveyorReverse.whileHeld(new ConveyorReverseCommand());
        conveyorReverse.whenReleased(new ConveyorOffCommand());
        
        balance.whileHeld(new GyroBalanceCommand());
        balance.whenReleased(new DriveWithJoystick());
        
//        elevatorUp.whenPressed(new MoveUpCommand());
//        
//        elevatorDown.whenPressed(new MoveDownCommand());
        
        elevatorUp.whileHeld(new SimpleUp());
        elevatorUp.whenReleased(new StopElevatorCommand());
        
        elevatorDown.whileHeld(new SimpleDown());
        elevatorDown.whenReleased(new StopElevatorCommand());
    }

    public static OI getInstance() {
        return INSTANCE;
    }

    public Joystick getDriveController() {
        return driveController;
    }
}