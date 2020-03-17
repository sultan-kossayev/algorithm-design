package sorting

import spock.lang.Specification
import spock.lang.Subject

class MergeSort2Spec extends Specification {

    @Subject
    def mergeSort2 = new MergeSort2()

    def "sort using merge sort"() {
        given:
        def unsorted = Fixture.UNSORTED_DATA.collect { it as Integer[] }
        def sorted = Fixture.SORTED_DATA
        when:
        unsorted.each { mergeSort2.sort(it) }
        then:
        sorted.eachWithIndex { it, index ->
            assert it == unsorted[index]
        }
    }
}
