/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.first.team342.commands.elevator.MoveToFloorCommand;
import org.first.team342.commands.thrower.FlywheelReverseCommand;
import org.first.team342.commands.thrower.FlywheelStopCommand;

/**
 *
 * @author abrightwell
 */
public class DefaultAutonomous extends CommandGroup {
    
    public DefaultAutonomous() {
        this.addParallel(new FlywheelReverseCommand());
        this.addSequential(new MoveToFloorCommand(2));
        this.addSequential(new WaitCommand(1.0));
        this.addSequential(new MoveToFloorCommand(1));
        this.addSequential(new WaitCommand(1.0));
        this.addSequential(new FlywheelStopCommand());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
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
