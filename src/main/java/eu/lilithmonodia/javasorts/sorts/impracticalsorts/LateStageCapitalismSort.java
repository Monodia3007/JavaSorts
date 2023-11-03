package eu.lilithmonodia.javasorts.sorts.impracticalsorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;

import java.util.List;
import java.util.Random;

public class LateStageCapitalismSort extends SortingAlgorithm {

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