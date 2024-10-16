package eu.lilithmonodia.javasorts.sorts.practicalsorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Radix Sort is an algorithm for sorting integers by their digits, from the least significant digit to the most
 * significant digit. It is a non-comparative sorting algorithm and has a time complexity of O(nk), where n is the
 * number of elements in the input list, and k is the number of digits in the maximum element.
 *
 * @see SortingAlgorithm
 */
public class RadixSort extends SortingAlgorithm {
    /**
     * Finds the maximum value in the given list of integers.
     *
     * @param list the list of integers
     * @return the maximum value in the list
     */
    private static int findMax(@NotNull List<Integer> list) {
        if (list.isEmpty()) {
            return -1; // Indicates a list is empty. This value won't affect the actual algorithm.
        }
        int max = list.get(0);
        for (Integer integer : list) {
            if (integer > max) {
                max = integer;
            }
        }
        return max;
    }

    /**
     * Sorts the given list of integers using the Counting Sort algorithm. The list will be sorted in non-decreasing
     * order.
     *
     * @param list the list of integers to sort
     * @param exp  the exponent value used for sorting (usually powers of 10)
     */
    private static void countSort(@NotNull List<Integer> list, int exp) {
        int n = list.size();
        List<Integer> output = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            output.add(0);
        }
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            count[(list.get(i) / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output.set(count[(list.get(i) / exp) % 10] - 1, list.get(i));
            count[(list.get(i) / exp) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            list.set(i, output.get(i));
        }
    }

    /**
     * Sorts the given list of integers in ascending order using the Radix Sort algorithm.
     *
     * @param list the list of integers to be sorted
     */
    @Override
    public void sort(List<Integer> list) {
        int maxValue = findMax(list);

        if (maxValue == -1) {
            return;  // List is empty, no need to sort.
        }

        for (int exp = 1; maxValue / exp > 0; exp *= 10) {
            countSort(list, exp);
        }
    }
}
