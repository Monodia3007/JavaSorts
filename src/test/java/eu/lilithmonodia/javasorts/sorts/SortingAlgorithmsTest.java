package eu.lilithmonodia.javasorts.sorts;

import eu.lilithmonodia.javasorts.SortingAlgorithmFactory;
import eu.lilithmonodia.javasorts.sorts.practicalsorts.TimSort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class contains test cases for sorting algorithms.
 */
class SortingAlgorithmsTest {
    private static final Logger logger = Logger.getLogger(SortingAlgorithmsTest.class.getName());

    private static final List<String> ALGORITHMS = Arrays.asList(
            "QuickSort", "MergeSort", "HeapSort", "PancakeSort", "BogoSort", "SlowSort", "BubbleSort",
            "SelectionSort", "InsertionSort", "BozoSort", "StoogeSort", "ShellSort", "RadixSort", "TimSort",
            "ParallelMergeSort"
    );

    private SortingAlgorithmFactory factory;

    /**
     * Sets up the test environment before each test case execution.
     */
    @BeforeEach
    public void setup() {
        factory = new SortingAlgorithmFactory();
    }

    /**
     * This method is used to test the sorting algorithm implemented by the SortingAlgorithmFactory. It tests the
     * sorting algorithm for each algorithm available in the ALGORITHMS list, using a sample list of integers. The
     * method compares the sorted copy of the list with the expected sorted result and asserts that they are equal. Note
     * that a copy of the list is created before sorting, as each sorting algorithm modifies the list.
     */
    @Test
    void testSort() {
        logger.info("Running sorting test...");
        List<Integer> list = Arrays.asList(5, 3, 1, 4, 2);

        for (String algorithm : ALGORITHMS) {
            SortingAlgorithm sorter = factory.getSortingAlgorithm(algorithm);
            List<Integer> copy = new ArrayList<>(list); // Creating a copy of list as each sorter modifies the list.
            sorter.sort(copy);

            assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, copy.toArray(new Integer[0]),
                    "Failed for algorithm: " + algorithm);
        }
    }

    /**
     * This method is used to test the sorting algorithm implemented by the SortingAlgorithmFactory on an empty list. It
     * tests the sorting algorithm for each algorithm available in the ALGORITHMS list, using an empty list. The method
     * asserts that the sorted copy of the empty list is also empty. Note that a copy of the list is created before
     * sorting, as each sorting algorithm modifies the list.
     */
    @Test
    void testSortOnEmptyList() {
        logger.info("Running sorting test on an empty list...");
        List<Integer> list = List.of();

        for (String algorithm : ALGORITHMS) {
            SortingAlgorithm sorter = factory.getSortingAlgorithm(algorithm);
            List<Integer> copy = new ArrayList<>(list); // Creating a copy of list as each sorter modifies the list.
            sorter.sort(copy);

            assertTrue(copy.isEmpty(), "List should be empty after sorting with algorithm: " + algorithm);
        }
    }

    /**
     * This method is used to test the sorting algorithm implemented by the SortingAlgorithmFactory on a list with a
     * single item. It tests the sorting algorithm for each algorithm available in the ALGORITHMS list, using a list
     * with a single item. The method asserts that the sorted copy of the list contains only the single item in the
     * correct order. Note that a copy of the list is created before sorting, as each sorting algorithm modifies the
     * list.
     */
    @Test
    void testSortOnSingleItemList() {
        logger.info("Running sorting test on a single item list...");
        List<Integer> list = List.of(3);

        for (String algorithm : ALGORITHMS) {
            SortingAlgorithm sorter = factory.getSortingAlgorithm(algorithm);
            List<Integer> copy = new ArrayList<>(list); // Creating a copy of list as each sorter modifies the list.
            sorter.sort(copy);

            assertArrayEquals(new Integer[]{3}, copy.toArray(new Integer[0]),
                    "Failed for algorithm: " + algorithm);
        }
    }

    /**
     * Additional tests specific to TimSort, especially to test the merge functionality.
     */
    @Test
    void testTimSortWithLargeList() {
        logger.info("Running TimSort test with a large list to cover merging...");
        TimSort sorter = new TimSort();

        // Creating a list large enough to ensure merge functionality is invoked
        List<Integer> list = new ArrayList<>();
        for (int i = 1000; i >= 1; i--) {
            list.add(i);
        }

        sorter.sort(list);

        List<Integer> expected = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            expected.add(i);
        }

        assertArrayEquals(expected.toArray(new Integer[0]), list.toArray(new Integer[0]), "TimSort merge failed.");
    }

    /**
     * Additional tests specific to ParallelMergeSort
     */
    @Test
    void testParallelMergeSort() {
        logger.info("Running specific test for ParallelMergeSort...");
        SortingAlgorithm sorter = new eu.lilithmonodia.javasorts.sorts.practicalsorts.ParallelMergeSort();

        List<Integer> list = Arrays.asList(5, 3, 1, 4, 2);
        sorter.sort(list);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, list.toArray(new Integer[0]), "ParallelMergeSort failed.");

        list = List.of();
        sorter.sort(list);
        assertTrue(list.isEmpty(), "ParallelMergeSort should handle empty list.");

        list = List.of(3);
        sorter.sort(list);
        assertArrayEquals(new Integer[]{3}, list.toArray(new Integer[0]), "ParallelMergeSort failed on single item list.");

        // Test with a larger list to ensure thorough testing
        List<Integer> largeList = new ArrayList<>();
        for (int i = 1000; i >= 1; i--) {
            largeList.add(i);
        }
        sorter.sort(largeList);

        List<Integer> expected = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            expected.add(i);
        }
        assertArrayEquals(expected.toArray(new Integer[0]), largeList.toArray(new Integer[0]), "ParallelMergeSort failed on large list.");
    }
}