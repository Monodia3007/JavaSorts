/**
 * Provides implementations of practical sorting algorithms.
 *
 * <p>This package includes various efficient and commonly used sorting algorithms, each implemented in its
 * own class. These algorithms are designed to handle real-world sorting tasks effectively and are
 * typically faster and more reliable than impractical sorts.</p>
 *
 * <p>Key classes in this package:</p>
 * <ul>
 *   <li>{@link eu.lilithmonodia.javasorts.sorts.practicalsorts.QuickSort}: An efficient sorting
 *       algorithm that uses the divide-and-conquer strategy to sort elements quickly. It works by
 *       selecting a pivot element and partitioning the array into subarrays that are then sorted
 *       independently.</li>
 *   <li>{@link eu.lilithmonodia.javasorts.sorts.practicalsorts.MergeSort}: Another efficient sorting
 *       algorithm based on the divide-and-conquer approach that recursively divides the input array
 *       into two halves, sorts them, and then merges the sorted halves.</li>
 *   <li>{@link eu.lilithmonodia.javasorts.sorts.practicalsorts.HeapSort}: A comparison-based sorting
 *       algorithm that uses a binary heap data structure to sort elements. It is notable for its
 *       efficient use of memory and guarantees a worst-case time complexity of O(n log n).</li>
 *   <li>{@link eu.lilithmonodia.javasorts.sorts.practicalsorts.InsertionSort}: A simple and efficient
 *       algorithm for small datasets. It builds the sorted array one item at a time, with the advantage
 *       of being adaptive, i.e., efficient for data that is already partially sorted.</li>
 *   <li>{@link eu.lilithmonodia.javasorts.sorts.practicalsorts.ShellSort}: An in-place comparison-based
 *       sorting algorithm that improves upon insertion sort by comparing elements separated by a gap
 *       that decreases with each pass.</li>
 *   <li>{@link eu.lilithmonodia.javasorts.sorts.practicalsorts.TimSort}: A hybrid stable sorting algorithm
 *       derived from merge sort and insertion sort, designed to perform efficiently on many kinds of
 *       real-world data.</li>
 *   <li>... Other sorting algorithms can be listed similarly ...</li>
 * </ul>
 * <p>
 * Examples of how to use the package:
 * <pre>{@code
 *     // Sorting a list using QuickSort
 *     SortingAlgorithm sortingAlgorithm = new QuickSort();
 *     List<Integer> list = SortingAlgorithm.generateRandomList(100);
 *     sortingAlgorithm.sort(list);
 *
 *     // Sorting a list using MergeSort
 *     SortingAlgorithm mergeSortAlgorithm = new MergeSort();
 *     mergeSortAlgorithm.sort(list);
 *     }
 * </pre>
 */
package eu.lilithmonodia.javasorts.sorts.practicalsorts;