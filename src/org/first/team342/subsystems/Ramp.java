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
 * This subsystem is the system to controls the lever that pushes down the ramps
 * in order to cross the field.
 * 
 * @author FIRST Team 342
 */
public class Ramp extends Subsystem {

    /**
     * The constant value for the default speed of the lever moving up.
     */
    public static final double DEFAULT_SPEED_UP = -0.75;
    
    /**
     * The constant value for the default speed of the lever moving down.
     */
    public static final double DEFAULT_SPEED_DOWN = 0.75;
    
    /**
     * Singleton instance.
     */
    private static final Ramp INSTANCE = new Ramp();
    
    /**
     * The speed controller to used to control the lever.
     */
    private SpeedController rampMotor;
    
    /**
     * The limit switch that will indicate that the lever has reached the fully
     * retracted position.
     */
    private DigitalInput limitSwitch;

    /**
     * Creates a new instance.  Initializes all properties.
     */
    private Ramp() {
        this.rampMotor = new Victor(RobotMap.PWM_CHANNEL_RAMP);
        this.limitSwitch = new DigitalInput(RobotMap.DIO_CHANNEL_RAMP_SWITCH);
    }

    /**
     * Returns the ramp instance for the robot.
     * @return the ramp instance for the robot.
     */
    public static Ramp getInstance() {
        return INSTANCE;
    }

    /**
     * Rotates the lever down.
     */
    public void down() {
        this.rampMotor.set(DEFAULT_SPEED_DOWN);
    }

    /**
     * Rotates the lever up.
     */
    public void up() {
        this.rampMotor.set(DEFAULT_SPEED_UP);
    }

    /**
     * Stops the rotation of the lever.
     */
    public void stop() {
        this.rampMotor.set(0.0);
    }

    /**
     * Returns whether or not the lever has been fully retracted.
     * @return whether or not the lever has been fully retracted.
     */
    public boolean isLeverRetracted() {
        return this.limitSwitch.get();
    }
    
    /**
     * {@inheritDoc}
     */
    public void initDefaultCommand() {
    }
}
