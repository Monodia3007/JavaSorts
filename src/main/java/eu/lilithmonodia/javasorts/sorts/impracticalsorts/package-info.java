/**
 * Provides implementations of impractical sorting algorithms.
 *
 * <p>This package includes various inefficient and often humorous sorting algorithms, each implemented
 * in its own class. These algorithms are generally not suitable for real-world sorting tasks due to
 * their poor performance but can be used for educational purposes or as a fun way to understand
 * the limitations of different sorting approaches.</p>
 *
 * <p>Key classes in this package:</p>
 * <ul>
 *   <li>{@link eu.lilithmonodia.javasorts.sorts.impracticalsorts.BogoSort}: An extraordinarily inefficient
 *       sorting algorithm that repeatedly shuffles the list until it is sorted. It has a worst-case
 *       and average-case time complexity of O((n+1)!), making it impractical for all but the smallest datasets.</li>
 *   <li>{@link eu.lilithmonodia.javasorts.sorts.impracticalsorts.SlowSort}: An intentionally inefficient
 *       sorting algorithm with O(n^2) time complexity. It is designed to demonstrate the pitfalls
 *       of poor algorithm design.</li>
 *   <li>{@link eu.lilithmonodia.javasorts.sorts.impracticalsorts.BubbleSort}: A simple but inefficient
 *       sorting algorithm that repeatedly steps through the list, compares adjacent elements, and swaps
 *       them if they are in the wrong order. Its average and worst-case time complexity is O(n^2).</li>
 *   <li>{@link eu.lilithmonodia.javasorts.sorts.impracticalsorts.StoogeSort}: A highly inefficient and
 *       recursive sorting algorithm that sorts the first two-thirds of the list, then the last
 *       two-thirds, and then the first two-thirds again. Its time complexity is O(n^(log 3/log 1.5)).</li>
 *   <li>{@link eu.lilithmonodia.javasorts.sorts.impracticalsorts.BozoSort}: A random sort that shuffles
 *       pairs of elements randomly until the list is sorted, similar to BogoSort but slightly more refined.</li>
 * </ul>
 * <p>
 * Examples of how to use the package:
 * <pre>{@code
 *     // Sorting a list using BogoSort (not recommended for large lists!)
 *     SortingAlgorithm sortingAlgorithm = new BogoSort();
 *     List<Integer> list = SortingAlgorithm.generateRandomList(10); // Use a very small list due to inefficiency
 *     sortingAlgorithm.sort(list);
 *
 *     // Sorting a list using SlowSort
 *     SortingAlgorithm slowSortAlgorithm = new SlowSort();
 *     slowSortAlgorithm.sort(list);
 *     }
 * </pre>
 */
package eu.lilithmonodia.javasorts.sorts.impracticalsorts;