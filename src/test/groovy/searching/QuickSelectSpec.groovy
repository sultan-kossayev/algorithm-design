package searching

import spock.lang.Specification
import spock.lang.Subject

class QuickSelectSpec extends Specification {

    @Subject
    def qs = new QuickSelect()

    def "select Kth smallest element"(int[] array, def k, def res) {
        expect:
        qs.findKthSmallestElement(array, k) == res
        where:
        array                                                                 | k  | res
        []                                                                    | 0  | -1
        [1]                                                                   | 0  | 1
        [11, 21, 7, 3, 10]                                                    | 1  | 7
        [25, 43, 45, 1, 39, 49, 29, 28, 8, 34, 4, 21, 33, 1]                  | 3  | 8
        [28, 41, 40, 23, 13, 35, 9, 23, 20, 10, 45, 2, 35, 20, 20, 2, 16, 19] | 10 | 23
    }
}
