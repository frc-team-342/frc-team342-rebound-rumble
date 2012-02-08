/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.commands.elevator;

import org.first.team342.commands.CommandBase;
import org.first.team342.subsystems.Elevator;

/**
 *
 * @author Charlie
 */
public class MoveUpCommand extends CommandBase {
    
    Elevator elevator;
    
    public MoveUpCommand() {
        elevator = Elevator.getInstance();
        requires(elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        elevator.up();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return elevator.isAtTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
        elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        elevator.stop();
    }
}
