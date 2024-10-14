package eu.lilithmonodia.javasorts.util;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

/**
 * A utility class for formatting durations in nanoseconds into a human-readable string representation.
 */
public class TimeUtils {

    /**
     * A utility class for handling time-related operations.
     * This class is not meant to be instantiated.
     * <p>
     * The constructor is private and throws an exception to prevent instantiation.
     */
    private TimeUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Formats the given duration in nanoseconds into a human-readable string representation.
     *
     * @param nanos the duration in nanoseconds to format
     * @return the formatted string representation of the duration
     */
    public static @NotNull String formatNanos(long nanos) {
        DecimalFormat df = new DecimalFormat("0.00");

        final long[] thresholds = {
                86_400_000_000_000L, // days
                3_600_000_000_000L,  // hours
                60_000_000_000L,     // minutes
                1_000_000_000L,      // seconds
                1_000_000L,          // milliseconds
                1_000L,              // microseconds
                1                   // nanoseconds
        };

        final String[] units = {
                " d",
                " h",
                " min",
                " s",
                " ms",
                " us",
                " ns"
        };

        double scaledNanos = nanos;
        for (int i = 0; i < thresholds.length; i++) {
            if (scaledNanos >= thresholds[i]) {
                scaledNanos /= thresholds[i];
                return df.format(scaledNanos) + units[i];
            }
        }

        // Fallback shouldn't reach here unless input is less than 1 ns
        return df.format(scaledNanos) + " ns";
    }
}