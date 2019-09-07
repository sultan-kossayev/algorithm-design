package structures.tree;

import structures.DataStructure;
import structures.IterableStructure;
import structures.tree.traversal.TreeTraversal;

/**
 * An interface for tree data structure
 *
 * @param <T> type of nodes that the tree has
 */
public interface Tree<T> extends DataStructure {

    /**
     * Returns the root node of the tree
     * or null if the tree is empty
     *
     * @return root node or null
     */
    Node<T> root();

    /**
     * If the tree is empty adds a root to the tree and returns a reference to it.
     * Otherwise raises an exception
     *
     * @param element to be added as the root of the tree
     * @return a reference to root node
     * @throws IllegalStateException is the tree is not empty
     */
    Node<T> addRoot(T element) throws IllegalStateException;

    /**
     * Returns the parent node of the child node
     * or null if the child is the root itself
     *
     * @param child the child node
     * @return parent node or null
     */
    Node<T> parentOf(Node<T> child);

    /**
     * Returns an iterable collection of children nodes of
     * given parent node
     *
     * @param parent the parent node
     * @return
     */
    IterableStructure<Node<T>> childrenOf(Node<T> parent);

    /**
     * Returns all nodes of the tree in specified order
     *
     * @param using order in which the nodes has to be returned
     * @return all nodes or empty collection
     */
    IterableStructure<Node<T>> nodes(TreeTraversal<T> using);

    /**
     * An interface for tree' node
     */
    interface Node<T> {

        T element();
    }
}
