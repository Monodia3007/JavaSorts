package eu.lilithmonodia.javasorts.sorts.impracticalsorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;
import org.jetbrains.annotations.NotNull;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;

/**
 * This class implements the BozoSort algorithm for sorting a list of integers. It extends the SortingAlgorithm class.
 *
 * @see SortingAlgorithm
 */
public class BozoSort extends SortingAlgorithm {
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Checks whether the given list of integers is sorted in non-decreasing order.
     *
     * @param list The list of integers to be checked.
     * @return A boolean value indicating whether the list is sorted or not.
     */
    private static boolean isSorted(@NotNull List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sorts the given list of integers using a randomly generated permutation until the list is sorted.
     *
     * @param list The list of integers to be sorted.
     */
    @Override
    public void sort(List<Integer> list) {
        while (!isSorted(list)) {
            int i = RANDOM.nextInt(list.size());
            int j = RANDOM.nextInt(list.size());
            Collections.swap(list, i, j);
        }
    }
}
