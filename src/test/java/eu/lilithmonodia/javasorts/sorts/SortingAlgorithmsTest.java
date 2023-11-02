package eu.lilithmonodia.javasorts.sorts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import eu.lilithmonodia.javasorts.SortingAlgorithmFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortingAlgorithmsTest {

    private static final List<String> ALGORITHMS = Arrays.asList(
            "QuickSort", "MergeSort", "HeapSort", "PancakeSort", "BogoSort", "SlowSort", "BubbleSort");

    private SortingAlgorithmFactory factory;

    @BeforeEach
    public void setup() {
        factory = new SortingAlgorithmFactory();
    }

    @Test
    public void testSort() {
        List<Integer> list = Arrays.asList(5, 3, 1, 4, 2);
        for (String algorithm : ALGORITHMS) {
            SortingAlgorithm sorter = factory.getSortingAlgorithm(algorithm);
            List<Integer> copy =  new ArrayList<>(list); // Creating a copy of list as each sorter modifies the list.
            sorter.sort(copy);
            assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, copy.toArray(new Integer[0]));
        }
    }

    @Test
    public void testSortOnEmptyList() {
        List<Integer> list = Arrays.asList();
        for (String algorithm : ALGORITHMS) {
            SortingAlgorithm sorter = factory.getSortingAlgorithm(algorithm);
            List<Integer> copy =  new ArrayList<>(list); // Creating a copy of list as each sorter modifies the list.
            sorter.sort(copy);
            assertTrue(copy.isEmpty());
        }
    }

    @Test
    public void testSortOnSingleItemList() {
        List<Integer> list = Arrays.asList(3);
        for (String algorithm : ALGORITHMS) {
            SortingAlgorithm sorter = factory.getSortingAlgorithm(algorithm);
            List<Integer> copy =  new ArrayList<>(list); // Creating a copy of list as each sorter modifies the list.
            sorter.sort(copy);
            assertArrayEquals(new Integer[]{3}, copy.toArray(new Integer[0]));
        }
    }
}
