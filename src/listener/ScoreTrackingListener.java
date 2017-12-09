package listener;

import collidables.Ball;
import collidables.Block;
import game.Counter;

/**
 * This class create ScoreTrackingListener.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * This function constructor.
     *
     * @param scoreCounter is Counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This function is hitEvent.
     *
     * @param beingHit is Block
     * @param hitter   is Ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() > 0) {
            this.currentScore.increase(5);
        }
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(10);
        }
    }
}