package treetraversal

import spock.lang.Specification
import spock.lang.Subject
import utils.TreeBuilder

class PostOrderSpec extends Specification {

    @Subject
    def postorder = new PostOrder()

    def "validate postorder traversal"() {
        given:
        def tree = TreeBuilder.build(1, 2, 3, 4, 5, 6, 7)
        expect:
        postorder.recursiveTraversal(tree) == [4, 5, 2, 6, 7, 3, 1]
        postorder.iterativeTraversal(tree) == [4, 5, 2, 6, 7, 3, 1]
    }
}
