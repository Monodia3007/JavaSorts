package eu.lilithmonodia.javasorts.util;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeUtilsTest {

    @Test
    public void testFormatNanos() {
        assertEquals("500.00 ns", TimeUtils.formatNanos(500));
        assertEquals("1.00 us", TimeUtils.formatNanos(1_000));
        assertEquals("1.00 ms", TimeUtils.formatNanos(1_000_000));
        assertEquals("1.00 s", TimeUtils.formatNanos(1_000_000_000));
        assertEquals("1.00 min", TimeUtils.formatNanos(60_000_000_000L));
        assertEquals("1.00 h", TimeUtils.formatNanos(3_600_000_000_000L));
        assertEquals("1.00 d", TimeUtils.formatNanos(86_400_000_000_000L));
    }
}