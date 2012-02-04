/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.first.team342.RobotMap;

/**
 *
 * @author Team 342
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
        try {
            this.leftFront = new CANJaguar(RobotMap.CAN_DEVICE_LEFT_FRONT, CANJaguar.ControlMode.kPercentVbus);
            this.leftRear = new CANJaguar(RobotMap.CAN_DEVICE_LEFT_REAR, CANJaguar.ControlMode.kPercentVbus);
            this.rightRear = new CANJaguar(RobotMap.CAN_DEVICE_RIGHT_REAR, CANJaguar.ControlMode.kPercentVbus);
            this.rightFront = new CANJaguar(RobotMap.CAN_DEVICE_RIGHT_FRONT, CANJaguar.ControlMode.kPercentVbus);

        this.robotDrive = new RobotDrive(leftFront, leftRear, rightFront, rightRear);
        this.robotDrive.setSafetyEnabled(false);
        this.robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        this.robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);

        //create the accelerometer object
        this.accelerometer = new ADXL345_I2C(1, ADXL345_I2C.DataFormat_Range.k16G);
        // create the gyro object
        this.gyro = new Gyro(RobotMap.DEFAULT_ANNALOG_SLOT, RobotMap.ANALOG_CHANNEL_GYRO);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public static Drive getInstance() {
        return INSTANCE;
    }

    public void driveWithJoystick(Joystick joystick) {
        double x = joystick.getX();
        double y = joystick.getY();
        double rot = joystick.getZ();
        this.robotDrive.mecanumDrive_Cartesian(x, y, rot, 0.0);
        //System.out.println(this.accelerometer.getAcceleration(ADXL345_I2C.Axes.kY));

    }

    public void balance() {
        //----------------------------------------------------------------------
        //Yeske's Elegant Gyro-Based Balancing
        //----------------------------------------------------------------------
        double angle = this.gyro.getAngle();
        double ySpeed = 0.0;
        if (angle > 5 || angle < -5) {
            ySpeed = -angle * 2.0 / 100;
        } else {
            this.robotDrive.stopMotor();
        }
        this.robotDrive.mecanumDrive_Cartesian(0.0, ySpeed, 0.0, 0.0);

        //----------------------------------------------------------------------
        //Collaborative Brute-Force Gyro-Based Balancing
        //----------------------------------------------------------------------
//        System.out.println(angle);
//        if (angle >= 5 && angle <= 12.5){
//            this.robotDrive.mecanumDrive_Cartesian(0.0, -0.1, 0.0, 0.0);
//        }
//        else if (angle <= -5 && angle >= -12.5){
//            this.robotDrive.mecanumDrive_Cartesian(0.0, 0.1, 0.0, 0.0);
//        }
//        else if (angle > 12.5 && angle <= 15){
//            this.robotDrive.mecanumDrive_Cartesian(0.0, -0.30, 0.0, 0.0);
//        }
//        else if (angle < -12.5 && angle >= -15){
//            this.robotDrive.mecanumDrive_Cartesian(0.0, 0.30, 0.0, 0.0);
//        }
//        else if (angle > 15){
//            this.robotDrive.mecanumDrive_Cartesian(0.0, -0.35, 0.0, 0.0);
//        }
//        else if (angle < -15){
//            this.robotDrive.mecanumDrive_Cartesian(0.0, 0.35, 0.0, 0.0);
//        }
//        else{
//            this.robotDrive.stopMotor();
//        }
    }

    public void setGyro() {
        this.gyro.reset();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
