package eu.lilithmonodia.javasorts.sorts.impracticalsorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * BubbleSort is a class that extends the SortingAlgorithm class and provides the implementation for the sort method
 * using the bubble sort algorithm.
 *
 * @see SortingAlgorithm
 */
public class BubbleSort extends SortingAlgorithm {
    /**
     * Sorts a list of integers in ascending order.
     *
     * @param list the list of integers to be sorted
     */
    @Override
    public void sort(@NotNull List<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (list.get(j) > list.get(j + 1)) {
                    // swap arr[j+1] and arr[j]
                    Integer temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
    }
}
