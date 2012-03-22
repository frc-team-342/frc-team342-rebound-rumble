package org.first.team342.commands.drive;

import org.first.team342.commands.TimedCommand;
import org.first.team342.subsystems.Drive;

/**
 * Timed command to drive the robot in the backward direction.
 * 
 * @author FIRST Team 342
 */
public class TimedDriveBackwardCommand extends TimedCommand {

    /*
     * The robot drive subsystem.
     */
    private Drive drive;
    
    /**
     * Create a new command set to drive for the specified amount of time.
     * 
     * @param driveTime the time in milliseconds to drive backwards.
     */
    public TimedDriveBackwardCommand(long driveTime) {
        super(driveTime);
        this.drive = Drive.getInstance();
        this.requires(this.drive);
    }

    protected void execute() {
        System.out.println("Timed Drive Backward");
        this.drive.driveBackward();
    }

    protected void end() {
        this.drive.stop();
    }

    protected void interrupted() {
        this.drive.stop();
    }
}
