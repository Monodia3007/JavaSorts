package eu.lilithmonodia.javasorts.sorts;

import eu.lilithmonodia.javasorts.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * An abstract class representing a sorting algorithm.
 */
public abstract class SortingAlgorithm {

    /**
     * Generates a list of random integers.
     *
     * @param size the size of the random list to generate
     *
     * @return a list of random integers with the specified size
     */
    public static List<Integer> generateRandomList(int size) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(Integer.MAX_VALUE)); // Generates random numbers between 0 and 999
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
     * Display and time the sorting algorithm with the given list.
     *
     * @param list          the list to be sorted
     * @param algorithmName the name of the sorting algorithm
     * @param outputSb      the StringBuilder to append the output to, use null for printing to console
     */
    public void displayAndTime(List<Integer> list, String algorithmName, StringBuilder outputSb) {
        String original = "Original List for " + algorithmName + " (first 10 elements): " + list.subList(0, Math.min(list.size(), 10)) + "... ";
        String sorted;
        long startTime = System.nanoTime();
        sort(list);
        long endTime = System.nanoTime();
        sorted = "Sorting List using " + algorithmName + " (first 10 elements): " + list.subList(0, Math.min(list.size(), 10)) + "... ";
        String time = algorithmName + " took " + TimeUtils.formatNanos(endTime - startTime) + "\n";
        if (outputSb != null) {
            outputSb.append(original).append("\n").append(sorted).append("\n").append(time);
        } else {
            System.out.println(original);
            System.out.println(sorted);
            System.out.println(time);
        }
    }
}
