/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.commands.thrower;

import edu.wpi.first.wpilibj.DriverStation;
import org.first.team342.commands.CommandBase;
import org.first.team342.subsystems.Thrower;

/**
 *
 * @author FIRST Team 342
 */
public class FlywheelReverseCommand extends CommandBase {
    
    private Thrower thrower;
    private double value;
    
    public FlywheelReverseCommand(double value) {
        this.value = value;
        this.thrower = Thrower.getInstance();
        requires(this.thrower);
    }
    
    public FlywheelReverseCommand() {
        this(3000);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("Spinning Thrower Backward");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        this.thrower.throwReverse(this.value);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}