package structures;

/**
 * Generic interface for all "one-dimensional" data structures
 *
 * @param <T> type of the data
 */
public interface GenericCollection<T> extends IterableStructure<T> {

    void add(T element);
}
