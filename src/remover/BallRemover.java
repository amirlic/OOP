package remover;

import collidables.Ball;
import collidables.Block;
import game.Counter;
import game.GameLevel;
import listener.HitListener;

/**
 * This class create BallRemover.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that were removed.
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter removedBalls;

    /**
     * This function is constructor.
     *
     * @param game         is GameLevel
     * @param removedBalls is Counter
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.removedBalls = removedBalls;
    }

    /**
     * This function is hitEvent.
     *
     * @param beingHit is Block
     * @param hitter   is Ball
     */
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.
    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.game.getBallCounter().decrease(1);
    }
}
