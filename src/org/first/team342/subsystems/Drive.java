/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.subsystems;

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
    public void balance(){
        double angle = this.gyro.getAngle();
        System.out.println(angle);
        if (angle >= 5 && angle <= 12.5){
            this.robotDrive.mecanumDrive_Cartesian(0.0, -0.1, 0.0, 0.0);
        }
        else if (angle <= -5 && angle >= -12.5){
            this.robotDrive.mecanumDrive_Cartesian(0.0, 0.1, 0.0, 0.0);
        }
        else if (angle > 12.5 && angle <= 15){
            this.robotDrive.mecanumDrive_Cartesian(0.0, -0.30, 0.0, 0.0);
        }
        else if (angle < -12.5 && angle >= -15){
            this.robotDrive.mecanumDrive_Cartesian(0.0, 0.30, 0.0, 0.0);
        }
        else if (angle > 15){
            this.robotDrive.mecanumDrive_Cartesian(0.0, -0.35, 0.0, 0.0);
        }
        else if (angle < -15){
            this.robotDrive.mecanumDrive_Cartesian(0.0, 0.35, 0.0, 0.0);
        }
        else{
            this.robotDrive.stopMotor();
        }
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}