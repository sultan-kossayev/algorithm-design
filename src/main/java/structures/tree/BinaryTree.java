package structures.tree;

/**
 * An interface for binary tree data structure
 *
 * @param <T> type of data
 */
public interface BinaryTree<T> extends Tree<T> {

    /**
     * Returns a left child of the given parent
     * or null if the parent doesn't have left child
     *
     * @param parent
     * @return left child or null
     */
    Node<T> leftOf(Node<T> parent);

    /**
     * Adds an element as a left child of a given parent node.
     * If the parent already has a left child, then an exception is thrown
     *
     * @param parent  a node which the left node has to be assigned to
     * @param element element of the left node
     * @return a reference to the added left node
     * @throws IllegalStateException
     */
    Node<T> addLeft(Node<T> parent, T element) throws IllegalStateException;

    /**
     * Returns a right child of the given parent
     * or null if the parent doesn't have the right child
     *
     * @param parent
     * @return right child or null
     */
    Node<T> rightOf(Node<T> parent);

    /**
     * Adds an element as a right child of a given parent node.
     * If the parent already has a right child, then an exception is thrown
     *
     * @param parent  a node which the right node has to be assigned to
     * @param element element of the right node
     * @return a reference to the added right node
     * @throws IllegalStateException
     */
    Node<T> addRight(Node<T> parent, T element) throws IllegalStateException;

    /**
     * Returns a sibling of the given node, i.e. for left node it returns right node and vice versa.
     * Returns null if the node doesn't have a sibling
     *
     * @param node left or right node
     * @return a sibling node or null
     */
    Node<T> siblingOf(Node<T> node);
}
