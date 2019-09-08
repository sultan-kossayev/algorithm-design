package sorting;

import java.util.Arrays;

/**
 * Merge sort implementation
 *
 * @param <T> data type
 */
public class MergeSort<T extends Comparable> implements ArraySort<T> {

    @Override
    public void sort(T[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(T[] array, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int pivot = (lo + hi) / 2;

        mergeSort(array, lo, pivot);
        mergeSort(array, pivot + 1, hi);

        merge(array, lo, pivot, hi);
    }

    private void merge(T[] array, int lo, int pivot, int hi) {
        T[] leftPart = Arrays.copyOfRange(array, lo, pivot + 1);
        T[] rightPart = Arrays.copyOfRange(array, pivot + 1, hi + 1);

        int leftCursor = 0, rightCursor = 0;

        for (int i = lo; i <= hi; i++) {
            T min;

            T left = leftCursor < leftPart.length ? leftPart[leftCursor] : null;
            T right = rightCursor < rightPart.length ? rightPart[rightCursor] : null;

            if (right == null) {
                min = leftPart[leftCursor++];
            } else if (left == null) {
                min = rightPart[rightCursor++];
            } else if (lessThan(left, right)) {
                min = leftPart[leftCursor++];
            } else {
                min = rightPart[rightCursor++];
            }

            array[i] = min;
        }
    }

    private boolean lessThan(T t1, T t2) {
        return t1.compareTo(t2) < 0;
    }
}
