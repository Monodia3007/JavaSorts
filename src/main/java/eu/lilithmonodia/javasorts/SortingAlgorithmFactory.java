package eu.lilithmonodia.javasorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;
import eu.lilithmonodia.javasorts.sorts.impracticalsorts.*;
import eu.lilithmonodia.javasorts.sorts.practicalsorts.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The SortingAlgorithmFactory class is responsible for creating instances of different sorting algorithms. It provides
 * a method to retrieve a specific sorting algorithm based on its type.
 */
public class SortingAlgorithmFactory {
    private final Map<String, SortingAlgorithm> sortingAlgorithms = new HashMap<>();

    /**
     * The SortingAlgorithmFactory class is responsible for creating instances of various sorting algorithms. It
     * initializes a map of sorting algorithms with their corresponding names.
     */
    public SortingAlgorithmFactory() {
        initializeSortingAlgorithms();
    }

    private void initializeSortingAlgorithms() {
        sortingAlgorithms.put("QuickSort", new QuickSort());
        sortingAlgorithms.put("MergeSort", new MergeSort());
        sortingAlgorithms.put("HeapSort", new HeapSort());
        sortingAlgorithms.put("PancakeSort", new PancakeSort());
        sortingAlgorithms.put("BogoSort", new BogoSort());
        sortingAlgorithms.put("SlowSort", new SlowSort());
        sortingAlgorithms.put("BubbleSort", new BubbleSort());
        sortingAlgorithms.put("SelectionSort", new SelectionSort());
        sortingAlgorithms.put("InsertionSort", new InsertionSort());
        sortingAlgorithms.put("BozoSort", new BozoSort());
        sortingAlgorithms.put("StoogeSort", new StoogeSort());
        sortingAlgorithms.put("ShellSort", new ShellSort());
        sortingAlgorithms.put("RadixSort", new RadixSort());
        sortingAlgorithms.put("TimSort", new TimSort());
    }

    /**
     * Retrieves the sorting algorithm based on the given type.
     *
     * @param type the type of sorting algorithm to retrieve
     * @return the sorting algorithm corresponding to the given type
     */
    public SortingAlgorithm getSortingAlgorithm(String type) {
        SortingAlgorithm algorithm = sortingAlgorithms.get(type);
        if (algorithm == null) {
            throw new IllegalArgumentException("Invalid sorting algorithm type: " + type);
        }
        return algorithm;
    }
}