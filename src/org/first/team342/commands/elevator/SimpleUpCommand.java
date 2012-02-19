/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.commands.elevator;

import org.first.team342.commands.CommandBase;
import org.first.team342.subsystems.Elevator;

/**
 *
 * @author abrightwell
 */
public class SimpleUpCommand extends CommandBase {

    private Elevator elevator;
    
    protected void initialize() {
        this.elevator = Elevator.getInstance();
        this.requires(this.elevator);
    }

    protected void execute() {
        this.elevator.up();
    }

    protected void end() {
        this.elevator.stop();
    }

    protected void interrupted() {
    }
}
