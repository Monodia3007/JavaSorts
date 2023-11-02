package eu.lilithmonodia.javasorts.sorts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import eu.lilithmonodia.javasorts.SortingAlgorithmFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class contains test cases for sorting algorithms.
 */
public class SortingAlgorithmsTest {
    private static final Logger logger = Logger.getLogger(SortingAlgorithmsTest.class.getName());

    private static final List<String> ALGORITHMS = Arrays.asList(
            "QuickSort", "MergeSort", "HeapSort", "PancakeSort", "BogoSort", "SlowSort", "BubbleSort");

    private SortingAlgorithmFactory factory;

    /**
     * Sets up the test environment before each test case execution.
     */
    @BeforeEach
    public void setup() {
        factory = new SortingAlgorithmFactory();
    }

    /**
     * This method is used to test the sorting algorithm implemented by the SortingAlgorithmFactory.
     * It tests the sorting algorithm for each algorithm available in the ALGORITHMS list, using a sample list of integers.
     * The method compares the sorted copy of the list with the expected sorted result and asserts that they are equal.
     * Note that a copy of the list is created before sorting, as each sorting algorithm modifies the list.
     */
    @Test
    public void testSort() {
        logger.info("Running sorting test...");
        List<Integer> list = Arrays.asList(5, 3, 1, 4, 2);
        for (String algorithm : ALGORITHMS) {
            SortingAlgorithm sorter = factory.getSortingAlgorithm(algorithm);
            List<Integer> copy =  new ArrayList<>(list); // Creating a copy of list as each sorter modifies the list.
            sorter.sort(copy);
            assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, copy.toArray(new Integer[0]));
        }
    }

    /**
     * This method is used to test the sorting algorithm implemented by the SortingAlgorithmFactory on an empty list.
     * It tests the sorting algorithm for each algorithm available in the ALGORITHMS list, using an empty list.
     * The method asserts that the sorted copy of the empty list is also empty.
     * Note that a copy of the list is created before sorting, as each sorting algorithm modifies the list.
     */
    @Test
    public void testSortOnEmptyList() {
        logger.info("Running sorting test on an empty list...");
        List<Integer> list = Arrays.asList();
        for (String algorithm : ALGORITHMS) {
            SortingAlgorithm sorter = factory.getSortingAlgorithm(algorithm);
            List<Integer> copy =  new ArrayList<>(list); // Creating a copy of list as each sorter modifies the list.
            sorter.sort(copy);
            assertTrue(copy.isEmpty());
        }
    }

    /**
     * This method is used to test the sorting algorithm implemented by the SortingAlgorithmFactory on a list with a single item.
     * It tests the sorting algorithm for each algorithm available in the ALGORITHMS list, using a list with a single item.
     * The method asserts that the sorted copy of the list contains only the single item in the correct order.
     * Note that a copy of the list is created before sorting, as each sorting algorithm modifies the list.
     */
    @Test
    public void testSortOnSingleItemList() {
        logger.info("Running sorting test on a single item list...");
        List<Integer> list = Arrays.asList(3);
        for (String algorithm : ALGORITHMS) {
            SortingAlgorithm sorter = factory.getSortingAlgorithm(algorithm);
            List<Integer> copy =  new ArrayList<>(list); // Creating a copy of list as each sorter modifies the list.
            sorter.sort(copy);
            assertArrayEquals(new Integer[]{3}, copy.toArray(new Integer[0]));
        }
    }
}
