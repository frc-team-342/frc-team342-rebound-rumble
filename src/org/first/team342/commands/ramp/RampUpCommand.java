package org.first.team342.commands.ramp;

import org.first.team342.commands.CommandBase;
import org.first.team342.subsystems.Ramp;

/**
 * This command will move the ramp mechanism's lever upwards until it has reached
 * the fully retracted state.
 * 
 * @author FIRST Team 342
 */
public class RampUpCommand extends CommandBase {
    
    /**
     * The robot's ramp subsystem.
     */
    private Ramp ramp;
    
    /**
     * Create a new instance of this command.
     */
    public RampUpCommand() {
        this.ramp = Ramp.getInstance();
        this.requires(this.ramp);
    }

    /**
     * {@inheritDoc}
     */
    protected void initialize() {
    }

    /**
     * Moves the ramp mechanism up.
     */
    protected void execute() {
        this.ramp.up();
    }

    /**
     * Returns <code>true</code> if the ramp mechanism has reached its retracted
     * position.  Otherwise, returns <code>false</code>.
     * 
     * @return <code>true</code> if the ramp mechanism has reached its retracted
     * position.  Otherwise, returns <code>false</code>.
     */
    protected boolean isFinished() {
        return true;//this.ramp.isLeverRetracted();
    }

    /**
     * Called once {@link RampUpCommand#isFinished()} returns <code>true</code>.
     * Calling this method will stop the movement of the ramp mechanism.
     */
    protected void end() {
        //this.ramp.stop();
    }

    /**
     * {@inheritDoc}
     */
    protected void interrupted() {
    }
}
