package structures.tree.traversal

import spock.lang.Specification
import spock.lang.Subject
import structures.IterableStructure
import structures.tree.LinkedBinaryTree
import structures.tree.Tree

class InOrderTraversalSpec extends Specification {

    @Subject
    def inOrder = new InOrderTraversal()

    def tree = new LinkedBinaryTree()

    def setup() {
        def root = tree.addRoot("root")
        def l1 = tree.addLeft(root, "root-left")
        tree.addLeft(l1, "root-left-left")
        tree.addRight(l1, "root-left-right")

        def r1 = tree.addRight(root, "root-right")
        tree.addLeft(r1, "root-right-left")
        tree.addRight(r1, "root-right-right");
    }

    def "given a not binary tree check traversal order"() {
        given:
        def notBinaryTree = getNotBinaryTree()
        when:
        inOrder.traverse(notBinaryTree)
        then:
        thrown(IllegalArgumentException)
    }

    def "given a tree check inorder traversal"() {
        given:
        def order = ["root-left-left", "root-left", "root-left-right", "root", "root-right-left", "root-right", "root-right-right"]
        def i = 0
        when:
        def nodes = inOrder.traverse(tree)
        then:
        nodes.size() == 7
        nodes.every {
            it.element() == order[i++]
        }
    }

    def getNotBinaryTree() {
        return new Tree() {

            @Override
            Tree.Node root() {
                return null
            }

            @Override
            Tree.Node addRoot(Object element) throws IllegalStateException {
                return null
            }

            @Override
            Tree.Node parentOf(Tree.Node child) {
                return null
            }

            @Override
            IterableStructure<Tree.Node> childrenOf(Tree.Node parent) {
                return null
            }

            @Override
            boolean isEmpty() {
                return false
            }

            @Override
            int size() {
                return 0
            }
        }

        return tree
    }
}
