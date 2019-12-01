package graph

import spock.lang.Specification
import utils.Graph

class SingleSourceShortestPathSpec extends Specification {

    def "find sssp in a connected graph"(int u, List path) {
        given:
        def g = new Graph((int[][]) [[1, 6], [0, 6], [3, 5, 6], [2, 4], [3, 5], [2, 4, 6], [0, 1, 2, 5]])
        when:
        def sssp = new SingleSourceShortestPath(g)
        then:
        sssp.pathTo(u) == path
        where:
        u | path
        0 | [0]
        1 | [0, 1]
        2 | [0, 6, 2]
        3 | [0, 6, 2, 3]
        4 | [0, 6, 5, 4]
        5 | [0, 6, 5]
        6 | [0, 6]
    }

    def "find sssp in a disconnected graph"(int u, List path) {
        given:
        def g = new Graph((int[][]) [[3, 6], [2], [4, 5], [0, 6], [2, 5], [2, 4], [0, 3]])
        when:
        def sssp = new SingleSourceShortestPath(g)
        then:
        sssp.pathTo(u) == path
        where:
        u | path
        0 | [0]
        1 | [1]
        2 | [1, 2]
        3 | [0, 3]
        4 | [1, 2, 4]
        5 | [1, 2, 5]
        6 | [0, 6]
    }
}
