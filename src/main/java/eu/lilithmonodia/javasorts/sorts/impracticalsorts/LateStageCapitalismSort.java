package eu.lilithmonodia.javasorts.sorts.impracticalsorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;

import java.util.List;
import java.util.Random;

/**
 * A sorting algorithm that represents the chaos and inequality of late stage capitalism.
 * It randomly shuffles the elements in the list, simulating the unpredictability and unfairness of the economy.
 * <p>
 * This algorithm extends the abstract class SortingAlgorithm and provides the implementation for the sort method.
 */
public class LateStageCapitalismSort extends SortingAlgorithm {

    /**
     * Sorts a list of integers in ascending order.
     *
     * @param list the list of integers to be sorted
     */
    @Override
    public void sort(List<Integer> list) {
        final Random rand = new Random();
        for (int i = 0; i < list.size(); i++) {
            int randIndex = rand.nextInt(list.size());
            if (list.get(i) < list.get(randIndex)) {
                int temp = list.get(i);
                list.set(i, list.get(randIndex));
                list.set(randIndex, temp);
            }
        }
    }
}