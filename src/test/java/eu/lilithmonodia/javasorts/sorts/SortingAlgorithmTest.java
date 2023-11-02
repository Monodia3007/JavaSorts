package eu.lilithmonodia.javasorts.sorts;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortingAlgorithmTest {

    private static class DummySortAlgorithm extends SortingAlgorithm {
        @Override
        public void sort(List<Integer> list) {
            Collections.sort(list);
        }
    }

    private final SortingAlgorithm sortingAlgorithm = new DummySortAlgorithm();

    @Test
    void generateRandomListShouldGenerateCorrectSizeList() {
        List<Integer> list = SortingAlgorithm.generateRandomList(10);
        assert(list.size() == 10);
    }

    @Test
    void sortShouldSortList() {
        List<Integer> list = Arrays.asList(5, 3, 1, 4, 2);
        sortingAlgorithm.sort(list);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, list.toArray(new Integer[0]));
    }

    @Test
    void displayAndTimeShouldModifyList() {
        List<Integer> list = Arrays.asList(5, 3, 1, 4, 2);
        StringBuilder sb = new StringBuilder();
        sortingAlgorithm.displayAndTime(list, "DummySortAlgorithm", sb);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, list.toArray(new Integer[0]));
    }
}