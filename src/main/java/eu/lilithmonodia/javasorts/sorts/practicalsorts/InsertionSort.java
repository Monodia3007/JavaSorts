package eu.lilithmonodia.javasorts.sorts.practicalsorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;

import java.util.List;


/**
 * The InsertionSort class is a sorting algorithm that extends the SortingAlgorithm class. It implements the sort()
 * method to sort a given List of integers using the Insertion Sort algorithm.
 */
public class InsertionSort extends SortingAlgorithm {
    /**
     * Sorts the given list of integers in ascending order using the Insertion Sort algorithm.
     *
     * @param list the list of integers to be sorted
     */
    @Override
    public void sort(List<Integer> list) {
        int n = list.size();
        for (int i = 1; i < n; ++i) {
            int key = list.get(i);
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
    }
}