package treetraversal

import spock.lang.Specification
import spock.lang.Subject
import utils.TreeBuilder

class LevelOrderSpec extends Specification {

    @Subject
    def levelorder = new LevelOrder()

    def "validate levelorder traversal"() {
        given:
        def tree = TreeBuilder.build(1, 2, 3, 4, 5, 6, 7)
        expect:
        levelorder.iterativeTraversal(tree) == [1, 2, 3, 4, 5, 6, 7]
    }
}