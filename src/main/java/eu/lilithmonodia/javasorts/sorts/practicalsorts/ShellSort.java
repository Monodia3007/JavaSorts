package eu.lilithmonodia.javasorts.sorts.practicalsorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;

import java.util.List;

/**
 * ShellSort is a class that implements the Shell Sort algorithm to sort a given List of Integers.
 * It extends the SortingAlgorithm class.
 */
public class ShellSort extends SortingAlgorithm {
    /**
     * Sorts a given list of integers in ascending order using the Shell Sort algorithm.
     *
     * @param list the list of integers to be sorted
     */
    @Override
    public void sort(List<Integer> list) {
        int n = list.size();
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && list.get(j) < list.get(j - h); j -= h) {
                    int temp = list.get(j);
                    list.set(j, list.get(j - h));
                    list.set(j - h, temp);
                }
            }
            h = h / 3;
        }
    }
}