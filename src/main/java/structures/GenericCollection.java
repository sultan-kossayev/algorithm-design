package structures;

/**
 * Generic interface for all "one-dimensional" collections
 *
 * @param <T> type of the data
 */
public interface GenericCollection<T> extends IterableStructure<T> {

    void add(T element);
}
