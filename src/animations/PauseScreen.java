package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This class create pauseScreen.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * This function constructor.
     *
     * @param k is KeyboardSensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * This function doOneFrame.
     *
     * @param d  is DrawSurface
     * @param dt is double
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
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