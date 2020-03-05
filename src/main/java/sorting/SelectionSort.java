package sorting;

/**
 * Implementation of the selection sort
 *
 * @param <T> data in the array
 */
public class SelectionSort<T extends Comparable> implements ArraySort<T> {

    @Override
    public void sort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < array.length; j++) {
                minIdx = lessThan(array[j], array[minIdx]) ? j : minIdx;
            }

            swap(array, i, minIdx);
        }
    }

    private boolean lessThan(T t1, T t2) {
        return t1.compareTo(t2) < 0;
    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
