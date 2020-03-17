package sorting

import spock.lang.Specification
import spock.lang.Subject

class SelectionSortSpec extends Specification {

    @Subject
    def selectionSort = new SelectionSort()

    def "sort using selection sort"() {
        given:
        def unsorted = Fixture.UNSORTED_DATA.collect { it as Integer[] }
        def sorted = Fixture.SORTED_DATA
        when:
        unsorted.each { selectionSort.sort(it) }
        then:
        sorted.eachWithIndex { it, index ->
            assert it == unsorted[index]
        }
    }
}
