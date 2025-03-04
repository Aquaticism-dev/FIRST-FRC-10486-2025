package org.weavers.drive;

/**
 * Basically drives the robot.
 *
 * <p>Provides functions to control movement.
 */
public class DriveController {

    private final Double r; // halved side length

    public DriveController(Double l) {
        this.r = l / 2D;
    }

    /**
     * Combine forward and rotational velocities
     *
     * @param mvSpd Speed of moving forwards and backwards
     * @param rotSpdX Speed in the X-axis from rotating
     * @param rotSpdY Speed in the Y-axis from rotating
     * @return A {@link WheelState} Representing the state of the wheel
     */
    private static WheelState calculateWheelState(double mvSpd, double rotSpdX, double rotSpdY) {
        double totalX = mvSpd + rotSpdX;

        double spd = Math.sqrt(totalX * totalX + rotSpdY * rotSpdY);
        double rot = Math.atan2(rotSpdY, totalX);

        return new WheelState(spd, rot);
    }

    /**
     * Calculates ik for a desired speed and rotation.
     *
     * @param spd Desired speed, positive is forwards.
     * @param rot Desired rotation, positive is clockwise.
     * @return A {@link WheelsKinematics} as the result of ik calculations.
     */
    public WheelsKinematics inverseKinematics(Double spd, Double rot) {
        // Precompute rotation contributions
        double rotationalSpeedX = rot * this.r; // Rotational velocity in the X-direction
        double rotationalSpeedY = rot * this.r; // Rotational velocity in the Y-direction

        // Calculate wheel velocities and orientations
        WheelState frontLeft = calculateWheelState(spd, rotationalSpeedX, rotationalSpeedY);
        WheelState frontRight = calculateWheelState(spd, rotationalSpeedX, -rotationalSpeedY);
        WheelState backLeft = calculateWheelState(spd, -rotationalSpeedX, rotationalSpeedY);
        WheelState backRight = calculateWheelState(spd, -rotationalSpeedX, -rotationalSpeedY);

        return new WheelsKinematics(frontLeft, frontRight, backLeft, backRight);
    }

    /**
     * Actually drives the robot to achieve a desired speed and rotation.
     *
     * @param spd Desired speed, positive is forwards.
     * @param rot Desired rotation, positive is clockwise.
     */
    public void drive(Double spd, Double rot) {
        // TODO: Complete wheel control
    }

}
