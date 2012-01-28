package org.first.team342;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    

    // Speed Controller Channels for Module #1.
    public static final int CAN_DEVICE_LEFT_FRONT = 5;
    public static final int CAN_DEVICE_LEFT_REAR = 8;
    public static final int CAN_DEVICE_RIGHT_FRONT = 6;
    public static final int CAN_DEVICE_RIGHT_REAR = 7;
    public static final int CAN_DEVICE_THROWER_MOTOR = 3;
    
    //Joystick ports.
    public static final int JOYSTICK_DRIVE_CONTROL = 1;
    
    //Annanlog constants
    public static final int DEFAULT_ANNALOG_SLOT = 1;
    
    //Gyro constants
    public static final int ANALOG_CHANNEL_GYRO = 1;
    
}
