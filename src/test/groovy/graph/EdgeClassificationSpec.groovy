package graph

import spock.lang.Specification
import spock.lang.Subject
import utils.Graph

import static graph.EdgeClassification.Edge
import static graph.EdgeClassification.Edge.Type.BACKWARD
import static graph.EdgeClassification.Edge.Type.TREE

class EdgeClassificationSpec extends Specification {

    @Subject
    def ec = new EdgeClassification()

    def "classify edges of a simple undirected graph"() {
        given:
        def g = new Graph((int[][]) [[1, 2, 3], [0, 2], [0, 1, 3], [0, 2]])
        def answer = [new Edge(0, 1, TREE), new Edge(1, 2, TREE), new Edge(2, 0, BACKWARD)
                      , new Edge(2, 3, TREE), new Edge(3, 0, BACKWARD)]
        expect:
        ec.classify(g) == answer
    }
}
