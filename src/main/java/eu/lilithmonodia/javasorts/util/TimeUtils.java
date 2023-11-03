package eu.lilithmonodia.javasorts.util;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

/**
 * A utility class for formatting durations in nanoseconds into a human-readable string representation.
 */
public class TimeUtils {

    /**
     * Formats the given duration in nanoseconds into a human-readable string representation.
     *
     * @param nanos the duration in nanoseconds to format
     *
     * @return the formatted string representation of the duration
     */
    public static String formatNanos(long nanos) {
        DecimalFormat df = new DecimalFormat("0.00");

        if (nanos >= 86_400_000_000_000L) {
            return df.format((double) TimeUnit.NANOSECONDS.toDays(nanos)) + " d";
        } else if (nanos >= 3_600_000_000_000L) {
            return df.format((double) TimeUnit.NANOSECONDS.toHours(nanos)) + " h";
        } else if (nanos >= 60_000_000_000L) {
            return df.format((double) TimeUnit.NANOSECONDS.toMinutes(nanos)) + " min";
        } else if (nanos >= 1_000_000_000) {
            return df.format((double) TimeUnit.NANOSECONDS.toSeconds(nanos)) + " s";
        } else if (nanos >= 1_000_000) {
            return df.format((double) TimeUnit.NANOSECONDS.toMillis(nanos)) + " ms";
        } else if (nanos >= 1_000) {
            return df.format((double) TimeUnit.NANOSECONDS.toMicros(nanos)) + " us";
        } else {
            return df.format((double) nanos) + " ns";
        }
    }
}