package sorting

import spock.lang.Specification
import spock.lang.Subject

class InsertionSortSpec extends Specification {

    @Subject
    def insertionSort = new InsertionSort()

    def "sort using insertion sort"() {
        given:
        def unsorted = Fixture.UNSORTED_DATA.collect { it as Integer[] }
        def sorted = Fixture.SORTED_DATA
        when:
        unsorted.each { insertionSort.sort(it) }
        then:
        sorted.eachWithIndex { it, index ->
            assert it == unsorted[index]
        }
    }
}
