package eu.lilithmonodia.javasorts.sorts.practicalsorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * Implementation of the Heap Sort algorithm.
 * <p>
 * This class provides an implementation of the Heap Sort algorithm to sort a list of integers. It extends the
 * SortingAlgorithm class and overrides the sort() method.
 * <p>
 * The Heap Sort algorithm works by building a binary heap from the input list and repeatedly extracting the maximum
 * element from the heap, which is then placed at the end of the sorted portion of the list. The process is repeated
 * until the entire list is sorted in ascending order.
 * <p>
 * Example usage:
 * <pre>{@code
 * List<Integer> list = new ArrayList<>(Arrays.asList(5, 2, 8, 6, 1, 9));
 * HeapSort heapSort = new HeapSort();
 * heapSort.sort(list);
 * System.out.println(list); // Output: [1, 2, 5, 6, 8, 9]
 * }</pre>
 */
public class HeapSort extends SortingAlgorithm {

    /**
     * Sorts the given list of integers in ascending order using the heap sort algorithm.
     *
     * @param list the list of integers to be sorted
     */
    @Override
    public void sort(@NotNull List<Integer> list) {
        int n = list.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(list, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            Collections.swap(list, 0, i);
            heapify(list, i, 0);
        }
    }

    /**
     * Heapify is a helper method used in the heap sort algorithm to maintain the heap property of a binary tree rooted
     * at a specified index i in the given list of integers.
     *
     * @param list the list of integers
     * @param n    the size of the heap
     * @param i    the index of the root of the subtree to be heapified
     */
    private void heapify(List<Integer> list, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && list.get(left) > list.get(largest)) {
            largest = left;
        }

        if (right < n && list.get(right) > list.get(largest)) {
            largest = right;
        }

        if (largest != i) {
            Collections.swap(list, i, largest);
            heapify(list, n, largest);
        }
    }
}