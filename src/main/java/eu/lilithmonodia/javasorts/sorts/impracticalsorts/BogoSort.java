package eu.lilithmonodia.javasorts.sorts.impracticalsorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * BogoSort is a class that extends the SortingAlgorithm class and implements the Bogo Sort algorithm. Bogo Sort is a
 * highly inefficient sorting algorithm that works by randomly shuffling the elements until the list is sorted.
 * <p>
 * The BogoSort class provides a method sort() that takes a List of Integers as input and sorts the
 * list in ascending order using the Bogo Sort algorithm.
 * <p>
 * The class also provides a private helper method isSorted() that checks if the given list is sorted
 * in ascending order.
 * <p>
 * This class inherits the abstract method sort() from the SortingAlgorithm class.
 *
 * @see SortingAlgorithm
 */
public class BogoSort extends SortingAlgorithm {

    /**
     * Sorts the given list in ascending order.
     *
     * @param list the list of integers to be sorted
     */
    @Override
    public void sort(List<Integer> list) {
        while (!isSorted(list)) {
            Collections.shuffle(list);
        }
    }

    /**
     * Checks whether the given list of integers is sorted in ascending order.
     *
     * @param list the list of integers to check
     *
     * @return true if the list is sorted in ascending order, false otherwise
     */
    private boolean isSorted(@NotNull List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}