package structures.stack;

import structures.DataStructure;

/**
 * Interface of the stack data structure
 *
 * @param <T> element the stack holds
 */
public interface Stack<T> extends DataStructure {

    /**
     * Returns but doesn't removes the top element of the stack.
     * @return top element of null if the stack is empty
     */
    T top();

    /**
     * Inserts the element at the top of stack
     *
     * @param element to be inserted
     */
    void push(T element);

    /**
     * Removes and returns the top element of the stack.
     * @return top element of null if the stack is empty
     */
    T pop();
}
