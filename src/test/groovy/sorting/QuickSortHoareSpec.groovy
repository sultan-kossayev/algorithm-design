package sorting

import spock.lang.Specification
import spock.lang.Subject

class QuickSortHoareSpec extends Specification {

    @Subject
    def qs = new QuickSortHoare()

    def "sort using quick sort"() {
        given:
        def unsorted = Fixture.UNSORTED_DATA.collect { it as Integer[] }
        def sorted = Fixture.SORTED_DATA
        when:
        unsorted.each { qs.sort(it) }
        then:
        sorted.eachWithIndex { it, index ->
            assert it == unsorted[index]
        }
    }
}
