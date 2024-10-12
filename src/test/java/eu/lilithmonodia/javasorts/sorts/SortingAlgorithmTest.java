package eu.lilithmonodia.javasorts.sorts;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is used to test the SortingAlgorithm class.
 */
class SortingAlgorithmTest {
    private static final Logger logger = Logger.getLogger(SortingAlgorithmTest.class.getName());
    private final SortingAlgorithm sortingAlgorithm = new DummySortAlgorithm();

    /**
     * Test method to validate that generateRandomList method generates a list of the correct size.
     */
    @Test
    void generateRandomListShouldGenerateCorrectSizeList() {
        logger.info("Checking list generation correctness...");
        long size = 10;
        List<Integer> list = SortingAlgorithm.generateRandomList(size);

        assertEquals(size, list.size(), "The size of the list is not correct.");

        long distinctSize = list.stream().distinct().count();
        assertEquals(size, distinctSize, "All numbers in the list are not distinct.");

        assertTrue(SortingAlgorithm.generateRandomList(0).isEmpty(), "List of size 0 isn't empty.");
    }

    /**
     * Test method to validate that sort method sorts the given list in ascending order.
     */
    @Test
    void sortShouldSortList() {
        logger.info("Checking list sorting...");
        List<Integer> list = Arrays.asList(5, 3, 1, 4, 2);
        sortingAlgorithm.sort(list);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, list.toArray(new Integer[0]));
    }

    /**
     * Test method to validate that displayAndTime method modifies the given list. It calls the displayAndTime method of
     * the sortingAlgorithm with the list, algorithm name, and a StringBuilder. It then asserts that the modified list
     * is sorted in ascending order.
     */
    @Test
    void displayAndTimeShouldModifyList() {
        logger.info("Checking list modification through display & timing...");
        List<Integer> list = Arrays.asList(5, 3, 1, 4, 2);
        StringBuilder sb = new StringBuilder();
        StringBuilder rawDuration = new StringBuilder();
        sortingAlgorithm.displayAndTime(list, "DummySortAlgorithm", sb, rawDuration);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, list.toArray(new Integer[0]));

        // Verify that the rawDuration is a valid long number
        try {
            long duration = Long.parseLong(rawDuration.toString());
            assertTrue(duration > 0, "The duration should be greater than 0.");
        } catch (NumberFormatException e) {
            fail("The raw duration is not a valid long number.");
        }
    }

    /**
     * A dummy sorting algorithm implementation that sorts a list of integers using the {@link Collections#sort(List)}
     * method.
     *
     * <p>This class extends the {@link SortingAlgorithm} abstract class.</p>
     *
     * <p>Usage:
     *
     * <pre>{@code
     * DummySortAlgorithm algorithm = new DummySortAlgorithm();
     * List<Integer> myList = Arrays.asList(5, 2, 9, 1, 3);
     * algorithm.sort(myList);
     * }</pre>
     * </p>
     */
    private static class DummySortAlgorithm extends SortingAlgorithm {
        /**
         * Sorts the given list in ascending order.
         *
         * @param list the list to be sorted
         */
        @Override
        public void sort(List<Integer> list) {
            Collections.sort(list);
        }
    }
}