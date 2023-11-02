package eu.lilithmonodia.javasorts.sorts.impracticalsorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;

import java.util.Collections;
import java.util.List;

/**
 * This class implements the SlowSort algorithm for sorting a list of integers.
 * <p>
 * SlowSort is a recursive sorting algorithm that divides the list into two halves, sorts each half recursively,
 * and then swaps the middle element with the last element if the middle element is greater than the last element.
 * Finally, it recursively sorts the list again from the beginning to the second-to-last element.
 *
 * @see SortingAlgorithm
 */
public class SlowSort extends SortingAlgorithm {

    /**
     * Sorts the given list in ascending order using slow sort algorithm.
     *
     * @param list the list of integers to be sorted
     */
    @Override
    public void sort(List<Integer> list) {
        slowSortHelper(list, 0, list.size() - 1);
    }

    /**
     * Recursive helper method for slow sort algorithm.
     * Sorts the sublist between the specified left and right indices in ascending order.
     *
     * @param list  the list of integers to be sorted
     * @param left  the left index of the sublist
     * @param right the right index of the sublist
     */
    private void slowSortHelper(List<Integer> list, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        slowSortHelper(list, left, mid);
        slowSortHelper(list, mid + 1, right);
        if (list.get(mid) > list.get(right)) {
            Collections.swap(list, mid, right);
        }
        slowSortHelper(list, left, right - 1);
    }
}
