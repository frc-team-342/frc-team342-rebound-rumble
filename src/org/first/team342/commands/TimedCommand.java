/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.commands;

/**
 *
 * @author abrightwell
 */
public abstract class TimedCommand extends CommandBase {
    
    private long commandTime;
    
    private long startTime;
    
    
    
    public TimedCommand(long commandTime) {
        this.commandTime = commandTime;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.startTime = System.currentTimeMillis();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - this.startTime;
        return elapsedTime >= this.commandTime;
    }
}
