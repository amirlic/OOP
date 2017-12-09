package listener;

/**
 * This class create HitNotifier.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public interface HitNotifier {
    /**
     * This function is addHitListener.
     *
     * @param hl is HitListener
     */
    // Add hl as a listener to hit events.
    void addHitListener(HitListener hl);

    /**
     * This function is removeHitListener.
     *
     * @param hl is HitListener
     */
    // Remove hl from the list of listeners to hit events.
    void removeHitListener(HitListener hl);
}
