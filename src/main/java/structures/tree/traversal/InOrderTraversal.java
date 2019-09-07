package structures.tree.traversal;

import structures.GenericCollection;
import structures.IterableStructure;
import structures.array.FixedSizeArray;
import structures.tree.BinaryTree;
import structures.tree.Tree;

/**
 * Allows to traverse binary trees using inorder traversal.
 * Note, the strategy is applicable only for binary trees
 */
public class InOrderTraversal<T> implements TreeTraversal<T> {

    /**
     * @throws IllegalArgumentException if tree doesn't implement {@link BinaryTree}
     */
    @Override
    public IterableStructure<Tree.Node<T>> traverse(Tree<T> tree) throws IllegalArgumentException {
        if (!(tree instanceof BinaryTree)) {
            throw new IllegalArgumentException("The tree must be a binary tree");
        }

        GenericCollection<Tree.Node<T>> nodes = initContainer(tree);

        if (tree.root() != null) {
            BinaryTree<T> binaryTree = (BinaryTree) tree;
            inOrder(nodes, binaryTree, tree.root());
        }

        return nodes;
    }

    protected void inOrder(GenericCollection<Tree.Node<T>> nodes, BinaryTree<T> tree, Tree.Node<T> parent) {
        if (parent == null) {
            return;
        }

        Tree.Node<T> left = tree.leftOf(parent);
        if (left != null) {
            inOrder(nodes, tree, left);
        }

        nodes.add(parent);

        Tree.Node<T> right = tree.rightOf(parent);
        if (right != null) {
            inOrder(nodes, tree, right);
        }
    }

    protected GenericCollection<Tree.Node<T>> initContainer(Tree<T> tree) {
        return new FixedSizeArray(tree.size());
    }
}
