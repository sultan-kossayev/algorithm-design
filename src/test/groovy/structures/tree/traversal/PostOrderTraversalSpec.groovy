package structures.tree.traversal

import spock.lang.Specification
import spock.lang.Subject
import structures.tree.LinkedBinaryTree

class PostOrderTraversalSpec extends Specification {

    @Subject
    def postOrder = new PostOrderTraversal()

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

    def "given a tree traverse it using postorder traversal"() {
        given:
        def order = ["root-left-left", "root-left-right", "root-left", "root-right-left", "root-right-right", "root-right", "root"]
        def i = 0
        when:
        def nodes = postOrder.traverse(tree)
        then:
        nodes.size() == 7
        nodes.every {
            it.element() == order[i++]
        }
    }
}
