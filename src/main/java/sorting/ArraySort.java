package sorting;

/**
 * Arrays sorting strategy
 *
 * @param <T> data type
 */
public interface ArraySort<T extends Comparable> {

    /**
     * Sorts the given array.
     * Note that the source array is modified
     *
     * @param array to be sorted
     */
    void sort(T[] array);
}
