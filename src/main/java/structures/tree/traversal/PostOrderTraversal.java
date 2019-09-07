package structures.tree.traversal;

import structures.GenericCollection;
import structures.IterableStructure;
import structures.array.FixedSizeArray;
import structures.tree.Tree;

/**
 * Allows to traverse the tree using postorder traversal
 */
public class PostOrderTraversal<T> implements TreeTraversal<T> {

    @Override
    public IterableStructure<Tree.Node<T>> traverse(Tree<T> tree) {
        GenericCollection<Tree.Node<T>> nodes = initContainer(tree);

        if (tree.root() != null) {
            postOrder(nodes, tree, tree.root());
        }

        return nodes;
    }

    protected void postOrder(GenericCollection<Tree.Node<T>> nodes, Tree<T> tree, Tree.Node<T> parent) {
        if (parent == null) {
            return;
        }

        for (Tree.Node<T> child : tree.childrenOf(parent)) {
            postOrder(nodes, tree, child);
        }

        nodes.add(parent);
    }

    protected GenericCollection<Tree.Node<T>> initContainer(Tree<T> tree) {
        return new FixedSizeArray(tree.size());
    }
}
