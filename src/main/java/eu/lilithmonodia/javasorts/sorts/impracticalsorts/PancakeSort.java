package eu.lilithmonodia.javasorts.sorts.impracticalsorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The PancakeSort class implements the pancake sort algorithm, which sorts a list of integers
 * in ascending order.
 */
public class PancakeSort extends SortingAlgorithm {

    /**
     * Sorts a List of Integers in descending order using the Pancake Sort algorithm.
     * <p>
     * The Pancake Sort algorithm works by finding the maximum element in the list and flipping it to the beginning of the list,
     * then flipping the entire list so that the maximum element is at the end. This process is repeated for each element
     * in the list, resulting in a sorted list in descending order.
     *
     * @param list the List of Integers to be sorted
     */
    @Override
    public void sort(List<Integer> list) {
        int n = list.size();
        for (int i = n; i > 1; i--) {
            int maxIndex = findMaxIndex(list, i);
            if (maxIndex != i - 1) {
                flip(list, maxIndex);
                flip(list, i - 1);
            }
        }
    }

    /**
     * Finds the index of the maximum element in a given sub-list of a List of Integers.
     *
     * @param list the List of Integers to search in
     * @param n    the number of elements in the sub-list to search in
     * @return the index of the maximum element in the sub-list
     */
    private int findMaxIndex(List<Integer> list, int n) {
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (list.get(i) > list.get(maxIndex)) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * Reverses the order of a sub-list in a given List of Integers.
     *
     * @param list the List of Integers to modify
     * @param k    the index of the last element in the sub-list to reverse
     */
    private void flip(List<Integer> list, int k) {
        List<Integer> sublist = list.subList(0, k + 1);
        List<Integer> reversedSublist = new ArrayList<>(sublist);
        Collections.reverse(reversedSublist);
        sublist.clear();
        sublist.addAll(reversedSublist);
    }
}