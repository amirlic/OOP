package collidables;

import biuoop.DrawSurface;

/**
 * This class Interface Sprite.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public interface Sprite {
    /**
     * This function draw the sprite on the given DrawSurface.
     *
     * @param d is DrawSurface
     */
    // draw the sprite to the screen
    void drawOn(DrawSurface d);

    /**
     * This function notify the sprite that time has passed.
     *
     * @param dt is double
     */
    // notify the sprite that time has passed
    void timePassed(double dt);

    // Add this lives to the game.
    //void addToGame(GameLevel g);
}