package org.first.team342;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    

    // Speed Controller Channels for Module #1.
    public static final int PWM_CHANNEL_LEFT_FRONT = 1;
    public static final int PWM_CHANNEL_RIGHT_FRONT = 2;
    public static final int PWM_CHANNEL_RIGHT_REAR = 3;
    public static final int PWM_CHANNEL_LEFT_REAR = 4;
    public static final int PWM_CHANNEL_CONVEYOR = 5;
    public static final int PWM_CHANNEL_ELEVATOR = 6;
    public static final int PWM_CHANNEL_RAMP = 7;
    
    // CAN device constants.
    public static final int CAN_DEVICE_THROWER_MOTOR = 4;
    
    //Joystick ports.
    public static final int JOYSTICK_DRIVE_CONTROL = 1;
    
    //Analog constants
    public static final int DEFAULT_ANNALOG_SLOT = 1;
    
    //Gyro constants
    public static final int ANALOG_CHANNEL_GYRO = 1;
    
    //Digital contstants
    public static final int DIO_CHANNEL_RAMP_SWITCH = 1;
    public static final int DIO_CHANNEL_GROUND_FLOOR = 2;
    public static final int DIO_CHANNEL_MIDDLE_FLOOR = 3;
    public static final int DIO_CHANNEL_TOP_FLOOR = 4;
    public static final int DIO_CHANNEL_SHOOTING_FLOOR = 5;
    
}
