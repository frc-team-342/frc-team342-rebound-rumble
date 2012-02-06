/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.first.team342.RobotMap;

/**
 *
 * @author abrightwell
 */
public class Conveyor extends Subsystem {
    private static final Conveyor INSTANCE = new Conveyor();
    private boolean isOn;
    
    private SpeedController conveyorMotor;
    
    private Conveyor() {
        this.conveyorMotor = new Victor(RobotMap.PWM_CHANNEL_CONVEYOR);
        isOn = false;
    }
    
    public static Conveyor getInstance() {
        return INSTANCE;
    }
    
    public void conveyorToggle() {
        if(isOn) {
            this.conveyorOff();
        } else {
            conveyorMotor.set(1.0);
            isOn = true;
        }
    }
    
    public void conveyorOff() {
        conveyorMotor.set(0.0);
        isOn = false;
    }
    
    public void conveyorReverse() {
        conveyorMotor.set(-1.0);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
