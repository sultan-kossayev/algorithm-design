package graph

import spock.lang.Specification
import utils.Graph

class BipartiteGraphSpec extends Specification {

    def "check whether a graph is bipartite"(boolean bipartite, Graph g) {
        when:
        def bg = new BipartiteGraph(g)
        then:
        bg.isBipartite() == bipartite
        where:
        bipartite | g
        true      | new Graph((int[][]) [[]])
        true      | new Graph((int[][]) [[1], [0]])
        false     | new Graph((int[][]) [[1, 2], [0, 2], [0, 1]])
        true      | new Graph((int[][]) [[1, 3], [0, 2], [1, 3], [0, 2]])
    }
}
