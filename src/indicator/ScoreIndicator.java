package indicator;

import collidables.Sprite;
import game.Counter;
import game.GameLevel;
import geometry.Rectangle;
import geometry.Point;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * This class create ScoreIndicator.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class ScoreIndicator implements Sprite {
    private Color color;
    private Rectangle scoreIndicator;
    private Counter scoreCounter;

    /**
     * This function constructor.
     *
     * @param upperLeft    is Point
     * @param width        is double
     * @param height       is double
     * @param color        is Color
     * @param scoreCounter is Counter
     */
    public ScoreIndicator(Point upperLeft, double width, double height, Color color, Counter scoreCounter) {
        this.scoreIndicator = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.scoreCounter = scoreCounter;
    }

    /**
     * This function draw the sprite on the given DrawSurface.
     *
     * @param d is DrawSurface
     */
    // draw the sprite to the screen
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.scoreIndicator.getUpperLeft().getX(), (int) this.scoreIndicator.getUpperLeft().getY()
                , (int) this.scoreIndicator.getWidth(), (int) this.scoreIndicator.getHeight());
        d.setColor(Color.BLACK);
        //score
        d.drawText((int) (this.scoreIndicator.getLowerLine().start().getX() + 350)
                , (int) (this.scoreIndicator.getLowerLine().start().getY() - 5)
                , "Score:" + " " + String.valueOf(scoreCounter.getValue()), 12);
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
    // Add this score to the game.
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
