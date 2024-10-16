package eu.lilithmonodia.javasorts.sorts.practicalsorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * MergeSort class is a concrete implementation of the SortingAlgorithm abstract class. It uses the merge sort algorithm
 * to sort a given list of integers in ascending order.
 *
 * @see SortingAlgorithm
 */
public class MergeSort extends SortingAlgorithm {

    /**
     * Sorts a given list of integers using the merge sort algorithm.
     *
     * @param list the list of integers to be sorted
     */
    @Override
    public void sort(List<Integer> list) {
        mergeSort(list, 0, list.size() - 1);
    }

    /**
     * Sorts a given list of integers using the merge sort algorithm.
     *
     * @param list the list of integers to be sorted
     * @param low  the starting index of the subarray to be sorted
     * @param high the ending index of the subarray to be sorted
     */
    private void mergeSort(List<Integer> list, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(list, low, mid);
            mergeSort(list, mid + 1, high);
            merge(list, low, mid, high);
        }
    }

    /**
     * Merges two subarrays within a given list of integers.
     *
     * @param list the list of integers containing the subarrays to be merged
     * @param low  the starting index of the first subarray
     * @param mid  the ending index of the first subarray (exclusive)
     * @param high the ending index of the second subarray (inclusive)
     */
    private void merge(@NotNull List<Integer> list, int low, int mid, int high) {
        List<Integer> left = new ArrayList<>(list.subList(low, mid + 1));
        List<Integer> right = new ArrayList<>(list.subList(mid + 1, high + 1));

        int i = 0;
        int j = 0;
        int k = low;
        while (i < left.size() && j < right.size()) {
            list.set(k++, left.get(i) <= right.get(j) ? left.get(i++) : right.get(j++));
        }
        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            list.set(k++, right.get(j++));
        }
    }
}
