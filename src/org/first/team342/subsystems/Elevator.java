/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
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
    private DigitalInput groundFloorSensor;
    private DigitalInput middleFloorSensor;
    private DigitalInput topFloorSensor;
    private DigitalInput shootingFloorSensor;
    private DigitalInput targetFloor;
    private int currentPosition;
    private DigitalInput[] floors;
    private int lastPosition;

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
        if (this.currentPosition >= this.floors.length) {
            this.currentPosition = 0;
        } else {
            this.currentPosition++;
        }
    }

    /**
     * Move the elevator down one floor.
     */
    public void down() {
        if (this.currentPosition <= 0) {
            this.currentPosition = 0;
        } else {
            this.currentPosition--;
            this.targetFloor = floors[currentPosition];
        }
    }
    public void toBottom(){
        this.currentPosition = 0;
    }

    public boolean move() {
        boolean done = false;
        if (!this.targetFloor.get()) {
            if (currentPosition < lastPosition) {
                this.elevatorMotor.set(1.0);
            } else if (currentPosition > lastPosition) {
                this.elevatorMotor.set(-1.0);
            }else{
                this.elevatorMotor.set(0.0);
            }
        } else {
            this.elevatorMotor.set(0.0);
            this.currentPosition = this.lastPosition;
            done =true;
        }
        return done;
    }

    public void stop() {
        elevatorMotor.stopMotor();
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
