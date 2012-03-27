/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.autonomous;

import org.first.team342.commands.autonomous.ShootOnly;
import org.first.team342.commands.drive.TimedDriveBackwardCommand;
import org.first.team342.commands.drive.TimedDriveForwardCommand;
import org.first.team342.commands.ramp.TimedRampArmDownCommand;

/**
 *
 * @author abrightwell
 */
public class ShootAndTipCommand extends ShootOnly {

    public ShootAndTipCommand() {
        super();
        this.addSequential(new TimedDriveBackwardCommand(1000, 0.75));
        this.addSequential(new TimedRampArmDownCommand(2000));
        this.addSequential(new TimedDriveForwardCommand(500, 0.75));
    }
}
