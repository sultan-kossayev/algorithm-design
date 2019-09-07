package structures.tree.traversal;

import structures.IterableStructure;
import structures.tree.Tree;

/**
 * An interface that defines traversal order of trees
 *
 * @param <T>
 */
public interface TreeTraversal<T> {

    /**
     * Returns an iterable collection of nodes in particular order.
     * The order is defined by the implementation
     *
     * @param tree
     * @return nodes in particular order or empty collection
     */
    IterableStructure<Tree.Node<T>> traverse(Tree<T> tree);
}