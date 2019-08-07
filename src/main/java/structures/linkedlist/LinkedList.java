package structures.linkedlist;

import structures.DataStructure;

/**
 * Interface of linked list data structure
 */
public interface LinkedList<T> extends DataStructure<T> {


    void addFirst(T element);

    T getFirst();

    T getLast();
}
