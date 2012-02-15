package org.first.team342;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    
    
    // CAN device constants.
    public static final int CAN_DEVICE_THROWER_MOTOR_MASTER = 4;
    public static final int CAN_DEVICE_THROWER_MOTOR_SLAVE = 3;
    
    // Speed Controller Channels.
    public static final int CAN_DEVICE_LEFT_FRONT_DRIVE_MOTOR = 5;
    public static final int CAN_DEVICE_RIGHT_FRONT_DRIVE_MOTOR = 6;
    public static final int CAN_DEVICE_RIGHT_REAR_DRIVE_MOTOR = 7;
    public static final int CAN_DEVICE_LEFT_REAR_DRIVE_MOTOR = 8;
    public static final int CAN_DEVICE_THROWER_MOTOR = 4;
    
    // Victor channels
    public static final int PWM_CHANNEL_CONVEYOR = 1;
    public static final int PWM_CHANNEL_ELEVATOR = 2;
    public static final int PWM_CHANNEL_RAMP = 3;
    
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
