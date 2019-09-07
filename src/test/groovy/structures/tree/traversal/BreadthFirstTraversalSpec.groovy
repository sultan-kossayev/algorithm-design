package structures.tree.traversal

import spock.lang.Specification
import spock.lang.Subject
import structures.tree.LinkedBinaryTree

class BreadthFirstTraversalSpec extends Specification {

    @Subject
    def breadthFirst = new BreadthFirstTraversal()

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

    def "given a tree check traversal order"() {
        given:
        def order = ["root", "root-left", "root-right", "root-left-left", "root-left-right", "root-right-left", "root-right-right"]
        def i = 0
        when:
        def nodes = breadthFirst.traverse(tree)
        then:
        nodes.size() == 7
        nodes.every {
            it.element() == order[i++]
        }
    }
}
