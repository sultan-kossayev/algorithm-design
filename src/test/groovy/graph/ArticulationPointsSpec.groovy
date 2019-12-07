package graph

import spock.lang.Specification
import utils.Graph

class ArticulationPointsSpec extends Specification {

    def "test a one vertex graph"() {
        given:
        def g = new Graph((int[][]) [[]])
        def ap = new ArticulationPoints(g)
        expect:
        ap.points() == []
    }

    def "test a two vertices graph"() {
        given:
        def g = new Graph((int[][]) [[1], [0]])
        def ap = new ArticulationPoints(g)
        expect:
        ap.points() == []
    }

    def "find a root cut-node"() {
        given:
        def g = new Graph((int[][]) [[1, 2], [0], [0]])
        def ap = new ArticulationPoints(g)
        expect:
        ap.points() == [0]
    }

    def "find a bridge cut-node"() {
        given:
        def g = new Graph((int[][]) [[1], [0, 2], [1]])
        def ap = new ArticulationPoints(g)
        expect:
        ap.points() == [1]
    }

    def "find a parent cut-node"() {
        given:
        def g = new Graph((int[][]) [[1], [0, 2, 3], [1, 3], [1, 2]])
        def ap = new ArticulationPoints(g)
        expect:
        ap.points() == [1]
    }

    def "test that a biconnected graph doesn't have articulation points"() {
        given:
        def g = new Graph((int[][]) [[1, 2, 3], [0, 2, 3, 4], [0, 1, 4], [0, 1, 4], [1, 2, 3]])
        def ap = new ArticulationPoints(g)
        expect:
        ap.points() == []
    }

    def "find cut nodes in a disconnected graph"() {
        given:
        def g = new Graph((int[][]) [[1], [0, 2], [1], [4, 5], [3], [3], [7], [6, 8, 9], [7, 9], [7, 8]])
        def ap = new ArticulationPoints(g)
        expect:
        // bridge, root, and parent cut nodes
        ap.points() == [1, 3, 7]
    }
}
