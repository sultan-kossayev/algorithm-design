package graphtraversal

import spock.lang.Specification
import spock.lang.Subject
import utils.Graph

class DepthFirstSearchSpec extends Specification {

    @Subject
    def dfs = new DepthFirstSearch()

    def "traverse the undirected graph using dfs"() {
        given:
        def undirected = new Graph((int[][]) [[1, 2], [0, 3], [0, 3], [1, 2, 4], [3]])
        expect:
        dfs.recursiveTraversal(undirected) == [0, 1, 3, 2, 4]
        dfs.iterativeTraversal(undirected) == [0, 1, 3, 2, 4]
    }
}
