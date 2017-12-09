package animations;

/**
 * This class is Menu<T>.
 *
 * @param <T>
 */
public interface Menu<T> extends Animation {

    /**
     * This function add selection.
     *
     * @param key       is String
     * @param message   is String
     * @param returnVal is <T>
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * This function return a status.
     *
     * @return a status
     */
    T getStatus();

    /**
     * This function add selection.
     *
     * @param key     is String
     * @param message is String
     * @param subMenu is Menu<T>
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}