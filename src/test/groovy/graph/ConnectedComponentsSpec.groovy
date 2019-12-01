package graph

import spock.lang.Specification
import spock.lang.Subject
import utils.Graph

class ConnectedComponentsSpec extends Specification {

    @Subject
    def cc = new ConnectedComponents()

    def "find cc in a connected graph"() {
        given:
        def g = new Graph((int[][]) [[1, 3], [0, 2], [1, 3], [0, 2]])
        expect:
        cc.findUsingDFS(g) == [[0, 1, 2, 3]]
        cc.findUsingBFS(g) == [[0, 1, 3, 2]]
    }

    def "find cc in a disconnected graph"() {
        given:
        def g = new Graph((int[][]) [[1], [0], [3], [2]])
        expect:
        cc.findUsingDFS(g) == [[0, 1], [2, 3]]
        cc.findUsingBFS(g) == [[0, 1], [2, 3]]
    }
}
