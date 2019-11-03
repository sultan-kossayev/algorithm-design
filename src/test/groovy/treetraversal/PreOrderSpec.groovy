package treetraversal

import spock.lang.Specification
import spock.lang.Subject
import utils.TreeBuilder

class PreOrderSpec extends Specification {

    @Subject
    def preorder = new PreOrder()

    def "validate preorder traversal"() {
        given:
        def tree = TreeBuilder.build(1, 2, 3, 4, 5, 6, 7)
        expect:
        preorder.recursiveTraversal(tree) == [1, 2, 4, 5, 3, 6, 7]
        preorder.iterativeTraversal(tree) == [1, 2, 4, 5, 3, 6, 7]
        preorder.morrisTraversal(tree) == [1, 2, 4, 5, 3, 6, 7]
    }
}
