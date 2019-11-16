package graph

import spock.lang.Specification
import spock.lang.Subject
import utils.Graph

class BreadthFirstSearchSpec extends Specification {

    @Subject
    def bfs = new BreadthFirstSearch()

    def "traverse an undirected graph"() {
        given:
        def undirected = new Graph((int[][]) [[2, 4], [3, 4], [0, 3, 4], [1, 2], [0, 1, 2]])
        expect:
        bfs.search(undirected) == [0, 2, 4, 3, 1]
    }

    def "traverse a directed graph"() {
        given:
        def directed = new Graph((int[][]) [[1, 3], [2, 5], [4, 5], [2], [5], []])
        expect:
        bfs.search(directed) == [0, 1, 3, 2, 5, 4]
    }

    def "traverse a not connected graph"() {
        given:
        def notconnected = new Graph((int[][]) [[1, 3], [0, 2, 3], [1, 3], [0, 1, 2], [5], [4]])
        expect:
        bfs.search(notconnected) == [0, 1, 3, 2, 4, 5]
    }
}
