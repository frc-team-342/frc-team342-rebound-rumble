/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.first.team342.commands.elevator.MoveToFloorCommand;
import org.first.team342.commands.thrower.FlywheelForwardCommand;
import org.first.team342.commands.thrower.FlywheelStopCommand;

/**
 *
 * @author abrightwell
 */
public class ShootAndTipCommand extends CommandGroup {

    public ShootAndTipCommand() {
        this.addParallel(new FlywheelForwardCommand());
        this.addSequential(new MoveToFloorCommand(2));
        this.addSequential(new WaitCommand(1.0));
        this.addSequential(new MoveToFloorCommand(1));
        this.addSequential(new WaitCommand(1.0));
        this.addSequential(new FlywheelStopCommand());
        // this.addSequential(); // move the robot backwards.
        // this.addSequential(); // move ramp arm down.
    }
}
