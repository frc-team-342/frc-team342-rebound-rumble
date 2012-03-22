/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.commands.elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.first.team342.subsystems.Elevator;

/**
 *
 * @author Team 342
 */
public class ResetElevatorCommandGroup extends CommandGroup {
    
    public ResetElevatorCommandGroup() {
        Elevator elevator = Elevator.getInstance();
        if (elevator.getCurrentFloor() == Elevator.SHOOTER_FLOOR) {
            addSequential(new MoveToFloorCommand(Elevator.GROUND_FLOOR));
        }
        addSequential(new StopElevatorCommand());
    }
}
