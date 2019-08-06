package structures.array;

import structures.DataStructure;

/**
 * Array data structure
 */
public interface Array<T> extends DataStructure<T> {

    /**
     * Adds the element to the end of the array
     */
    void append(T element);

    /**
     * Inserts the element to the array into specified array
     */
    void insertTo(int index, T element);

    /**
     * Returns the element by its index
     */
    T getBy(int index);

    /**
     * Removes the element by its index
     */
    T removeBy(int index);
}