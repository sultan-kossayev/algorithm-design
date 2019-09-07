package structures.tree.traversal;

import structures.GenericCollection;
import structures.IterableStructure;
import structures.array.FixedSizeArray;
import structures.tree.Tree;

/**
 * Allows to traverse the tree in preorder traversal
 *
 * @param <T>
 */
public class PreOrderTraversal<T> implements TreeTraversal<T> {

    @Override
    public IterableStructure<Tree.Node<T>> traverse(Tree<T> tree) {
        GenericCollection<Tree.Node<T>> nodes = initContainer(tree);

        if (tree.root() != null) {
            preOrder(nodes, tree, tree.root());
        }

        return nodes;
    }

    protected void preOrder(GenericCollection<Tree.Node<T>> nodes, Tree<T> tree, Tree.Node<T> parent) {
        if (parent == null) {
            return;
        }

        nodes.add(parent);
        for (Tree.Node<T> child : tree.childrenOf(parent)) {
            preOrder(nodes, tree, child);
        }
    }

    protected GenericCollection<Tree.Node<T>> initContainer(Tree<T> tree) {
        return new FixedSizeArray(tree.size());
    }
}
