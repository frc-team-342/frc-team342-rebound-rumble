/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.first.team342.commands.elevator.MoveToFloorCommand;
import org.first.team342.commands.thrower.FlywheelForwardCommand;
import org.first.team342.commands.thrower.FlywheelStopCommand;

/**
 *
 * @author Team 342
 */
public class ShootOnly extends CommandGroup {
    
    public ShootOnly() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        addParallel(new FlywheelForwardCommand(2500));
        addSequential(new WaitCommand(2));
        addSequential(new MoveToFloorCommand(2));
        addSequential(new WaitCommand(2));
        addSequential(new MoveToFloorCommand(3));
        addParallel(new MoveToFloorCommand(0));
        addSequential(new FlywheelStopCommand());
    }
}
