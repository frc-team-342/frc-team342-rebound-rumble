/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.subsystems;

import com.sun.squawk.util.Arrays;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.first.team342.RobotMap;

/**
 *
 * @author Team 342
 */
public class Drive extends Subsystem {
    
    private static final Drive INSTANCE = new Drive();
    
    private static final int ANGLE_HISTORY_SIZE = 10;
    private static final int ANGLE_CHANGE_THRESHHOLD = 2;
    
    private SpeedController leftFront;
    private SpeedController rightFront;
    private SpeedController leftRear;
    private SpeedController rightRear;
    
    private RobotDrive robotDrive;
    //accelerometer
    private ADXL345_I2C accelerometer;
    //Gyro
    private Gyro gyro;
    private double[] angleHistory;
    private int index;
    private double angleSum;
    private boolean latch;
    
    private Drive() {
        this.leftFront = new Jaguar(RobotMap.PWM_CHANNEL_LEFT_FRONT);
        this.rightFront = new Jaguar(RobotMap.PWM_CHANNEL_RIGHT_FRONT);
        this.leftRear = new Jaguar(RobotMap.PWM_CHANNEL_LEFT_REAR);
        this.rightRear = new Jaguar(RobotMap.PWM_CHANNEL_RIGHT_REAR);
        
        this.robotDrive = new RobotDrive(leftFront, leftRear, rightFront, rightRear);
        this.robotDrive.setSafetyEnabled(false);
        this.robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        this.robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        
        //create the accelerometer object
        this.accelerometer = new ADXL345_I2C(1, ADXL345_I2C.DataFormat_Range.k16G);
        // create the gyro object
        this.gyro = new Gyro(RobotMap.DEFAULT_ANNALOG_SLOT, RobotMap.ANALOG_CHANNEL_GYRO);
        this.angleHistory = new double[ANGLE_HISTORY_SIZE];
        Arrays.fill(this.angleHistory, 0.0);
        this.angleSum = 0.0;
        this.resetLatch();
    }
    
    public static Drive getInstance() {
        return INSTANCE;
    }
    
    public void driveWithJoystick(Joystick joystick) {
        latch = false;
        double x = joystick.getX();
        double y = joystick.getY();
        double rot = joystick.getZ();
        this.robotDrive.mecanumDrive_Cartesian(x, y, rot, 0.0);
        //System.out.println(this.accelerometer.getAcceleration(ADXL345_I2C.Axes.kY));
        this.refreshAngleHistory();
        
    }
    public void balance(double powerFactor){
//        this.refreshAngleHistory();
//        double currentAngle = this.gyro.getAngle();
//        double angleDiff = currentAngle - (angleSum / ANGLE_HISTORY_SIZE);
//        double driveSpeed = -currentAngle * 0.025;
//        double ySpeed = 0;
//        
//        if (currentAngle > 5 || currentAngle < -5 || latch) {
//            if (angleDiff > ANGLE_CHANGE_THRESHHOLD) {
//                ySpeed = driveSpeed * .1;
//            } else {
//                ySpeed = driveSpeed;
//            }
//        } else {
//            latch = true;
//            ySpeed = 0;
//        }
//        this.robotDrive.mecanumDrive_Cartesian(0.0, ySpeed, 0.0, 0.0);
        //----------------------------------------------------------------------
        // Charlie's crazy accelerometer-based balancing code
        //----------------------------------------------------------------------
//        double Yaccel = this.accelerometer.getAcceleration(ADXL345_I2C.Axes.kY);
//        double Xaccel = this.accelerometer.getAcceleration(ADXL345_I2C.Axes.kX);
//        double Zaccel = this.accelerometer.getAcceleration(ADXL345_I2C.Axes.kZ);
//        System.out.println("Accel =\t" + Yaccel + Xaccel + Zaccel);
//        double constantFactor = 1.0;
//        //multiply the accleration the robot experiences by a constant factor to
//        //move the robot the other way. Hopefully, at low accelerations, the
//        //drivevalue won't be enough to turn the motors.
//        double driveValue = constantFactor * Yaccel;
//        System.out.println("Drive value =\t" + driveValue);
//        this.robotDrive.mecanumDrive_Cartesian(0.0, driveValue, 0.0, 0.0);
        //----------------------------------------------------------------------
        // The thought-out, insightful gyro-based balancing code
        //----------------------------------------------------------------------
        double angle = this.gyro.getAngle();
        System.out.println(angle);
        double driveSpeed;
        if (angle >= 5 && angle <= 12.5){
            driveSpeed = -0.1;
        }
        else if (angle <= -5 && angle >= -12.5){
            driveSpeed = 0.1;
        }
        else if (angle > 12.5 && angle <= 15){
            driveSpeed = -0.30;
        }
        else if (angle < -12.5 && angle >= -15){
            driveSpeed = 0.30;
        }
        else if (angle > 15){
            driveSpeed = -0.35;
        }
        else if (angle < -15){
            driveSpeed = 0.35;
        }
        else{
            driveSpeed = 0.0;
        }
        this.robotDrive.mecanumDrive_Cartesian(0.0, driveSpeed, 0.0, 0.0);
    }
    
    public void refreshAngleHistory() {
        index++;
        index = index % ANGLE_HISTORY_SIZE;
        double currentAngle = this.gyro.getAngle();
        double oldestAngle = angleHistory[index];
        angleHistory[index] = currentAngle;
        angleSum += currentAngle;
        angleSum -= oldestAngle;
    }
    
    public void resetLatch() {
        latch = false;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}