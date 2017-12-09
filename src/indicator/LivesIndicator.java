package indicator;

import collidables.Sprite;
import game.Counter;
import game.GameLevel;
import biuoop.DrawSurface;

/**
 * This class create LivesIndicator.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class LivesIndicator implements Sprite {
    private Counter livesCounter;

    /**
     * This function constructor.
     *
     * @param livesCounter is Counter
     */
    public LivesIndicator(Counter livesCounter) {
        this.livesCounter = livesCounter;
    }

    /**
     * This function draw the sprite on the given DrawSurface.
     *
     * @param d is DrawSurface
     */
    // draw the sprite to the screen
    public void drawOn(DrawSurface d) {
        //lives
        d.drawText(50, 15, "Lives:" + " " + String.valueOf(livesCounter.getValue()), 12);
    }

    /**
     * This function notify the sprite that time has passed.
     *
     * @param dt is double
     */
    // notify the sprite that time has passed
    public void timePassed(double dt) {
        return;
    }

    /**
     * This function add the object to the game.
     *
     * @param g is Game
     */
    // Add this lives to the game.
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}

