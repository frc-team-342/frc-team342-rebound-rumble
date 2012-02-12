/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.first.team342.subsystems.Elevator;

/**
 *
 * @author FIRST Team 342
 */
public class ResetElevatorCommand extends Command {

    private Elevator elevator;
    
    public ResetElevatorCommand() {
        this.elevator = Elevator.getInstance();
        this.requires(this.elevator);
    }

    protected void initialize() {
    }

    protected void execute() {
        this.elevator.down();
    }

    protected boolean isFinished() {
        return (this.elevator.getCurrentFloor() == Elevator.GROUND_FLOOR);
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
