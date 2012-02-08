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

//    public static final int GROUND_FLOOR = 2;
//    
//    public static final int MIDDLE_FLOOR = 3;
//    
//    public static final int TOP_FLOOR = 4;
    
    public static final int INITIAL_ELEVATOR_POSITION = 0;
    
    private static final Elevator INSTANCE = new Elevator();

    private Victor elevatorMotor;
    
    private double motorDirection;
    
    private DigitalInput groundFloorSensor;
    
    private DigitalInput middleFloorSensor;
    
    private DigitalInput topFloorSensor;
    
    private DigitalInput shootingFloorSensor;
    
    private DigitalInput targetFloor;
    
    private int currentPosition;
    
    private DigitalInput[] floors;
    
    private Elevator() {
        this.elevatorMotor = new Victor(RobotMap.PWM_CHANNEL_ELEVATOR);
        
        this.groundFloorSensor = new DigitalInput(RobotMap.DIO_CHANNEL_GROUND_FLOOR);
        this.middleFloorSensor = new DigitalInput(RobotMap.DIO_CHANNEL_MIDDLE_FLOOR);
        this.topFloorSensor = new DigitalInput(RobotMap.DIO_CHANNEL_TOP_FLOOR);
        this.shootingFloorSensor = new DigitalInput(RobotMap.DIO_CHANNEL_SHOOTING_FLOOR);
        
        this.floors = new DigitalInput[4];
        this.floors[0] = groundFloorSensor;
        this.floors[1] = middleFloorSensor;
        this.floors[2] = topFloorSensor;
        this.floors[3] = shootingFloorSensor;
        
        this.currentPosition = INITIAL_ELEVATOR_POSITION;
        this.targetFloor = floors[currentPosition];
    }

    public static Elevator getInstance() {
        return INSTANCE;
    }
    
    /**
     * Move the elevator up one floor.
     */
    public void up() {
        // there may in fact be a try! TODO: see if the increment is undone if it fails.
        try {
            currentPosition++;
            targetFloor = floors[currentPosition];
        } catch(ArrayIndexOutOfBoundsException e) {
            currentPosition--;
            // TODO: make this yell at Alex through driver station
            System.out.println("You're already at the top floor, Alex");
        }
        System.out.println("currentPosition = \t" + currentPosition);
        this.motorDirection = 1.0;
    }

    /**
     * Move the elevator down one floor.
     */
    public void down() {
        // there is in fact a try!
        try {
            currentPosition--;
            targetFloor = floors[currentPosition];
        } catch(ArrayIndexOutOfBoundsException e) {
            currentPosition++;
            // TODO: make this yell at Alex through driver station
            System.out.println("You're already at the bottom floor, Alex");
        }
        System.out.println("currentPosition = \t" + currentPosition);
        this.motorDirection = -1.0;
    }
    
    public void move() {
        elevatorMotor.set(motorDirection);
    }
    
    public boolean isAtTarget() {
        return targetFloor.get();
    }
    
    public void stop() {
        elevatorMotor.stopMotor();
    }
    
    public int getPosition() {
        return currentPosition;
    }


//    /**
//     * Move the elevator to the ground floor.
//     */
//    public void ground() {
//    }
//
//    /**
//     * Move the elevator to the top floor.
//     */
//    public void top() {
//    }

    public void initDefaultCommand() {
        // needs to move the elevator to the ground floor.
    }
}
