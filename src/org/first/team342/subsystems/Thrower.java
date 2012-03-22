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
import org.first.team342.RobotUtilities;

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
        this.throwerMotorMaster = RobotUtilities.initializeCANJaguar(RobotMap.CAN_DEVICE_THROWER_MOTOR_MASTER);
        this.throwerMotorSlave = RobotUtilities.initializeCANJaguar(RobotMap.CAN_DEVICE_THROWER_MOTOR_SLAVE);

        try {
            if (this.throwerMotorMaster != null) {
                this.throwerMotorMaster.changeControlMode(CANJaguar.ControlMode.kSpeed);
                this.throwerMotorMaster.setSpeedReference(CANJaguar.SpeedReference.kEncoder);
                this.throwerMotorMaster.configEncoderCodesPerRev(360);
                this.throwerMotorMaster.enableControl();
            }

            if (this.throwerMotorSlave != null) {
                this.throwerMotorSlave.changeControlMode(CANJaguar.ControlMode.kVoltage);
                this.throwerMotorSlave.enableControl();
            }

            this.updatePID();
        } catch (CANTimeoutException e) {
            System.out.println("An error occured initializing the thrower motors.");
        }
    }

    public static Thrower getInstance() {
        return INSTANCE;
    }

    public void throwForward(double value) {
        try {
            if (this.throwerMotorMaster != null) {
                this.throwerMotorMaster.setX(value);
            }

            if (this.throwerMotorSlave != null) {
                this.throwerMotorSlave.setX(this.throwerMotorMaster.getOutputVoltage());
            }
            
            System.out.println("Speed: " + this.throwerMotorMaster.getSpeed());
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void throwReverse(double value) {
        try {
            if (this.throwerMotorMaster != null) {
                this.throwerMotorMaster.setX(value);
            }

            if (this.throwerMotorSlave != null) {
                this.throwerMotorSlave.setX(this.throwerMotorMaster.getOutputVoltage());
            }
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void stop() {
        try {
            if (this.throwerMotorMaster != null) {
                this.throwerMotorMaster.setX(0.0);
            }

            if (this.throwerMotorSlave != null) {
                this.throwerMotorSlave.setX(0.0);
            }
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void initDefaultCommand() {
    }

    public final void updatePID() {
        Preferences preferences = Preferences.getInstance();
        double proportionalGain = preferences.getDouble("Thrower_Proportional", 0.0);
        double integralGain = preferences.getDouble("Thrower_Integral", 0.0);
        double derivativeGain = preferences.getDouble("Thrower_Derivative", 0.0);

        try {
            if (this.throwerMotorMaster != null) {
                this.throwerMotorMaster.setPID(proportionalGain, integralGain, derivativeGain);
            }
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void updateStatus() {
        try {
            if (this.throwerMotorMaster != null) {
                SmartDashboard.putDouble("Shooter Speed: ", this.throwerMotorMaster.getSpeed());
            }
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public double getSpeed() {
        double value = 0.0;
        try {
            if (this.throwerMotorMaster != null) {
                value = this.throwerMotorMaster.getSpeed();
            }
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return value;
    }
}