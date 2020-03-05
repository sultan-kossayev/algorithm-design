package sorting;

/**
 * The implementation of Insertion sort
 */
public class InsertionSort<T extends Comparable> implements ArraySort<T> {

    @Override
    public void sort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j - 1 >= 0; j--) {
                if (lessThan(array[j], array[j - 1])) {
                    swap(array, j, j - 1);
                } else {
                    break;
                }
            }
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
