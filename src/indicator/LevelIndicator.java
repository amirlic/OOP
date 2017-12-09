package indicator;

import collidables.Sprite;
import game.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * This class create LevelIndicator.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class LevelIndicator implements Sprite {
    private String levelName;

    /**
     * This function constructor.
     *
     * @param levelName is Color
     */
    public LevelIndicator(String levelName) {
        this.levelName = levelName;
    }

    /**
     * This function draw the sprite on the given DrawSurface.
     *
     * @param d is DrawSurface
     */
    // draw the sprite to the screen
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(650, 15, "LevelName:" + " " + this.levelName, 12);

    }

    /**
     * This function notify the sprite that time has passed.
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
    // Add this score to the game.
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
