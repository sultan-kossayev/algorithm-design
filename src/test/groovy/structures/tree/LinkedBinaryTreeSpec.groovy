package structures.tree

import spock.lang.Specification
import spock.lang.Subject

class LinkedBinaryTreeSpec extends Specification {

    @Subject
    def tree = new LinkedBinaryTree()

    def "given an empty tree check its invariants"() {
        expect:
        tree.size() == 0
        tree.isEmpty()
        tree.root() == null
    }

    def "given an empty tree add the root element"() {
        when:
        def root = tree.addRoot(10)
        then:
        tree.size() == 1
        root.element() == 10
    }

    def "given a tree that has the root try to add a new root"() {
        given:
        tree.addRoot(10)
        when:
        tree.addRoot(11)
        then:
        thrown(IllegalStateException)
    }

    def "given a tree add a left child to a node"() {
        given:
        def parent = tree.addRoot(10)
        when:
        def left = tree.addLeft(parent, 11)
        then:
        tree.size() == 2
        tree.leftOf(parent).element() == 11
        tree.parentOf(left) == parent
    }

    def "given a tree add a left child to a node which already has a left child"() {
        given:
        def p = tree.addRoot(10)
        tree.addLeft(p, 11)
        when:
        tree.addLeft(p, 12)
        then:
        thrown(IllegalStateException)
    }

    def "given a tree try to add a node which implements a incorrect node interface"() {
        when:
        def incorrectType = new Tree.Node<Integer>() {
            Integer element() {
                return 10;
            }
        }
        tree.addLeft(incorrectType, 10)
        then:
        thrown(IllegalArgumentException)
    }

    def "given a tree add a right child to a node"() {
        given:
        def p = tree.addRoot(10)
        when:
        def r = tree.addRight(p, 11)
        then:
        tree.size() == 2
        tree.rightOf(p).element() == 11
        tree.parentOf(r) == p
    }

    def "given a tree add a right child to a node which already has a right child"() {
        given:
        def p = tree.addRoot(10)
        tree.addRight(p, 11)
        when:
        tree.addRight(p, 12)
        then:
        thrown(IllegalStateException)
    }

    def "given a tree add 2 levels of nodes after the root node"() {
        given:
        def root = tree.addRoot(10)
        when:
        def l1 = tree.addLeft(root, 11)
        def l1_lchild = tree.addLeft(l1, 12)
        def l1_rchild = tree.addRight(l1, 13)
        then:
        tree.leftOf(l1) == l1_lchild
        tree.rightOf(l1) == l1_rchild
        tree.parentOf(l1_lchild) == l1
        tree.parentOf(l1_rchild) == l1
    }

    def "given a tree check siblings"() {
        given:
        def p = tree.addRoot(10)
        def l = tree.addLeft(p, 11)
        def r = tree.addRight(p, 12)
        expect:
        tree.siblingOf(p) == null
        tree.siblingOf(l) == r
        tree.siblingOf(r) == l
    }

    def "given a tree get children of a parent"() {
        given:
        def p = tree.addRoot(10)
        tree.addLeft(p, 11)
        tree.addRight(p, 12)
        def i = 0
        def arr = [11, 12]
        when:
        def children = tree.childrenOf(p)
        then:
        children.size() == 2
        children.every {
            it.element() == arr[i++]
        }
    }
}
