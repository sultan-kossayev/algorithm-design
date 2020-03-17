package sorting

import spock.lang.Specification
import spock.lang.Subject

class QuickSortLomutoSpec extends Specification {

    @Subject
    def qs = new QuickSortLomuto()

    def "sort using quicksort"() {
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
