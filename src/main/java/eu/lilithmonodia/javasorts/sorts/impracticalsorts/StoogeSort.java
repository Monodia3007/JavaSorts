package eu.lilithmonodia.javasorts.sorts.impracticalsorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;

import java.util.List;

/**
 * Implements the StoogeSort algorithm for sorting a list of integers in non-decreasing order.
 * <p>
 * This class extends the SortingAlgorithm abstract class and overrides the sort() method to implement the StoogeSort algorithm.
 */
public class StoogeSort extends SortingAlgorithm {
    /**
     * Sorts a list of integers in ascending order using the Stooge Sort algorithm.
     *
     * @param list the list of integers to be sorted
     */
    @Override
    public void sort(List<Integer> list) {
        stoogeSort(list, 0, list.size() - 1);
    }

    /**
     * Sorts a list of integers in ascending order using the Stooge Sort algorithm
     * within the given range [l, h].
     *
     * @param list the list of integers to be sorted
     * @param l    the starting index of the range to be sorted (inclusive)
     * @param h    the ending index of the range to be sorted (inclusive)
     */
    public void stoogeSort(List<Integer> list, int l, int h) {
        if (l >= h)
            return;

        // If first element is smaller than last,
        // swap them
        if (list.get(l) > list.get(h)) {
            // swap l and h
            int t = list.get(l);
            list.set(l, list.get(h));
            list.set(h, t);
        }

        // If there are more than 2 elements in
        // the array
        if (h - l + 1 > 2) {
            int t = (h - l + 1) / 3;

            // Recursively sort first 2/3 elements
            stoogeSort(list, l, h - t);

            // Recursively sort last 2/3 elements
            stoogeSort(list, l + t, h);

            // Recursively sort first 2/3 elements
            // again to confirm
            stoogeSort(list, l, h - t);
        }
    }
}