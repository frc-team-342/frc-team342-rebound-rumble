/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.commands.drive;

import org.first.team342.commands.CommandBase;
import org.first.team342.subsystems.Drive;

/**
 *
 * @author FIRST Team 342
 */
public class GyroSetCommand extends CommandBase {
    private Drive drive = Drive.getInstance();
    private boolean finished = false;
    
    public GyroSetCommand() {
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        this.drive.setGyro();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        this.finished = true;
    }
}
