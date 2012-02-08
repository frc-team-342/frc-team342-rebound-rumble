/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.commands.ramp;

import org.first.team342.commands.CommandBase;
import org.first.team342.subsystems.Ramp;

/**
 *
 * @author Team 342
 */
public class RampUpCommand extends CommandBase {
    private Ramp ramp = Ramp.getInstance();
    
    public RampUpCommand() {
        requires(ramp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        ramp.rampUp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ramp.getLimitSwitch();
    }

    // Called once after isFinished returns true
    protected void end() {
        ramp.rampStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
