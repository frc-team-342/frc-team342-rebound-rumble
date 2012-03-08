/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first.team342;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * Random utilities for robot operation.
 * 
 * @author FIRST Team 342
 */
public class RobotUtilities {

    /**
     * Initialize a CAN Jaguar device with the given device address.
     * 
     * @param deviceNumber the CAN device address.
     * 
     * @return the initialized CAN device.  If an error occurs then <code>null<code> is returned.
     */
    public static CANJaguar initializeCANJaguar(int deviceNumber) {
        CANJaguar jaguar = null;

        try {
            jaguar = new CANJaguar(deviceNumber);
        } catch (CANTimeoutException ex) {
            System.out.println(ex.getMessage() + " - Device Number: " + deviceNumber);
        }

        return jaguar;
    }
}
