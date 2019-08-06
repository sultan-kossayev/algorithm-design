package structures;

/**
 * The parent interface for all data structures
 *
 * @param <T> element the structure holds
 */
public interface DataStructure<T> {

	/**
	* Checks whether the data structure is empty
	*/
	boolean isEmpty();

	/**
	* Returns the size of the data structure
	*/
	int size();
}