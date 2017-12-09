package tasks;

/**
 * This class is Task<T>.
 *
 * @param <T>
 */
public interface Task<T> {

    /**
     * This function run.
     *
     * @return <T>
     */
    T run();
}