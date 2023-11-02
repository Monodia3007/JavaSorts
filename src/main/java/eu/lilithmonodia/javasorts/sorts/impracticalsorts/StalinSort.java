package eu.lilithmonodia.javasorts.sorts.impracticalsorts;
import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * The StalinSort class is a sorting algorithm that sorts a given list of integers
 * in ascending order by removing any elements that violate the order.
 * <p>
 * The algorithm compares each element of the list with the previous element.
 * If an element is less than the previous element, it is removed from the list.
 * This process continues until the entire list is sorted in ascending order.
 * <p>
 * This class extends the SortingAlgorithm abstract class and defines the sort method.
 */
public class StalinSort extends SortingAlgorithm {
    /**
     * Sorts the given list of integers in ascending order.
     *
     * @param list the list of integers to be sorted
     */
    @Override
    public void sort(List<Integer> list) {
        if (list.isEmpty()) {
            return;
        }

        Integer prevElement = list.get(0);
        Integer nextElement;
        List<Integer> sortedList = new ArrayList<>();
        sortedList.add(prevElement);

        for (int i = 1; i < list.size(); i++) {
            nextElement = list.get(i);
            if (nextElement >= prevElement) {
                sortedList.add(nextElement);
                prevElement = nextElement;
            }
        }

        list.clear();
        list.addAll(sortedList);
    }
}
