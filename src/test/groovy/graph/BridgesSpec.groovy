package graph

import spock.lang.Specification
import spock.lang.Unroll
import utils.Edge
import utils.Graph

class BridgesSpec extends Specification {

    @Unroll
    def "find bridges in a graph #g"(List edges, Graph g) {
        when:
        def b = new Bridges(g)
        then:
        b.bridges() == Edge.from(edges)
        where:
        edges    | g
        []       | new Graph((int[][]) [[]])
        [[0, 1]] | new Graph((int[][]) [[1], [0]])
        [[1, 3]] | new Graph((int[][]) [[1], [2, 3], [0], []])
    }
}
