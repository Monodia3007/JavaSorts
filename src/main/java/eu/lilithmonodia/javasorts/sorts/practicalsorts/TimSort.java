package eu.lilithmonodia.javasorts.sorts.practicalsorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;

import java.util.List;

public class TimSort extends SortingAlgorithm {

    private static final int RUN = 32;

    public void insertionSort(List<Integer> list, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = list.get(i);
            int j = i - 1;
            while (j >= left && list.get(j) > temp) {
                list.set(j+1, list.get(j));
                j--;
            }
            list.set(j+1, temp);
        }
    }

    public void merge(List<Integer> list, int l, int m, int r) {
        int len1 = m - l + 1, len2 = r - m;
        int[] left = new int[len1];
        int[] right = new int[len2];
        for (int x = 0; x < len1; x++) {
            left[x] = list.get(l + x);
        }
        for (int x = 0; x < len2; x++) {
            right[x] = list.get(m + 1 + x);
        }

        int i = 0;
        int j = 0;
        int k = l;

        while (i < len1 && j < len2) {
            if (left[i] <= right[j]) {
                list.set(k, left[i]);
                i++;
            } else {
                list.set(k, right[j]);
                j++;
            }
            k++;
        }

        while (i < len1) {
            list.set(k, left[i]);
            k++;
            i++;
        }

        while (j < len2) {
            list.set(k, right[j]);
            k++;
            j++;
        }
    }

    @Override
    public void sort(List<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n; i += RUN) {
            insertionSort(list, i, Math.min((i + RUN - 1), (n - 1)));
        }
        for (int size = RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));
                merge(list, left, mid, right);
            }
        }
    }
}