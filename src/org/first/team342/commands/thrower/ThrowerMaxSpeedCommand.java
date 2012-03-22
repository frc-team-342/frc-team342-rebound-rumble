/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.commands.thrower;

import org.first.team342.commands.CommandBase;
import org.first.team342.subsystems.Thrower;

/**
 *
 * @author Team 342
 */
public class ThrowerMaxSpeedCommand extends CommandBase {
    Thrower thrower = Thrower.getInstance();
    public ThrowerMaxSpeedCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(thrower);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        this.thrower.throwForward(-2500);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        this.thrower.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        this.thrower.stop();
    }
}
