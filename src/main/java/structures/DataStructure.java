package structures;

/**
 * The parent class for all implementations of types of ˚data structure
 *
 * @param <T> element the structure holds
 */
public interface DataStructure<T> {

	/**
	* Checks whether the structure is empty
	*/
	boolean isEmpty();

	/**
	* Returns the size of the structure i.e.
	* how many elements the structure right now contains
	*/
	int size();
}