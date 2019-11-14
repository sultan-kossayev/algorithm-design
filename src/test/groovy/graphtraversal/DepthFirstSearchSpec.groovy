package graphtraversal

import spock.lang.Specification
import spock.lang.Subject
import utils.Graph

class DepthFirstSearchSpec extends Specification {

    @Subject
    def dfs = new DepthFirstSearch()

    def "traverse an undirected graph using dfs"() {
        given:
        def undirected = new Graph((int[][]) [[1, 2], [0, 3], [0, 3], [1, 2, 4], [3]])
        expect:
        dfs.recursiveTraversal(undirected) == [0, 1, 3, 2, 4]
        dfs.iterativeTraversal(undirected) == [0, 1, 3, 2, 4]
    }

    def "traverse a directed graph using dfs"() {
        given:
        def directed = new Graph((int[][]) [[1, 3], [2, 5], [4, 5], [2], [5], []])
        expect:
        dfs.recursiveTraversal(directed) == [0, 1, 2, 4, 5, 3]
        dfs.iterativeTraversal(directed) == [0, 1, 2, 4, 5, 3]
    }

    def "traverse unconnected graph using dfs"() {
        given:
        def unconnected = new Graph((int[][]) [[1, 3], [0, 2, 3], [1, 3], [0, 1, 2], [5], [4]])
        expect:
        dfs.recursiveTraversal(unconnected) == [0, 1, 2, 3, 4, 5]
        dfs.iterativeTraversal(unconnected) == [0, 1, 2, 3, 4, 5]
    }
}
