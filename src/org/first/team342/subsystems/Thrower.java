/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package org.first.team342.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.first.team342.RobotMap;

/**
*
* @author FIRST Team 342
*/
public class Thrower extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private static final Thrower INSTANCE = new Thrower();
    private CANJaguar throwerMotorMaster;
    private CANJaguar throwerMotorSlave;

    

    private Thrower() {
        try {

            this.throwerMotorMaster = new CANJaguar(RobotMap.CAN_DEVICE_THROWER_MOTOR_MASTER);
            this.throwerMotorSlave = new CANJaguar(RobotMap.CAN_DEVICE_THROWER_MOTOR_SLAVE);
            this.throwerMotorMaster.changeControlMode(CANJaguar.ControlMode.kSpeed);
            this.throwerMotorSlave.changeControlMode(CANJaguar.ControlMode.kVoltage);
            this.updatePID();
            this.throwerMotorMaster.configEncoderCodesPerRev(360);
            this.throwerMotorMaster.setSpeedReference(CANJaguar.SpeedReference.kEncoder);
            this.throwerMotorMaster.enableControl();
            this.throwerMotorSlave.enableControl();
        } catch (CANTimeoutException ex) {
            System.out.println("The Thrower Timed Out");
        }
    }

    public static Thrower getInstance() {
        return INSTANCE;
    }

    public void throwForward(double value) {
        try {
            this.throwerMotorMaster.setX(value);
            this.throwerMotorSlave.setX(this.throwerMotorMaster.getOutputVoltage());
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void throwReverse(double value) {
        try {
            this.throwerMotorMaster.setX(value);
            this.throwerMotorSlave.setX(this.throwerMotorMaster.getOutputVoltage());
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void stop() {
        try {
            this.throwerMotorMaster.setX(0.0);
            this.throwerMotorSlave.setX(0.0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void initDefaultCommand() {
    }

    public void updatePID() {
        Preferences preferences = Preferences.getInstance();
        double proportionalGain = preferences.getDouble("Thrower_Proportional", 0.0);
        double integralGain = preferences.getDouble("Thrower_Integral", 0.0);
        double derivativeGain = preferences.getDouble("Thrower_Derivative", 0.0);
        
        try {
            this.throwerMotorMaster.setPID(proportionalGain, integralGain, derivativeGain);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void updateStatus() {
        try {
            SmartDashboard.putDouble("Shooter Speed: ", this.throwerMotorMaster.getSpeed());
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public double getSpeed() {
        double value = 0.0;
        try {
            value = this.throwerMotorMaster.getSpeed();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return value;
    }
}
