package graph

import spock.lang.Specification
import spock.lang.Subject
import utils.Graph

class CycleDetectionSpec extends Specification {

    @Subject
    def cd = new CycleDetection()

    def "detect a cycle in cyclic undirected graph"() {
        given:
        def connected = new Graph((int[][]) [[1], [0, 2, 3], [1, 3], [1, 2]])
        def disconnected = new Graph((int[][]) [[1], [0, 2], [1], [4, 5], [3, 5], [3, 4]])
        expect:
        cd.hasCycle(connected)
        cd.hasCycle(disconnected)
    }

    def "detect a cycle in noncyclic undirected graph"() {
        given:
        def connected = new Graph((int[][]) [[1], [0, 2, 3], [1], [1]])
        def disconnected = new Graph((int[][]) [[1], [0, 2], [1], [4], [3, 5], [4]])
        expect:
        !cd.hasCycle(connected)
        !cd.hasCycle(disconnected)
    }
}
