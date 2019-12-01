package graph

import spock.lang.Specification
import utils.Graph

class SingleSourcePathSpec extends Specification {

    def "find ssp in a connected graph"(int u, List path) {
        given:
        def g = new Graph((int[][]) [[1, 6], [0, 6], [3, 5, 6], [2, 4], [3, 5], [2, 4, 6], [0, 1, 2, 5]])
        when:
        def ssp = new SingleSourcePath(g)
        then:
        ssp.pathTo(u) == path
        where:
        u | path
        0 | [0]
        1 | [0, 1]
        2 | [0, 1, 6, 2]
        3 | [0, 1, 6, 2, 3]
        4 | [0, 1, 6, 2, 3, 4]
        5 | [0, 1, 6, 2, 3, 4, 5]
        6 | [0, 1, 6]
    }

    def "find ssp in a disconnected graph"(int u, List path) {
        given:
        def g = new Graph((int[][]) [[3, 6], [2], [4, 5], [0, 6], [2, 5], [2, 4], [0, 3]])
        when:
        def ssp = new SingleSourcePath(g)
        then:
        ssp.pathTo(u) == path
        where:
        u | path
        0 | [0]
        1 | [1]
        2 | [1, 2]
        3 | [0, 3]
        4 | [1, 2, 4]
        5 | [1, 2, 4, 5]
        6 | [0, 3, 6]
    }
}
