package eu.lilithmonodia.javasorts.sorts.practicalsorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides an implementation of the Merge Sort algorithm using parallel processing with
 * CompletableFuture to enhance performance on multicore systems. The algorithm recursively divides the list
 * into two halves, sorts each half in parallel, and then merges the sorted halves to produce the final sorted list.
 */
public class ParallelMergeSort extends SortingAlgorithm {

    private static final Logger LOGGER = Logger.getLogger(ParallelMergeSort.class.getName());

    /**
     * Merges two sorted arrays of integers into a single sorted array in ascending order.
     *
     * @param left  the first sorted array to be merged
     * @param right the second sorted array to be merged
     * @return a new sorted array containing all elements from the two input arrays
     */
    @Contract(pure = true)
    private static Integer @NotNull [] merge(Integer @NotNull [] left, Integer @NotNull [] right) {
        Integer[] result = new Integer[left.length + right.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }

    /**
     * Sorts the given list of integers in ascending order using a parallel merge sort algorithm.
     * This method leverages CompletableFuture to perform sorting in parallel threads.
     * If the list is null or contains fewer than two elements, no sorting is performed.
     *
     * @param list the list of integers to be sorted
     */
    @Override
    public void sort(List<Integer> list) {
        if (list == null || list.size() < 2) {
            return;
        }

        Integer[] array = list.toArray(new Integer[0]);

        try {
            CompletableFuture<Integer[]> sortedArray = mergeSort(array);
            List<Integer> sortedList = List.of(sortedArray.get());
            for (int i = 0; i < list.size(); i++) {
                list.set(i, sortedList.get(i));
            }
        } catch (InterruptedException e) {
            // Restore interrupted status
            Thread.currentThread().interrupt();
            LOGGER.log(Level.SEVERE, "Thread was interrupted during sorting", e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during sorting", e);
        }
    }

    /**
     * Sorts the given array of integers in ascending order using a parallel merge sort algorithm.
     * This method leverages CompletableFuture to perform sorting in parallel threads. The array is split
     * into smaller arrays until the base case of a single-element or empty array is reached. Each subarray
     * is sorted asynchronously, and the results are combined to form the final sorted array.
     *
     * @param array the array of integers to be sorted
     * @return a CompletableFuture that will complete with the sorted array of integers
     */
    private CompletableFuture<Integer[]> mergeSort(Integer @NotNull [] array) {
        if (array.length < 2) {
            return CompletableFuture.completedFuture(array);
        }

        int mid = array.length / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[array.length - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        CompletableFuture<Integer[]> leftSorted = CompletableFuture.supplyAsync(() -> mergeSort(left).join());
        CompletableFuture<Integer[]> rightSorted = CompletableFuture.supplyAsync(() -> mergeSort(right).join());

        return leftSorted.thenCombine(rightSorted, ParallelMergeSort::merge);
    }
}