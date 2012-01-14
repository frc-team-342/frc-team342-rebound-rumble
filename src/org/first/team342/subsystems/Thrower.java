/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
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
    
    private SpeedController throwerMotor;
    
    public static final double DEFAULT_THROWER_SPEED_FORWARD = 0.25;
    public static final double DEFAULT_THROWER_SPEED_REVERSE = -0.25;
    
    private Thrower() {
        this.throwerMotor = new Victor(RobotMap.PWM_CHANNEL_THROWER_MOTOR);
    }
    
    public static Thrower getInstance() {
        return INSTANCE;
    }
    
    public void throwForward() {
        this.throwForward(DEFAULT_THROWER_SPEED_FORWARD);
    }
    
    public void throwForward(double value) {
        this.throwerMotor.set(value);
    }
    
    public void throwReverse() {
        this.throwReverse(DEFAULT_THROWER_SPEED_REVERSE);
    }
    
    public void throwReverse(double value) {
        this.throwerMotor.set(value);
    }
    
    public void stop() {
        this.throwerMotor.set(0.0);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
