/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.first.team342.RobotMap;

/**
 *
 * @author Team 342
 */
public class Thrower extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private static final Thrower INSTANCE = new Thrower();
    private CANJaguar throwerMotor;
    public static final double DEFAULT_THROWER_SPEED_FORWARD = 1000;
    public static final double DEFAULT_THROWER_SPEED_REVERSE = -1000;
    
    private Thrower() {
        try {
            this.throwerMotor = new CANJaguar(RobotMap.CAN_DEVICE_THROWER_MOTOR);
            this.throwerMotor.changeControlMode(CANJaguar.ControlMode.kSpeed);
            this.throwerMotor.setPID(.390, 0.175, 0);
            this.throwerMotor.configEncoderCodesPerRev(360);
            this.throwerMotor.setSpeedReference(CANJaguar.SpeedReference.kEncoder);
            this.throwerMotor.enableControl();
            //this.throwerMotor.setX(1000);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public static Thrower getInstance() {
        return INSTANCE;
    }
    
    public void throwForward() {
        this.throwForward(DEFAULT_THROWER_SPEED_FORWARD);
    }
    
    public void throwForward(double value) {
        try {
            this.throwerMotor.setX(value);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void throwReverse() {
        this.throwReverse(DEFAULT_THROWER_SPEED_REVERSE);
    }
    
    public void throwReverse(double value) {
        try {
            this.throwerMotor.setX(value);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void stop() {
        try {
            this.throwerMotor.setX(0.0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getSpeed() {
        double value = 0.0;
        try {
            value = this.throwerMotor.getSpeed();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return value;
    }
}
