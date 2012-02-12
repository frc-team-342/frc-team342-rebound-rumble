/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.commands.conveyor;

import org.first.team342.commands.CommandBase;
import org.first.team342.subsystems.Conveyor;

/**
 *
 * @author FIRST Team 342
 */
public class ConveyorToggleCommand extends CommandBase {
    Conveyor conveyor;
    
    private boolean conveyorStatus;
    
    public ConveyorToggleCommand() {
        this.conveyor = Conveyor.getInstance();
        requires(conveyor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("Toggle Conveyor Initialize");
        this.conveyorStatus = this.conveyor.isConveyorOn();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (this.conveyorStatus) {
            this.conveyor.conveyorOff();
        } else {
            this.conveyor.conveyorOn();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
