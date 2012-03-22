/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.first.team342.commands.drive.TimedDriveBackwardCommand;
import org.first.team342.commands.drive.TimedDriveForwardCommand;
import org.first.team342.commands.elevator.MoveToFloorCommand;
import org.first.team342.commands.ramp.TimedRampArmDownCommand;
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
        this.addSequential(new TimedDriveBackwardCommand(2000));
        this.addSequential(new TimedRampArmDownCommand(2000));
        this.addSequential(new TimedDriveForwardCommand(500));
    }
}
