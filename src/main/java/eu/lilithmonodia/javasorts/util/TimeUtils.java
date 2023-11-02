package eu.lilithmonodia.javasorts.util;

import java.util.concurrent.TimeUnit;

public class TimeUtils {

    /**
     * Formats the given duration in nanoseconds into a human-readable string representation.
     *
     * @param nanos the duration in nanoseconds to format
     *
     * @return the formatted string representation of the duration
     */
    public static String formatNanos(long nanos) {
        if (nanos >= 86_400_000_000_000L) {
            return String.format("%.2f d", (double) TimeUnit.NANOSECONDS.toDays(nanos));
        } else if (nanos >= 3_600_000_000_000L) {
            return String.format("%.2f h", (double) TimeUnit.NANOSECONDS.toHours(nanos));
        } else if (nanos >= 60_000_000_000L) {
            return String.format("%.2f min", (double) TimeUnit.NANOSECONDS.toMinutes(nanos));
        } else if (nanos >= 1_000_000_000) {
            return String.format("%.2f s", (double) TimeUnit.NANOSECONDS.toSeconds(nanos));
        } else if (nanos >= 1_000_000) {
            return String.format("%.2f ms", (double) TimeUnit.NANOSECONDS.toMillis(nanos));
        } else if (nanos >= 1_000) {
            return String.format("%.2f us", (double) TimeUnit.NANOSECONDS.toMicros(nanos));
        } else {
            return String.format("%.2f ns", (double) nanos);
        }
    }
}