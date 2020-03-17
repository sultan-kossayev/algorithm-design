package sorting;

import utils.ArrayUtils;

/**
 * The implementation of quick sort algorithm using Hoare partition scheme
 */
public class QuickSortHoare<T extends Comparable> implements ArraySort<T> {

    @Override
    public void sort(T[] array) {
        quicksort(array, 0, array.length - 1);
    }

    private void quicksort(T[] array, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int pivot = partition(array, lo, hi);

        quicksort(array, lo, pivot - 1);
        quicksort(array, pivot + 1, hi);
    }

    private int partition(T[] array, int lo, int hi) {
        int pivot = (hi + lo) >>> 1;

        ArrayUtils.swap(array, lo, pivot);
        pivot = lo; // for clarity

        int left = lo;
        int right = hi + 1;

        while (true) {

            while (lessThan(array[++left], array[pivot])) {
                if (left == hi) {
                    break;
                }
            }

            while (lessThan(array[pivot], array[--right])) {
                if (right == lo) {
                    break;
                }
            }

            if (right <= left) {
                break;
            }

            ArrayUtils.swap(array, left, right);
        }

        ArrayUtils.swap(array, pivot, right);

        return right;
    }

    protected boolean lessThan(T t1, T t2) {
        return t1.compareTo(t2) < 0;
    }
}
