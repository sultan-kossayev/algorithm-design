package graph

import spock.lang.Specification
import spock.lang.Subject
import utils.Graph

class ArticulationPointsSpec extends Specification {

    @Subject
    def ap = new ArticulationPoints()

    def "test a one vertex graph"() {
        given:
        def g = new Graph((int[][]) [[]])
        expect:
        ap.find(g) == []
    }

    def "test a two vertices graph"() {
        given:
        def g = new Graph((int[][]) [[1], [0]])
        expect:
        ap.find(g) == []
    }

    def "find a root cut-node"() {
        given:
        def g = new Graph((int[][]) [[1, 2], [0], [0]])
        expect:
        ap.find(g) == [0]
    }

    def "find a bridge cut-node"() {
        given:
        def g = new Graph((int[][]) [[1], [0, 2], [1]])
        expect:
        ap.find(g) == [1]
    }

    def "find a parent cut-node"() {
        given:
        def g = new Graph((int[][]) [[1], [0, 2, 3], [1, 3], [1, 2]])
        expect:
        ap.find(g) == [1]
    }

    def "test that a biconnected graph doesn't have articulation points"() {
        given:
        def g = new Graph((int[][]) [[1, 2, 3], [0, 2, 3, 4], [0, 1, 4], [0, 1, 4], [1, 2, 3]])
        expect:
        ap.find(g) == []
    }

    def "find cut nodes in a disconnected graph"() {
        given:
        def g = new Graph((int[][]) [[1], [0, 2], [1], [4, 5], [3], [3], [7], [6, 8, 9], [7, 9], [7, 8]])
        expect:
        // bridge, root, and parent cut nodes
        ap.find(g) == [1, 3, 7]
    }
}
