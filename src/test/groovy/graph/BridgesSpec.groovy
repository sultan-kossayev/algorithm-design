package graph

import spock.lang.Specification
import spock.lang.Subject
import utils.Graph

class BridgesSpec extends Specification {

    @Subject
    def bridges = new Bridges()

    def "find bridges in a simple undirected graph"() {
        given:
        def g1 = new Graph((int[][]) [[1, 3], [0, 2], [1, 3, 8], [0, 2, 4], [3, 5, 7], [4, 6], [5, 7], [4, 6], [2, 9], [8]])
        def g2 = new Graph((int[][]) [[1], [0, 2], [1, 3], [2]])
        expect:
        bridges.find(g1) == [[3, 4], [8, 9], [2, 8]]
        bridges.find(g2) == [[2, 3], [1, 2], [0, 1]]
    }
}
