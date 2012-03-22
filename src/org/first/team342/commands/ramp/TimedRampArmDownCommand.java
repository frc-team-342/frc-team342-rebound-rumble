/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.commands.ramp;

import org.first.team342.commands.TimedCommand;
import org.first.team342.subsystems.Ramp;

/**
 *
 * @author abrightwell
 */
public class TimedRampArmDownCommand extends TimedCommand {

    private Ramp ramp;
    
    public TimedRampArmDownCommand(long duration) {
        super(duration);
        this.ramp = Ramp.getInstance();
        this.requires(this.ramp);
    }

    protected void execute() {
        System.out.println("Timed Ramp Arm Down");
        this.ramp.down();
    }

    protected void end() {
        this.ramp.stop();
    }

    protected void interrupted() {
        this.ramp.stop();
    }
}
