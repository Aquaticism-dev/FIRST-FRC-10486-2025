package org.weavers.drive;

/**
 * Stores states for each wheel individually
 *
 * @param spd
 * @param rot
 */
public record WheelState(Double spd, Double rot) {
}
