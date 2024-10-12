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
 *
 * @see SortingAlgorithm
 */
public class HeapSort extends SortingAlgorithm {

    /**
     * Sorts the given list of integers in ascending order using the heap sort algorithm.
     *
     * @param numbersToSort the list of integers to be sorted
     */
    @Override
    public void sort(@NotNull final List<Integer> numbersToSort) {
        int sizeOfHeap = numbersToSort.size();

        for (int i = sizeOfHeap / 2 - 1; i >= 0; i--) {
            maintainHeapInvariant(numbersToSort, sizeOfHeap, i);
        }

        for (int i = sizeOfHeap - 1; i >= 0; i--) {
            Collections.swap(numbersToSort, 0, i);
            maintainHeapInvariant(numbersToSort, i, 0);
        }
    }

    /**
     * MaintainHeapInvariant is a helper method used in the heap sort algorithm to maintain the heap property of a binary tree rooted
     * at a specified index i in the given list of integers.
     *
     * @param heap       the list of integers
     * @param sizeOfHeap the size of the heap
     * @param rootIndex  the index of the root of the subtree to be heapified
     */
    private void maintainHeapInvariant(final List<Integer> heap, final int sizeOfHeap, final int rootIndex) {
        int largestElementIndex = rootIndex;
        int leftChildIndex = 2 * rootIndex + 1;
        int rightChildIndex = 2 * rootIndex + 2;

        if (leftChildIndex < sizeOfHeap && heap.get(leftChildIndex) > heap.get(largestElementIndex)) {
            largestElementIndex = leftChildIndex;
        }

        if (rightChildIndex < sizeOfHeap && heap.get(rightChildIndex) > heap.get(largestElementIndex)) {
            largestElementIndex = rightChildIndex;
        }

        if (largestElementIndex != rootIndex) {
            Collections.swap(heap, rootIndex, largestElementIndex);
            maintainHeapInvariant(heap, sizeOfHeap, largestElementIndex);
        }
    }
}