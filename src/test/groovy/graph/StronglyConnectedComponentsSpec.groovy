package graph

import spock.lang.Specification
import spock.lang.Unroll
import utils.Graph

class StronglyConnectedComponentsSpec extends Specification {

    @Unroll
    def "calculate the number of scc on a graph #g"(int components, Graph g) {
        when:
        def scc = new StronglyConnectedComponents(g)
        then:
        scc.count() == components
        where:
        components | g
        1          | new Graph([[]])
        2          | new Graph([[1], []])
        1          | new Graph([[1], [2], [0]])
        2          | new Graph([[1], [0, 2], [3], [2]])
    }

    def "check scc on a graph"(int u, int v, boolean connected, Graph g) {
        when:
        def scc = new StronglyConnectedComponents(g)
        then:
        scc.stronglyConnected(u, v) == connected
        where:
        u | v | connected | g
        0 | 0 | true      | new Graph([[]])
        0 | 1 | true      | new Graph([[1], [0]])
        0 | 1 | false     | new Graph([[1], []])
    }
}
