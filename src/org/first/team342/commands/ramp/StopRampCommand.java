/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.commands.ramp;

import org.first.team342.commands.CommandBase;
import org.first.team342.subsystems.Ramp;

/**
 *
 * @author abrightwell
 */
public class StopRampCommand extends CommandBase {
    
    private Ramp ramp;
    
    public StopRampCommand() {
        this.ramp = Ramp.getInstance();
        this.requires(this.ramp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        this.ramp.stop();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        this.ramp.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
