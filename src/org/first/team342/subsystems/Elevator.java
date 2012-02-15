/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.subsystems;

import org.first.team342.commands.elevator.ResetElevatorCommand;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.first.team342.RobotMap;

/**
 *
 * @author abrightwell
 */
public class Elevator extends Subsystem {

    /**
     * The singleton instance of the Elevator.
     */
    private static final Elevator INSTANCE = new Elevator();
    
    /**
     * The constant that represents the ground floor.
     */
    public static final int GROUND_FLOOR = 0;
    
    /**
     * The constant that represents the middle floor.
     */
    public static final int MIDDLE_FLOOR = 1;
    
    /**
     * The constant that represents the top floor.
     */
    public static final int TOP_FLOOR = 2;
    
    /**
     * The constant that represents the shooter floor.
     */
    public static final int SHOOTER_FLOOR = 3;
    
    /**
     * The constant that represents an unknown floor.
     */
    public static final int UNKNOWN_FLOOR = 4;
    
    /**
     * The speed controller for the elevator motor.
     */
    private Victor elevatorMotor;
    
    /**
     * The digital input sensors for each floor.  It is indexed based off of the constants for the floors.
     */
    private DigitalInput[] floors;

    /**
     * Initialize the elevator.
     */
    private Elevator() {
        this.elevatorMotor = new Victor(RobotMap.PWM_CHANNEL_ELEVATOR);

        this.floors = new DigitalInput[5];

        this.floors[GROUND_FLOOR] = new DigitalInput(RobotMap.DIO_CHANNEL_GROUND_FLOOR);
        this.floors[MIDDLE_FLOOR] = new DigitalInput(RobotMap.DIO_CHANNEL_MIDDLE_FLOOR);
        this.floors[TOP_FLOOR] = new DigitalInput(RobotMap.DIO_CHANNEL_TOP_FLOOR);
        this.floors[SHOOTER_FLOOR] = new DigitalInput(RobotMap.DIO_CHANNEL_SHOOTING_FLOOR);
        this.floors[UNKNOWN_FLOOR] = null;
    }

    /**
     * Returns the elevator instance.
     * @return the elevator instance.
     */
    public static Elevator getInstance() {
        return INSTANCE;
    }

    /**
     * Move the elevator up.  If the current floor is the top most or shooter floor then the elevator will not move.
     */
    public void up() {
        int currentFloor = this.getCurrentFloor();
        if (currentFloor != SHOOTER_FLOOR) {
            this.elevatorMotor.set(1.0);
        } else {
            this.stop();
        }
    }

    /**
     * Move the elevator down.  If the current floor is the bottom most or ground floor then the elevator will not move.
     */
    public void down() {
        int currentFloor = this.getCurrentFloor();
        if (currentFloor != GROUND_FLOOR) {
            this.elevatorMotor.set(-1.0);
        }
    }
    
    public void simpleUp() {
        this.elevatorMotor.set(1.0);
    }
    
    public void simpleDown() {
        this.elevatorMotor.set(-1.0);
    }

    /**
     * Stop the elevator motor.
     */
    public void stop() {
        this.elevatorMotor.stopMotor();
    }

    /**
     * Get the current floor.
     * @return the current floor.
     */
    public int getCurrentFloor() {
        int currentFloor = UNKNOWN_FLOOR;

        for (int floor = GROUND_FLOOR; floor < this.floors.length; floor++) {
            if (this.floors[floor].get()) {
                currentFloor = floor;
                break;
            }
        }

        return currentFloor;
    }

    public void initDefaultCommand() {
        this.setDefaultCommand(new ResetElevatorCommand());
    }
}
