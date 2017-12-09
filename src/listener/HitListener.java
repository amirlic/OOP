package listener;

import collidables.Ball;
import collidables.Block;

/**
 * This class create HitListener.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public interface HitListener {
    /**
     * This function is hitEvent.
     *
     * @param beingHit is Block
     * @param hitter   is Ball
     */
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.
    void hitEvent(Block beingHit, Ball hitter);
}