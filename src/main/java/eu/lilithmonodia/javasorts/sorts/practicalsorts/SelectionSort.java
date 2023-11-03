package eu.lilithmonodia.javasorts.sorts.practicalsorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;

import java.util.List;

/**
 * The SelectionSort class implements the SelectionSort algorithm to sort a list of integers in ascending order.
 * The original list is modified in place.
 */
public class SelectionSort extends SortingAlgorithm {
    /**
     * Sorts a given list of integers in ascending order using the selection sort algorithm.
     * The original list is modified in place.
     *
     * @param list the list of integers to be sorted
     */
    @Override
    public void sort(List<Integer> list) {
        int n = list.size();

        // One by one move boundary of unsorted sub-array
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j) < list.get(minIndex))
                    minIndex = j;
            }

            // Swap the found minimum element with the first element
            int temp = list.get(minIndex);
            list.set(minIndex, list.get(i));
            list.set(i, temp);
        }
    }
}