/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
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
    private CANJaguar leftFront;
    private CANJaguar rightFront;
    private CANJaguar leftRear;
    private CANJaguar rightRear;
    private RobotDrive robotDrive;
    //accelerometer
    private ADXL345_I2C accelerometer;
    //Gyro
    private Gyro gyro;
    private double previousGyroAngle;
    private boolean initialized;

    private Drive() {
        this.initialized = false;

        this.leftFront = RobotUtilities.initializeCANJaguar(RobotMap.CAN_DEVICE_LEFT_FRONT_DRIVE_MOTOR);
        this.rightFront = RobotUtilities.initializeCANJaguar(RobotMap.CAN_DEVICE_RIGHT_FRONT_DRIVE_MOTOR);
        this.leftRear = RobotUtilities.initializeCANJaguar(RobotMap.CAN_DEVICE_LEFT_REAR_DRIVE_MOTOR);
        this.rightRear = RobotUtilities.initializeCANJaguar(RobotMap.CAN_DEVICE_RIGHT_REAR_DRIVE_MOTOR);

        if (this.leftFront != null && this.rightFront != null && this.leftRear != null && this.rightRear != null) {
            this.robotDrive = new RobotDrive(this.leftFront, this.leftRear, this.rightFront, this.rightRear);
            this.robotDrive.setSafetyEnabled(false);
            this.robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
            this.robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        } else {
            System.out.println("Drive Subsystem was not initialized.");
            this.initialized = false;
        }

        //create the accelerometer object
        this.accelerometer = new ADXL345_I2C(1, ADXL345_I2C.DataFormat_Range.k16G);
        // create the gyro object
        this.gyro = new Gyro(RobotMap.DEFAULT_ANNALOG_SLOT, RobotMap.ANALOG_CHANNEL_GYRO);
        this.previousGyroAngle = this.gyro.getAngle();
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

    /**
     * Drive the robot in the forward direction.  The value provided must be
     * between 0.0 and 1.0.
     * @param value the speed to drive the robot.
     */
    public void driveForward(double value) {
        if (this.isValidDriveValue(value)) {
            this.robotDrive.mecanumDrive_Cartesian(0.0, value, 0.0, 0.0);
        } else {
            System.out.println("Drive.driveForward(value): Could not drive forward, value = " + value);
        }
    }

    /**
     * Drive the robot in the forwards direction.  The value provided to the
     * robot will be equivalent to the full speed.
     * <br><br>
     * <b>WARNING:</b> use this method with caution.  It will command the robot 
     * to move in the forward direction at full speed.
     */
    public void driveForward() {
        this.driveForward(1.0);
    }

    /**
     * Drive the robot in the backward direction.  The value provided must be
     * between 0.0 and 1.0.
     * @param value the speed to drive the robot.
     */
    public void driveBackward(double value) {
        if (this.isValidDriveValue(value)) {
            this.robotDrive.mecanumDrive_Cartesian(0.0, -value, 0.0, 0.0);
        } else {
            System.out.println("Drive.driveBackward(value): Cound not drive backward, value = " + value);
        }
    }

    /**
     * Drive the robot in the backward direction.  The value provided to the
     * robot will be equivalent to the full speed.
     * <br><br>
     * <b>WARNING:</b> use this method with caution.  It will command the robot 
     * to move in the backward direction at full speed.
     */
    public void driveBackward() {
        this.driveBackward(1.0);
    }

    /**
     * Drive the robot in the left hand direction.  The value provided must be
     * between 0.0 and 1.0.
     * @param value the speed to drive the robot.
     */
    public void driveLeft(double value) {
        if (this.isValidDriveValue(value)) {
            this.robotDrive.mecanumDrive_Cartesian(-value, 0.0, 0.0, 0.0);
        } else {
            System.out.println("Drive.driveLeft(value): Cound not drive left, value = " + value);
        }
    }

    /**
     * Drive the robot in the left hand direction.  The value provided to the
     * robot will be equivalent to the full speed.
     * <br><br>
     * <b>WARNING:</b> use this method with caution.  It will command the robot 
     * to move in the left hand direction at full speed.
     */
    public void driveLeft() {
        this.driveLeft(1.0);
    }

    /**
     * Drive the robot in the right hand direction.  The value provided must be
     * between 0.0 and 1.0.
     * @param value the speed to drive the robot.
     */
    public void driveRight(double value) {
        if (this.isValidDriveValue(value)) {
            this.robotDrive.mecanumDrive_Cartesian(value, 0.0, 0.0, 0.0);
        } else {
            System.out.println("Drive.driveRight(value): Cound not drive right, value = " + value);
        }
    }

    /**
     * Drive the robot in the right hand direction.  The value provided to the
     * robot will be equivalent to the full speed.
     * <br><br>
     * <b>WARNING:</b> use this method with caution.  It will command the robot 
     * to move in the right hand direction at full speed.
     */
    public void driveRight() {
        this.driveRight(1.0);
    }
    
    /**
     * Stop the robot.
     */
    public void stop() {
        this.robotDrive.mecanumDrive_Cartesian(0.0, 0.0, 0.0, 0.0);
    }

    /**
     * Determines if the <code>value</code> is valid.  Valid values are between 0.0 and 1.0.
     * @param value the value to validate.
     * @return <code>true</code> if the value is valid, otherwise <code>false</code>.
     */
    private boolean isValidDriveValue(double value) {
        return (value < 0 || value > 1.0);
    }

    public void tankDrive(double left, double right) {
        byte syncGroup = (byte) 0x80;

        if (this.initialized) {
            try {
                this.leftFront.setX(left, syncGroup);
                this.leftRear.setX(left, syncGroup);
                this.rightFront.setX(right, syncGroup);
                this.rightRear.setX(right, syncGroup);

                CANJaguar.updateSyncGroup(syncGroup);
            } catch (CANTimeoutException ex) {
                ex.printStackTrace();
            }
        }
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