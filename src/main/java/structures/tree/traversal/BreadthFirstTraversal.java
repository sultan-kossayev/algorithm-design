package structures.tree.traversal;

import structures.GenericCollection;
import structures.IterableStructure;
import structures.array.FixedSizeArray;
import structures.queue.LinkedQueue;
import structures.queue.Queue;
import structures.tree.Tree;

/**
 * Allows to traverse a tree using breadth-first traversal
 *
 * @param <T>
 */
public class BreadthFirstTraversal<T> implements TreeTraversal<T> {

    @Override
    public IterableStructure<Tree.Node<T>> traverse(Tree<T> tree) {
        GenericCollection<Tree.Node<T>> nodes = initContainer(tree);

        if (tree.root() != null) {
            breadthFirst(nodes, tree, tree.root());
        }

        return nodes;
    }

    protected void breadthFirst(GenericCollection<Tree.Node<T>> nodes, Tree<T> tree, Tree.Node<T> root) {
        Queue<Tree.Node<T>> queue = new LinkedQueue<>();
        queue.enqueue(root);

        while (queue.first() != null) {
            Tree.Node<T> parent = queue.dequeue();
            nodes.add(parent);

            for (Tree.Node<T> child : tree.childrenOf(parent)) {
                queue.enqueue(child);
            }
        }
    }

    protected GenericCollection initContainer(Tree<T> tree) {
        return new FixedSizeArray(tree.size());
    }
}
