package eu.lilithmonodia.javasorts.util;

import java.text.DecimalFormat;

/**
 * A utility class for formatting durations in nanoseconds into a human-readable string representation.
 */
public class TimeUtils {

    private TimeUtils() {}

    /**
     * Formats the given duration in nanoseconds into a human-readable string representation.
     *
     * @param nanos the duration in nanoseconds to format
     *
     * @return the formatted string representation of the duration
     */
    public static String formatNanos(long nanos) {
        DecimalFormat df = new DecimalFormat("0.00");

        double scaledNanos = nanos;

        if (scaledNanos >= 86_400_000_000_000L) {
            scaledNanos /= 86_400_000_000_000L;
            return df.format(scaledNanos) + " d";
        } else if (scaledNanos >= 3_600_000_000_000L) {
            scaledNanos /= 3_600_000_000_000L;
            return df.format(scaledNanos) + " h";
        } else if (scaledNanos >= 60_000_000_000L) {
            scaledNanos /= 60_000_000_000L;
            return df.format(scaledNanos) + " min";
        } else if (scaledNanos >= 1_000_000_000) {
            scaledNanos /= 1_000_000_000;
            return df.format(scaledNanos) + " s";
        } else if (scaledNanos >= 1_000_000) {
            scaledNanos /= 1_000_000;
            return df.format(scaledNanos) + " ms";
        } else if (scaledNanos >= 1_000) {
            scaledNanos /= 1_000;
            return df.format(scaledNanos) + " us";
        } else {
            scaledNanos /= 1;
            return df.format(scaledNanos) + " ns";
        }
    }
}