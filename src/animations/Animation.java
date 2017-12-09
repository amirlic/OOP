package animations;

import biuoop.DrawSurface;

/**
 * This class create interface Animation.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public interface Animation {
    /**
     * This function doOneFrame.
     *
     * @param d  is DrawSurface
     * @param dt is double
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * This function shouldStop.
     *
     * @return Boolean
     */
    boolean shouldStop();
}