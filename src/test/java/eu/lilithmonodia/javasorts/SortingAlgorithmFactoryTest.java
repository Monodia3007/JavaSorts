package eu.lilithmonodia.javasorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;
import eu.lilithmonodia.javasorts.sorts.impracticalsorts.*;
import eu.lilithmonodia.javasorts.sorts.practicalsorts.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortingAlgorithmFactoryTest {

    @Test
    void testGetSortingAlgorithm_QuickSort() {
        SortingAlgorithmFactory factory = new SortingAlgorithmFactory();
        SortingAlgorithm algorithm = factory.getSortingAlgorithm("QuickSort");
        assertInstanceOf(QuickSort.class, algorithm);
    }

    @Test
    void testGetSortingAlgorithm_MergeSort() {
        SortingAlgorithmFactory factory = new SortingAlgorithmFactory();
        SortingAlgorithm algorithm = factory.getSortingAlgorithm("MergeSort");
        assertInstanceOf(MergeSort.class, algorithm);
    }

    @Test
    void testGetSortingAlgorithm_HeapSort() {
        SortingAlgorithmFactory factory = new SortingAlgorithmFactory();
        SortingAlgorithm algorithm = factory.getSortingAlgorithm("HeapSort");
        assertInstanceOf(HeapSort.class, algorithm);
    }

    @Test
    void testGetSortingAlgorithm_PancakeSort() {
        SortingAlgorithmFactory factory = new SortingAlgorithmFactory();
        SortingAlgorithm algorithm = factory.getSortingAlgorithm("PancakeSort");
        assertInstanceOf(PancakeSort.class, algorithm);
    }

    @Test
    void testGetSortingAlgorithm_BogoSort() {
        SortingAlgorithmFactory factory = new SortingAlgorithmFactory();
        SortingAlgorithm algorithm = factory.getSortingAlgorithm("BogoSort");
        assertInstanceOf(BogoSort.class, algorithm);
    }

    @Test
    void testGetSortingAlgorithm_SlowSort() {
        SortingAlgorithmFactory factory = new SortingAlgorithmFactory();
        SortingAlgorithm algorithm = factory.getSortingAlgorithm("SlowSort");
        assertInstanceOf(SlowSort.class, algorithm);
    }

    @Test
    void testGetSortingAlgorithm_BubbleSort() {
        SortingAlgorithmFactory factory = new SortingAlgorithmFactory();
        SortingAlgorithm algorithm = factory.getSortingAlgorithm("BubbleSort");
        assertInstanceOf(BubbleSort.class, algorithm);
    }

    @Test
    void testGetSortingAlgorithm_SelectionSort() {
        SortingAlgorithmFactory factory = new SortingAlgorithmFactory();
        SortingAlgorithm algorithm = factory.getSortingAlgorithm("SelectionSort");
        assertInstanceOf(SelectionSort.class, algorithm);
    }

    @Test
    void testGetSortingAlgorithm_InsertionSort() {
        SortingAlgorithmFactory factory = new SortingAlgorithmFactory();
        SortingAlgorithm algorithm = factory.getSortingAlgorithm("InsertionSort");
        assertInstanceOf(InsertionSort.class, algorithm);
    }

    @Test
    void testGetSortingAlgorithm_BozoSort() {
        SortingAlgorithmFactory factory = new SortingAlgorithmFactory();
        SortingAlgorithm algorithm = factory.getSortingAlgorithm("BozoSort");
        assertInstanceOf(BozoSort.class, algorithm);
    }

    @Test
    void testGetSortingAlgorithm_StoogeSort() {
        SortingAlgorithmFactory factory = new SortingAlgorithmFactory();
        SortingAlgorithm algorithm = factory.getSortingAlgorithm("StoogeSort");
        assertInstanceOf(StoogeSort.class, algorithm);
    }

    @Test
    void testGetSortingAlgorithm_ShellSort() {
        SortingAlgorithmFactory factory = new SortingAlgorithmFactory();
        SortingAlgorithm algorithm = factory.getSortingAlgorithm("ShellSort");
        assertInstanceOf(ShellSort.class, algorithm);
    }

    @Test
    void testGetSortingAlgorithm_RadixSort() {
        SortingAlgorithmFactory factory = new SortingAlgorithmFactory();
        SortingAlgorithm algorithm = factory.getSortingAlgorithm("RadixSort");
        assertInstanceOf(RadixSort.class, algorithm);
    }

    @Test
    void testGetSortingAlgorithm_TimSort() {
        SortingAlgorithmFactory factory = new SortingAlgorithmFactory();
        SortingAlgorithm algorithm = factory.getSortingAlgorithm("TimSort");
        assertInstanceOf(TimSort.class, algorithm);
    }

    @Test
    void testGetSortingAlgorithm_InvalidType() {
        SortingAlgorithmFactory factory = new SortingAlgorithmFactory();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> factory.getSortingAlgorithm("InvalidSort"));
        assertEquals("Invalid sorting algorithm type: InvalidSort", exception.getMessage());
    }

    @Test
    void testGetSortingAlgorithm_ParallelMergeSort() {
        SortingAlgorithmFactory factory = new SortingAlgorithmFactory();
        SortingAlgorithm algorithm = factory.getSortingAlgorithm("ParallelMergeSort");
        assertInstanceOf(ParallelMergeSort.class, algorithm);
    }
}