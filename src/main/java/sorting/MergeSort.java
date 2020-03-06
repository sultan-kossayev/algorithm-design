package sorting;

import java.util.Arrays;

/**
 * The top-down implementation of Merge sort
 */
public class MergeSort<T extends Comparable> implements ArraySort<T> {

    @Override
    public void sort(T[] array) {
        T[] buffer = Arrays.copyOf(array, array.length);
        mergeSort(array, buffer, 0, array.length - 1);
    }

    private void mergeSort(T[] array, T[] buffer, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int pivot = lo + (hi - lo) / 2;

        mergeSort(array, buffer, lo, pivot);
        mergeSort(array, buffer, pivot + 1, hi);

        merge(array, buffer, lo, pivot, hi);
    }

    private void merge(T[] array, T[] buffer, int lo, int pivot, int hi) {
        for (int i = lo; i <= hi; i++) {
            buffer[i] = array[i];
        }

        for (int i = lo, j = pivot + 1, k = lo; i <= pivot || j <= hi; k++) {
            if (i > pivot) {
                array[k] = buffer[j++];
            } else if (j > hi) {
                array[k] = buffer[i++];
            } else if (lessThan(buffer[i], buffer[j])) {
                array[k] = buffer[i++];
            } else {
                array[k] = buffer[j++];
            }
        }
    }

    private boolean lessThan(T t1, T t2) {
        return t1.compareTo(t2) < 0;
    }
}
