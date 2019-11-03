package treetraversal

import spock.lang.Specification
import spock.lang.Subject
import utils.TreeBuilder

class InOrderSpec extends Specification {

    @Subject
    def inorder = new InOrder()

    def "validate inorder traversal"() {
        given:
        def tree = TreeBuilder.build(1, 2, 3, 4, 5, 6, 7)
        expect:
        inorder.recursiveTraversal(tree) == [4, 2, 5, 1, 6, 3, 7]
        inorder.iterativeTraversal(tree) == [4, 2, 5, 1, 6, 3, 7]
        inorder.morrisTraversal(tree) == [4, 2, 5, 1, 6, 3, 7]
    }
}
