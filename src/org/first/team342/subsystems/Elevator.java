/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.first.team342.RobotMap;

/**
 *
 * @author abrightwell
 */
public class Elevator extends Subsystem {

    public static final int GROUND_FLOOR = 0;
    
    public static final int MIDDLE_FLOOR = 1;
    
    public static final int TOP_FLOOR = 2;
    
    private static final Elevator INSTANCE = new Elevator();

    private Victor elevatorMotor;
    
    private DigitalOutput groundFloorSensor;
    
    private DigitalOutput middleFloorSensor;
    
    private DigitalOutput topFloorSensor;
    
    private Elevator() {
        this.elevatorMotor = new Victor(RobotMap.PWM_CHANNEL_ELEVATOR);
//        this.groundFloorSensor = new DigitalOutput(RobotMap.DIO_CHANNEL_GROUND_FLOOR);
    }

    public static Elevator getInstance() {
        return INSTANCE;
    }
    
    /**
     * Move the elevator up one floor.
     */
    public void up() {
    }

    /**
     * Move the elevator down one floor.
     */
    public void down() {
    }

    /**
     * Move the elevator to the specified floor.
     * @param floor the floor to move the elevator to.
     */
    public void move(int floor) {
    }

    /**
     * Move the elevator to the ground floor.
     */
    public void ground() {
    }

    /**
     * Move the elevator to the top floor.
     */
    public void top() {
    }

    public void initDefaultCommand() {
        // needs to move the elevator to the ground floor.
    }
}
