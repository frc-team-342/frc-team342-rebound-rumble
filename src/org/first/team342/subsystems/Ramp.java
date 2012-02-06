/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.first.team342.RobotMap;

/**
 *
 * @author Team 342
 */
public class Ramp extends Subsystem {
    private static Ramp INSTANCE = new Ramp();
    
    private SpeedController rampMotor;
    private DigitalInput limitSwitch;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    private Ramp() {
        this.rampMotor = new Victor(RobotMap.PWM_CHANNEL_RAMP);
        this.limitSwitch = new DigitalInput(RobotMap.DIO_CHANNEL_RAMP_SWITCH);
    }
    
    public static Ramp getInstance() {
        return INSTANCE;
    }
    
    public void rampDown() {
        this.rampMotor.set(0.75);
    }
    
    public void rampUp() {
        this.rampMotor.set(-0.25);
    }
    
    public void rampStop() {
        this.rampMotor.set(0.0);
    }
    
    public boolean getLimitSwitch() {
        return limitSwitch.get();
    }
    
}
