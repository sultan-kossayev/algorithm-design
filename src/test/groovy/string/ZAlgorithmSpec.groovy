package string

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class ZAlgorithmSpec extends Specification {

    @Subject
    def app = new ZAlgorithm()

    @Unroll
    def "generate z indexes of '#str'"(def str, def res) {
        expect:
        app.calculate(str) == (int[]) res
        where:
        str                | res
        ''                 | []
        'a'                | [0]
        'aa'               | [0, 1]
        'acacbacac'        | [0, 0, 2, 0, 0, 4, 0, 2, 0]
        'acbacdacbacbacda' | [0, 0, 0, 2, 0, 0, 5, 0, 0, 7, 0, 0, 2, 0, 0, 1]
    }
}
