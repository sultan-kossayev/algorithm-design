package graph

import spock.lang.Specification
import spock.lang.Subject
import utils.Graph

class CyclicPathSpec extends Specification {

    @Subject
    def cp = new CyclicPath()

    def "get a cyclic path in a simple undirected graph"() {
        given:
        def g = new Graph((int[][])[[1], [0, 2, 4], [1, 3], [2, 4], [1, 3]])
        expect:
        cp.find(g) == [1, 2, 3, 4]
    }
}
