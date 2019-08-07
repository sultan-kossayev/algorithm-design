package structures.linkedlist;

/**
 * The implementation of the singly linked list
 */
public class SinglyLinkedList<T> implements LinkedList<T> {

    /**
     * The number of nodes the list contains
     */
    private int size;

    /**
     * The reference to the head of the list
     */
    private Node head;

    /**
     * The reference to the tail of the list
     */
    private Node tail;

    @Override
    public void addFirst(T element) {
        Node n = new Node(element);
        head = n;
        tail = n;

        size++;
    }

    @Override
    public T getFirst() {
        return (T) head.value;
    }

    @Override
    public T getLast() {
        return (T) tail.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node<T> {
        T value;
        Node next;

        Node(T v) {
            this.value = v;
        }
    }
}
