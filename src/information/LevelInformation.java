package information;

import collidables.Block;
import collidables.Sprite;

import java.util.List;

/**
 * This class is interface.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public interface LevelInformation {
    /**
     * this function is numberOfBalls.
     *
     * @return int
     */
    int numberOfBalls();

    /**
     * this function is initialBallVelocities.
     *
     * @return List of velocity
     */
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    List<Velocity> initialBallVelocities();

    /**
     * this function is paddleSpeed.
     *
     * @return int
     */
    int paddleSpeed();

    /**
     * this function is paddleWidth.
     *
     * @return int
     */
    int paddleWidth();

    /**
     * this function is levelName.
     *
     * @return String
     */
    // the level name will be displayed at the top of the screen.
    String levelName();

    /**
     * this function is getBackground.
     *
     * @return Sprite
     */
    // Returns a sprite with the background of the level
    Sprite getBackground();

    /**
     * this function is blocks.
     *
     * @return List of Blocks
     */
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    List<Block> blocks();

    /**
     * this function is numberOfBlocksToRemove.
     *
     * @return int
     */
    // Number of levels that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    int numberOfBlocksToRemove();
}