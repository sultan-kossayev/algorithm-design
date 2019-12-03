package graph

import spock.lang.Specification
import spock.lang.Unroll
import utils.Graph

class CycleDetectionSpec extends Specification {

    @Unroll
    def "detect a cycle in an undirected graph #g"(boolean cyclic, Graph g) {
        when:
        def cd = new CycleDetection(g)
        then:
        cd.hasCycle() == cyclic
        where:
        cyclic | g
        true   | new Graph((int[][]) [[1], [0, 2, 3], [1, 3], [1, 2]])
        false  | new Graph((int[][]) [[1], [0, 2, 3], [1], [1]])
    }

    @Unroll
    def "detect a cycle in a directed graph #g"(boolean cyclic, Graph g) {
        when:
        def cd = new CycleDetection(g)
        then:
        cd.hasCycle() == cyclic
        where:
        cyclic | g
        true   | new Graph((int[][]) [[1], [2], [0]])
        false  | new Graph((int[][]) [[1, 2], [2], []])
    }
}
