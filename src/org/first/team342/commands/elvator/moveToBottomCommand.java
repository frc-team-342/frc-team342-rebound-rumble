/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.commands.elvator;

import org.first.team342.commands.CommandBase;
import org.first.team342.subsystems.Elevator;

/**
 *
 * @author Team 342
 */
public class moveToBottomCommand extends CommandBase {
    private Elevator elevator;
    private boolean done;
    
    public moveToBottomCommand() {
        this.elevator = Elevator.getInstance();
        requires(this.elevator);
        this.done = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.elevator.toBottom();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        this.done = this.elevator.move();        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.done;
    }

    // Called once after isFinished returns true
    protected void end() {
        this.elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
