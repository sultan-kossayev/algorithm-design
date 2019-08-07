package structures.linkedlist

import spock.lang.Specification
import spock.lang.Subject

class SinglyLinkedListSpec extends Specification {

    @Subject
    def ll = new SinglyLinkedList()

    def "given an element add it to the empty linked list"() {
        given:
        def element = 1
        when:
        ll.addFirst(element)
        then:
        ll.getFirst() == element
        ll.getLast() == element
        ll.size() == 1
        !ll.isEmpty()
    }
}
