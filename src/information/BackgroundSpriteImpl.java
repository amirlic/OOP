package information;

import biuoop.DrawSurface;
import collidables.Sprite;

import java.awt.Color;
import java.awt.Image;

/**
 * This class is BackgroundSpriteImpl.
 */
public class BackgroundSpriteImpl implements Sprite {
    private Image image;
    private Color color;

    /**
     * This function constructor..
     *
     * @param img is Image
     */
    public BackgroundSpriteImpl(Image img) {
        this.image = img;
    }

    /**
     * This function constructor.
     *
     * @param color is Color
     */
    public BackgroundSpriteImpl(Color color) {
        this.color = color;
    }

    @Override
    /**
     * This function draw the image.
     *
     * @param d          is DrawSurface
     */
    public void drawOn(DrawSurface d) {
        if (this.image != null) {
            d.drawImage(0, 0, this.image);
        } else {
            d.setColor(this.color);
            d.fillRectangle(0, 0, 800, 600);
        }
    }

    @Override
    /**
     * This function notify that time has passed.
     *
     * @param dt          is double
     */
    public void timePassed(double dt) {
        return;
    }
}
