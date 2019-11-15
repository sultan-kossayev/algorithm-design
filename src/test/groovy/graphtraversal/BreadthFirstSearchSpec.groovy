package graphtraversal

import spock.lang.Specification
import spock.lang.Subject
import utils.Graph

class BreadthFirstSearchSpec extends Specification {

    @Subject
    def bfs = new BreadthFirstSearch()

    def "traverse an undirected graph"() {
        given:
        def undirected = new Graph((int[][]) [[1, 2], [0, 3], [0, 3], [1, 2, 4], [3]])
        expect:
        bfs.search(undirected) == [0, 1, 2, 3, 4]
    }
}
