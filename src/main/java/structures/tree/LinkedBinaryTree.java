package structures.tree;

import structures.IterableStructure;
import structures.array.FixedSizeArray;
import structures.tree.traversal.TreeTraversal;

/**
 * An implementation of the binary tree based on linked list
 *
 * @param <T> type of data
 */
public class LinkedBinaryTree<T> implements BinaryTree<T> {

    /**
     * The number of nodes the tree has
     */
    private int size;

    /**
     * The reference to the tree's root
     */
    private LinkedNode<T> root;

    @Override
    public Node<T> leftOf(Node<T> parent) {
        return wrap(parent).left;
    }

    @Override
    public Node<T> addLeft(Node<T> parent, T element) throws IllegalStateException {
        LinkedNode p = wrap(parent);

        if (p.left != null) {
            throw new IllegalStateException("The parent already has a left child");
        }

        LinkedNode<T> l = new LinkedNode(element, p);
        p.left = l;

        size++;

        return l;
    }

    @Override
    public Node<T> rightOf(Node<T> parent) {
        return wrap(parent).right;
    }

    @Override
    public Node<T> addRight(Node<T> parent, T element) throws IllegalStateException {
        LinkedNode p = wrap(parent);

        if (p.right != null) {
            throw new IllegalStateException("The parent already has a right child");
        }

        LinkedNode<T> r = new LinkedNode(element, p);
        p.right = r;

        size++;

        return r;
    }

    @Override
    public Node<T> siblingOf(Node<T> node) {
        LinkedNode<T> n = wrap(node);
        Node<T> sibling = null;

        if (n.parent != null) {
            sibling = n.parent.left == n ? n.parent.right : n.parent.left;
        }

        return sibling;
    }

    @Override
    public Node<T> root() {
        return root;
    }

    @Override
    public Node<T> addRoot(T element) throws IllegalStateException {
        if (root != null) {
            throw new IllegalStateException("The tree already has the root");
        }

        root = new LinkedNode(element, null);

        size++;
        return root;
    }

    @Override
    public Node<T> parentOf(Node<T> child) {
        return wrap(child).parent;
    }

    @Override
    public IterableStructure<Node<T>> childrenOf(Node<T> parent) {
        LinkedNode<T> p = wrap(parent);

        FixedSizeArray array = new FixedSizeArray(2);
        if (p.left != null) {
            array.append(p.left);
        }
        if (p.right != null) {
            array.append(p.right);
        }

        return array;
    }

    @Override
    public IterableStructure<Node<T>> nodes(TreeTraversal<T> using) {
        return using.traverse(this);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private LinkedNode<T> wrap(Node<T> node) {
        if (!(node instanceof LinkedNode)) {
            throw new IllegalArgumentException("Incorrect node type");
        }

        return (LinkedNode) node;
    }

    // todo next improvement
    // define BinaryNode interface with method like setLeft(), setParent() etc.
    // and make LinkedNode implement BinaryNode interface.
    // this way you can remove ugly wrap() method and
    // allow clients to extend your implementation
    private static class LinkedNode<T> implements Node<T> {
        T el;
        LinkedNode<T> parent;
        LinkedNode<T> left;
        LinkedNode<T> right;

        LinkedNode(T element, LinkedNode<T> parent) {
            this.el = element;
            this.parent = parent;
        }

        @Override
        public T element() {
            return el;
        }
    }
}
