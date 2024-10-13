/**
 * Provides classes and interfaces for various sorting algorithms used in the sorting application.
 *
 * <p>This package includes both practical and impractical sorting algorithms, each represented by its own
 * class. The main abstract class {@link eu.lilithmonodia.javasorts.sorts.SortingAlgorithm} defines the
 * common behavior and functionalities that all sorting algorithms must implement, such as generating
 * random lists, sorting them, and displaying timing information.</p>
 *
 * <p>Key classes in this package:</p>
 * <ul>
 *   <li>{@link eu.lilithmonodia.javasorts.sorts.SortingAlgorithm}: The abstract base class for all sorting
 *       algorithms. It contains methods for generating a list of random integers, sorting a given list, and
 *       displaying the original and sorted list along with the time taken to sort the list.</li>
 *   <li>{@link eu.lilithmonodia.javasorts.sorts.impracticalsorts.BogoSort}: An impractical sorting algorithm
 *       that shuffles the list until it is sorted.</li>
 *   <li>{@link eu.lilithmonodia.javasorts.sorts.impracticalsorts.SlowSort}: An impractical sorting algorithm
 *       with intentionally poor performance.</li>
 *   <li>{@link eu.lilithmonodia.javasorts.sorts.practicalsorts.QuickSort}: A practical and efficient sorting
 *       algorithm that uses the divide-and-conquer strategy.</li>
 *   <li>{@link eu.lilithmonodia.javasorts.sorts.practicalsorts.MergeSort}: Another practical sorting algorithm
 *       that follows the divide-and-conquer approach.</li>
 *   <li>... Other sorting algorithms can be listed similarly ...</li>
 * </ul>
 * <p>
 * Examples of how to use the package:
 * <pre>{@code
 *     // Generating a random list
 *     List<Integer> randomList = SortingAlgorithm.generateRandomList(100);
 *
 *     // Sorting the list and timing the execution
 *     SortingAlgorithm sortingAlgorithm = new QuickSort();
 *     StringBuilder outputSb = new StringBuilder();
 *     StringBuilder rawDuration = new StringBuilder();
 *     sortingAlgorithm.displayAndTime(randomList, "QuickSort", outputSb, rawDuration);
 *
 *     // Output results
 *     System.out.println(outputSb.toString());
 *     }
 * </pre>
 */
package eu.lilithmonodia.javasorts.sorts;