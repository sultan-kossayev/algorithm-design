package graph

import spock.lang.Specification
import spock.lang.Subject
import utils.Graph

class DepthFirstSearchSpec extends Specification {

    // vertices in discovered order
    static final DISCOVERED = 0

    // vertices in processed order
    static final PROCESSED = 1

    @Subject
    def dfs = new DepthFirstSearch()

    def "traverse an undirected graph using dfs"() {
        given:
        def undirected = new Graph((int[][]) [[1, 2], [0, 3], [0, 3], [1, 2, 4], [3]])
        expect:
        dfs.recursive(undirected)[DISCOVERED] == [0, 1, 3, 2, 4]
        dfs.recursive(undirected)[PROCESSED] == [2, 4, 3, 1, 0]

        dfs.iterative(undirected)[DISCOVERED] == [0, 1, 3, 2, 4]
        dfs.iterative(undirected)[PROCESSED] == [2, 4, 3, 1, 0]

        dfs.iterative2(undirected)[DISCOVERED] == [0, 1, 2, 3, 4]
    }

    def "traverse a directed graph using dfs"() {
        given:
        def directed = new Graph((int[][]) [[1, 3], [2, 5], [4, 5], [2], [5], []])
        expect:
        dfs.recursive(directed)[DISCOVERED] == [0, 1, 2, 4, 5, 3]
        dfs.recursive(directed)[PROCESSED] == [5, 4, 2, 1, 3, 0]

        dfs.iterative(directed)[DISCOVERED] == [0, 1, 2, 4, 5, 3]
        dfs.iterative(directed)[PROCESSED] == [5, 4, 2, 1, 3, 0]

        dfs.iterative2(directed)[DISCOVERED] == [0, 1, 3, 2, 4, 5]
    }

    def "traverse not connected graph using dfs"() {
        given:
        def unconnected = new Graph((int[][]) [[1, 3], [0, 2, 3], [1, 3], [0, 1, 2], [5], [4]])
        expect:
        dfs.recursive(unconnected)[DISCOVERED] == [0, 1, 2, 3, 4, 5]
        dfs.recursive(unconnected)[PROCESSED] == [3, 2, 1, 0, 5, 4]

        dfs.iterative(unconnected)[DISCOVERED] == [0, 1, 2, 3, 4, 5]
        dfs.iterative(unconnected)[PROCESSED] == [3, 2, 1, 0, 5, 4]

        dfs.iterative2(unconnected)[DISCOVERED] == [0, 1, 3, 2, 4, 5]
    }
}
