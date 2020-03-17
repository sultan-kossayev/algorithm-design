package sorting

import spock.lang.Specification
import spock.lang.Subject

class MergeSortSpec extends Specification {

    @Subject
    def mergeSort = new MergeSort()

    def "sort using merge sort"() {
        given:
        def unsorted = Fixture.UNSORTED_DATA.collect { it as Integer[] }
        def sorted = Fixture.SORTED_DATA
        when:
        unsorted.each { mergeSort.sort(it) }
        then:
        sorted.eachWithIndex { it, index ->
            assert it == unsorted[index]
        }
    }
}
