/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.first.team342.RobotMap;
import org.first.team342.RobotUtilities;
import org.first.team342.commands.drive.DriveWithJoystick;

/**
 *
 * @author FIRST Team 342
 */
public class Drive extends Subsystem {

    private static final Drive INSTANCE = new Drive();
    private SpeedController leftFront;
    private SpeedController rightFront;
    private SpeedController leftRear;
    private SpeedController rightRear;
    private RobotDrive robotDrive;
    //accelerometer
    private ADXL345_I2C accelerometer;
    //Gyro
    private Gyro gyro;

    private Drive() {
        this.leftFront = RobotUtilities.initializeCANJaguar(RobotMap.CAN_DEVICE_LEFT_FRONT_DRIVE_MOTOR);
        this.rightFront = RobotUtilities.initializeCANJaguar(RobotMap.CAN_DEVICE_RIGHT_FRONT_DRIVE_MOTOR);
        this.leftRear = RobotUtilities.initializeCANJaguar(RobotMap.CAN_DEVICE_LEFT_REAR_DRIVE_MOTOR);
        this.rightRear = RobotUtilities.initializeCANJaguar(RobotMap.CAN_DEVICE_RIGHT_REAR_DRIVE_MOTOR);

        this.robotDrive = new RobotDrive(leftFront, leftRear, rightFront, rightRear);
        this.robotDrive.setSafetyEnabled(false);
        this.robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        this.robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);

        //create the accelerometer object
        this.accelerometer = new ADXL345_I2C(1, ADXL345_I2C.DataFormat_Range.k16G);
        // create the gyro object
        this.gyro = new Gyro(RobotMap.DEFAULT_ANNALOG_SLOT, RobotMap.ANALOG_CHANNEL_GYRO);
    }

    public static Drive getInstance() {
        return INSTANCE;
    }

    public void driveWithJoystick(Joystick joystick) {
        double x = -joystick.getX() * 0.80;
        double y = joystick.getY() * 0.80;
        double rot = joystick.getZ() * 0.80;
        this.robotDrive.mecanumDrive_Cartesian(x, y, rot, 0.0);
    }

    public void balance() {
        final double ratio = .02;
        double angle = this.gyro.getAngle();
        double speed = (angle * ratio);
        System.out.println(angle + "\t" + speed);
        if (angle >= 5 && angle <= -5) {
            this.robotDrive.mecanumDrive_Cartesian(0.0, 0.0, 0.0, 0.0);
        } else if (angle <= 35 && angle >= -35) {
            this.robotDrive.mecanumDrive_Cartesian(0.0, -speed, 0.0, 0.00);
        } else {
            this.robotDrive.stopMotor();
        }
    }

    public void setGyro() {
        this.gyro.reset();
    }

    public void resetGyro() {
        this.gyro.reset();
    }

    public void initDefaultCommand() {
        this.setDefaultCommand(new DriveWithJoystick());
    }
}