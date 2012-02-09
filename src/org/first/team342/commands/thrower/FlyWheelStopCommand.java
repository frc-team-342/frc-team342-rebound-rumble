/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.commands.thrower;

import org.first.team342.commands.CommandBase;
import org.first.team342.subsystems.Thrower;

/**
 *
 * @author abrightwell
 */
public class FlywheelStopCommand extends CommandBase {
    
    private Thrower thrower;
    
    public FlywheelStopCommand() {
<<<<<<< HEAD
        //this.thrower = Thrower.getInstance();
=======
        this.thrower = Thrower.getInstance();
>>>>>>> 70d9f06086df599628e194b8ac0e61e5e35cba32
        requires(this.thrower);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        this.thrower.stop();
        this.setFinished(true);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
