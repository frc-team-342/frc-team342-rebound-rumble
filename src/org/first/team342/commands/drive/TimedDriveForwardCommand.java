/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.commands.drive;

import org.first.team342.commands.TimedCommand;
import org.first.team342.subsystems.Drive;

/**
 * Timed command to drive the robot in the forward direction.
 * 
 * @author FIRST Team 342
 */
public class TimedDriveForwardCommand extends TimedCommand {
    
    /*
     * The robot drive subsystem.
     */
    private Drive drive;
    
    /**
     * Create a new command set to drive the robot forward for the specified amount of time.
     * 
     * @param driveTime the time in milliseconds to drive forward.
     */
    public TimedDriveForwardCommand(long driveTime) {
        super(driveTime);
        this.drive = Drive.getInstance();
        this.requires(this.drive);
    }

    protected void execute() {
        this.drive.driveForward();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
