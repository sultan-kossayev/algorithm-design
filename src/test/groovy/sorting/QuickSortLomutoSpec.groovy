package sorting

import spock.lang.Specification
import spock.lang.Subject

class QuickSortLomutoSpec extends Specification {

    @Subject
    def qs = new QuickSortLomuto()

    def "sort the given array"() {
        given:
        Integer[] array = [5, 1, 3, 5, 3, 10, 2]
        def sorted = [1, 2, 3, 3, 5, 5, 10]
        when:
        qs.sort(array)
        then:
        array == sorted
    }

    def "sort the empty array"() {
        given:
        Integer[] array = []
        def sorted = []
        when:
        qs.sort(array)
        then:
        array == sorted
    }

    def "sort the already sorted array"() {
        given:
        Integer[] array = [1, 2, 3, 4, 5, 6]
        def sorted = [1, 2, 3, 4, 5, 6]
        when:
        qs.sort(array)
        then:
        array == sorted
    }

    def "sort the array that contains the same elements"() {
        given:
        Integer[] array = [2, 2, 2, 2, 2, 2]
        def sorted = [2, 2, 2, 2, 2, 2]
        when:
        qs.sort(array)
        then:
        array == sorted
    }

    def "sort the array that contains duplicates elements"() {
        given:
        Integer[] array = [10, 10, 2, 2, 8, 8, 2, 2]
        def sorted = [2, 2, 2, 2, 8, 8, 10, 10]
        when:
        qs.sort(array)
        then:
        array == sorted
    }

    def "sort the array that contains negative elements"() {
        given:
        Integer[] array = [4, -1, 3, -5, 10]
        def sorted = [-5, -1, 3, 4, 10]
        when:
        qs.sort(array)
        then:
        array == sorted
    }

    def "sort the array that contains even number of elements"() {
        given:
        Integer[] array = [4, -1, 3, -5]
        def sorted = [-5, -1, 3, 4]
        when:
        qs.sort(array)
        then:
        array == sorted
    }

    def "sort the array that contains odd number of elements"() {
        given:
        Integer[] array = [4, -1, 3, -5, 10, 6, 7]
        def sorted = [-5, -1, 3, 4, 6, 7, 10]
        when:
        qs.sort(array)
        then:
        array == sorted
    }

    def "sort the array that contains only one element"() {
        given:
        Integer[] array = [1]
        when:
        qs.sort(array)
        then:
        array == [1]
    }
}
