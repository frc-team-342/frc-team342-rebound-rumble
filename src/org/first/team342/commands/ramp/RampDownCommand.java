package org.first.team342.commands.ramp;

import org.first.team342.commands.CommandBase;
import org.first.team342.subsystems.Ramp;

/**
 * This command will move the ramp mechanism's lever downwards.
 * 
 * @author FIRST Team 342
 */
public class RampDownCommand extends CommandBase {
    
    /**
     * The robot's ramp system.
     */
    private Ramp ramp;
    
    /**
     * Create a new instance of this command.
     */
    public RampDownCommand() {
        this.ramp = Ramp.getInstance();
        this.requires(this.ramp);
    }

    /**
     * {@inheritDoc}
     */
    protected void initialize() {
    }

    /**
     * Moves the ramp mechanism down.  This method is called repeatedly until 
     * {@link RampDownCommand#isFinished() returns <code>true</code>.
     */
    protected void execute() {
        this.ramp.down();
    }

    /**
     * Always returns <code>true</code> since there is no condition to determine
     * whether or not the command should stop running.
     * @return always <code>true</code> as 
     */
    protected boolean isFinished() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    protected void end() {
    }

    /**
     * {@inheritDoc}
     */
    protected void interrupted() {
    }
}
