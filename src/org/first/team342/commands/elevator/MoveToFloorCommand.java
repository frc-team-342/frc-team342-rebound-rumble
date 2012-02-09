/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.commands.elevator;

import org.first.team342.commands.CommandBase;
import org.first.team342.subsystems.Elevator;

/**
 *
 * @author abrightwell
 */
public class MoveToFloorCommand extends CommandBase {

    private Elevator elevator;
    private int initialFloor;
    private int targetFloor;

    /**
     * Create a new instance of this command.
     * @param targetFloor the target floor to move to.  This value corresponds to the floor constants found in the Elevator class.
     */
    public MoveToFloorCommand(int targetFloor) {
        this.elevator = Elevator.getInstance();
        this.targetFloor = targetFloor;
        this.requires(this.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.initialFloor = this.elevator.getCurrentFloor();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (this.targetFloor < this.initialFloor) {
            this.elevator.down();
        } else if (this.targetFloor > this.initialFloor) {
            this.elevator.up();
        } else {
            this.elevator.stop();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (this.elevator.getCurrentFloor() == this.targetFloor);
    }

    // Called once after isFinished returns true
    protected void end() {
        this.elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        this.elevator.stop();
    }
}
