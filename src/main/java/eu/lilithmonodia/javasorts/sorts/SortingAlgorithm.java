package eu.lilithmonodia.javasorts.sorts;

import eu.lilithmonodia.javasorts.util.TimeUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * This is an abstract class that represents a sorting algorithm. It provides methods for generating a list of random
 * integers, sorting a given list, and displaying the original list along with the sorted list and the time taken to
 * sort the list.
 */
public abstract class SortingAlgorithm {

    private static final Random RANDOM = new Random();
    private static final Logger LOGGER = Logger.getLogger(SortingAlgorithm.class.getName());

    /**
     * Generates a list of random integers.
     *
     * @param size the size of the list to generate
     *
     * @return a list of random integers of the specified size
     */
    public static @NotNull List<Integer> generateRandomList(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(RANDOM.nextInt(Integer.MAX_VALUE));
        }
        return list;
    }

    /**
     * Sorts the given list of integers in ascending order.
     *
     * @param list the list of integers to be sorted
     */
    public abstract void sort(List<Integer> list);

    /**
     * Displays the original list, sorts it using the specified algorithm, and measures the time taken.
     *
     * @param list          The list to be sorted.
     * @param algorithmName The name of the sorting algorithm being used.
     * @param outputSb      The optional StringBuilder to which the output will be appended.
     */
    public void displayAndTime(@NotNull List<Integer> list, String algorithmName, StringBuilder outputSb) {
        String original = "Original List for " + algorithmName + " (first 10 elements): " + list.subList(0, Math.min(list.size(), 10)) + "... ";
        String sorted;
        long startTime = System.nanoTime();
        sort(list);
        long endTime = System.nanoTime();
        sorted = "Sorting List using " + algorithmName + " (first 10 elements): " + list.subList(0, Math.min(list.size(), 10)) + "... ";
        String time = algorithmName + " took " + TimeUtils.formatNanos(endTime - startTime);
        if (outputSb != null) {
            outputSb.append(original).append("\n").append(sorted).append("\n").append(time);
        } else {
            LOGGER.info(original);
            LOGGER.info(sorted);
            LOGGER.info(time);
        }
    }
}
