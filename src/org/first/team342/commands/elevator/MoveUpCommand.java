/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.commands.elevator;

import org.first.team342.commands.CommandBase;
import org.first.team342.subsystems.Elevator;

/**
 *
 * @author FIRST Team 342
 */
public class MoveUpCommand extends CommandBase {
    
    private Elevator elevator;
    private int initialFloor;
    private int targetFloor;
    
    public MoveUpCommand() {
        this.elevator = Elevator.getInstance();
        this.requires(this.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//        this.initialFloor = this.elevator.getCurrentFloor();
//        this.targetFloor = this.initialFloor;
//        
//        if (initialFloor < Elevator.SHOOTER_FLOOR) {
//            this.targetFloor = this.initialFloor + 1;
//        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        this.elevator.up();
    }

    protected boolean isFinished() {
        return false;
    }
    

    // Called once after isFinished returns true
    protected void end() {
        this.elevator.stop();
//        System.out.println("Reached destination:\t" + this.targetFloor);
//        this.elevator.setCurrentFloor(this.targetFloor);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        elevator.stop();
    }
}
