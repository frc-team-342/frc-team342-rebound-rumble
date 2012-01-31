package org.first.team342;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort.StopBits;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.first.team342.commands.drive.DriveWithJoystick;
import org.first.team342.commands.drive.GyroBalanceCommand;
//import org.first.team342.commands.drive.ResetGyroCommand;
import org.first.team342.commands.thrower.FlyWheelForwardCommand;
import org.first.team342.commands.thrower.FlyWheelReverseCommand;
import org.first.team342.commands.thrower.FlyWheelStopCommand;
import org.first.team342.subsystems.Drive;

public class OI {

    private Joystick driveController;
    
    private static final OI INSTANCE = new OI();

    private OI() {
        this.driveController = new Joystick(RobotMap.JOYSTICK_DRIVE_CONTROL);
        
        JoystickButton throwerForward = new JoystickButton(driveController, 1);
        JoystickButton throwerReverse = new JoystickButton(driveController, 2);

        JoystickButton balanceOne = new JoystickButton(driveController, 3);
        JoystickButton balanceTwo = new JoystickButton(driveController, 4);

        JoystickButton resetGyro = new JoystickButton(driveController, 10);
        
        balanceOne.whileHeld(new GyroBalanceCommand(1.0));
        balanceOne.whenReleased(new DriveWithJoystick());
        
        balanceTwo.whileHeld(new GyroBalanceCommand(2.0));
        balanceTwo.whenReleased(new DriveWithJoystick());
        
//        resetGyro.whileHeld(new ResetGyroCommand());
        resetGyro.whenReleased(new DriveWithJoystick());
        
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
