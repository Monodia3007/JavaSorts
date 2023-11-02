package eu.lilithmonodia.javasorts.util;


import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The TimeUtilsTest class is responsible for testing the TimeUtils class.
 */
public class TimeUtilsTest {
    private static final Logger logger = Logger.getLogger(TimeUtilsTest.class.getName());

    /**
     * This method is used to test the formatNanos method of the TimeUtils class.
     * It tests the formatNanos method for various values and asserts that the
     * formatted string is correct.
     */
    @Test
    public void testFormatNanos() {
        logger.info("Running test for formatNanos method...");
        assertEquals("500.00 ns", TimeUtils.formatNanos(500));
        assertEquals("1.00 us", TimeUtils.formatNanos(1_000));
        assertEquals("1.00 ms", TimeUtils.formatNanos(1_000_000));
        assertEquals("1.00 s", TimeUtils.formatNanos(1_000_000_000));
        assertEquals("1.00 min", TimeUtils.formatNanos(60_000_000_000L));
        assertEquals("1.00 h", TimeUtils.formatNanos(3_600_000_000_000L));
        assertEquals("1.00 d", TimeUtils.formatNanos(86_400_000_000_000L));
    }
}