package graph

import spock.lang.Specification
import utils.Graph

class TopologicalSortSpec extends Specification {

    def "find ordering in a dag #g"(List ordering, Graph g) {
        when:
        def ts = new TopologicalSort(g)
        then:
        ts.ordering() == ordering
        where:
        ordering              | g
        [5, 4, 0, 3, 2, 6, 1] | new Graph((int[][]) [[3], [], [6], [1, 2], [0], [0], []])
    }

    def "find ordering in a cyclic graph"() {
        given:
        def g = new Graph((int[][]) [[1], [2], [0]])
        when:
        new TopologicalSort(g)
        then:
        thrown(IllegalStateException)
    }
}
