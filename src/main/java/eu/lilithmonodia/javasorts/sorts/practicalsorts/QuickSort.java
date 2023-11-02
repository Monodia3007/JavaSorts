package eu.lilithmonodia.javasorts.sorts.practicalsorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;

import java.util.Collections;
import java.util.List;


/**
 * The QuickSort class is a subclass of SortingAlgorithm.
 * It provides an implementation of the QuickSort algorithm to sort a list of integers.
 */
public class QuickSort extends SortingAlgorithm {

    /**
     * Sorts a list of integers in ascending order using the QuickSort algorithm.
     *
     * @param list the list of integers to be sorted
     */
    @Override
    public void sort(List<Integer> list) {
        quickSort(list, 0, list.size() - 1);
    }

    /**
     * Recursively sorts a list of integers in ascending order using the QuickSort algorithm.
     *
     * @param list the list of integers to be sorted
     * @param low  the starting index of the sublist to be sorted
     * @param high the ending index of the sublist to be sorted
     */
    private void quickSort(List<Integer> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    /**
     * Partitions the list using the last element as pivot.
     *
     * @param list the list to be partitioned
     * @param low  the starting index of the partition range (inclusive)
     * @param high the ending index of the partition range (inclusive)
     * @return the index of the pivot element after partitioning
     */
    private int partition(List<Integer> list, int low, int high) {
        int pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j) < pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }
}