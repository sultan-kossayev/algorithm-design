package sorting;

/**
 * The implementation of quick sort algorithm using Lomuto partitioning scheme
 */
public class QuickSortLomuto<T extends Comparable> implements ArraySort<T> {

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

    /**
     * The method chooses the last element in the [lo, hi] range as a pivot element.
     * Then it maintains index left as it scans the array using another index right
     * such that the elements at lo through left-1 (inclusive) are less than the pivot,
     * and the elements at left through right (inclusive) are equal to or greater than the pivot.
     */
    private int partition(T[] array, int lo, int hi) {
        int pivot = hi;

        int left = lo;
        for (int right = lo; right <= hi; right++) {
            if (lessThan(array[right], array[pivot])) {
                swap(array, right, left);
                left++;
            }
        }

        swap(array, left, pivot);

        return left;
    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    protected boolean lessThan(T t1, T t2) {
        return t1.compareTo(t2) < 0;
    }
}
