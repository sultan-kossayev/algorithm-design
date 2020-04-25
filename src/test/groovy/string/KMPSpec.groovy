package string

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class KMPSpec extends Specification {

    @Subject
    def kmp = new KMP()

    @Unroll
    def "find string '#pattern' in string '#text'"(def text, def pattern, def res) {
        expect:
        kmp.indexOf(text, pattern) == res
        where:
        text               | pattern     | res
        'hello'            | 'll'        | 2
        'mississippi'      | 'issip'     | 4
        'mississippi'      | 'issipi'    | -1
        'mississippi'      | 'pi'        | 9
        'ababbbbaaabbbaaa' | 'bbbb'      | 3
        'mississippi'      | 'issi'      | 1
        ''                 | ''          | 0
        'a'                | ''          | 0
        'a'                | 'a'         | 0
        'ababcaababcaabc'  | 'ababcaabc' | 6
    }
}
