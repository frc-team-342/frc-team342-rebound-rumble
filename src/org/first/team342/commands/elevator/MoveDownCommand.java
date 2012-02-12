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
public class MoveDownCommand extends CommandBase {

    private Elevator elevator;
    private int initialFloor;
    private int targetFloor;

    public MoveDownCommand() {
        this.elevator = Elevator.getInstance();
        this.requires(this.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.initialFloor = this.elevator.getCurrentFloor();
        this.targetFloor = this.initialFloor;

        if (this.initialFloor > Elevator.GROUND_FLOOR) {
            this.targetFloor = this.initialFloor - 1;
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        this.elevator.down();
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
