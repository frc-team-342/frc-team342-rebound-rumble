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
    public static final int PWM_CHANNEL_LEFT_REAR = 2;
    public static final int PWM_CHANNEL_RIGHT_FRONT = 3;
    public static final int PWM_CHANNEL_RIGHT_REAR = 4;
    public static final int PWM_CHANNEL_THROWER_MOTOR = 10;
    
    //Joystick ports.
    public static final int JOYSTICK_DRIVE_CONTROL = 1;
}
