package org.weavers.drive;

/**
 * Stores kinematic information for all the wheels
 *
 * @param ul Upper-left wheel.
 * @param ur Upper-right wheel.
 * @param ll Lower-left wheel.
 * @param lr Lower-right wheel.
 */
public record WheelsKinematics(WheelState ul, WheelState ur, WheelState ll, WheelState lr) {
}
