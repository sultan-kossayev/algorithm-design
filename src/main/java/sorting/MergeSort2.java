package sorting;

import java.util.Arrays;

/**
 * The bottom-up implementation of Merge sort
 */
public class MergeSort2<T extends Comparable> implements ArraySort<T> {

    @Override
    public void sort(T[] array) {
        T[] buffer = Arrays.copyOf(array, array.length);

        for (int step = 1; step < array.length; step *= 2) {
            for (int lo = 0; lo < array.length - step; lo += step + step) {

                int hi = Math.min(array.length - 1, lo + step + step - 1);
                int pivot = lo + step - 1;
                merge(array, buffer, lo, pivot, hi);
            }
        }
    }

    private void merge(T[] array, T[] buffer, int lo, int pivot, int hi) {
        for (int i = lo; i <= hi; i++) {
            buffer[i] = array[i];
        }

        for (int k = lo, i = lo, j = pivot + 1; i <= pivot || j <= hi; k++) {
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
