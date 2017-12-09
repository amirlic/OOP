package animations;

import game.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This class create EndScreen.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter numOfLives;
    private Counter score;

    /**
     * This function constructor.
     *
     * @param k          is KeyboardSensor
     * @param numOfLives is Counter
     * @param score      is Counter
     */
    public EndScreen(KeyboardSensor k, Counter numOfLives, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.numOfLives = numOfLives;
        this.score = score;
    }

    /**
     * This function doOneFrame.
     *
     * @param d  is DrawSurface
     * @param dt is double
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.numOfLives.getValue() == 0) {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is" + this.score.getValue(), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 32);
        }
    }

    /**
     * This function shouldStop.
     *
     * @return Boolean
     */
    public boolean shouldStop() {
        return this.stop;
    }
}