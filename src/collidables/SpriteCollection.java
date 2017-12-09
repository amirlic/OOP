package collidables;

import biuoop.DrawSurface;

import java.util.ArrayList;


/**
 * This class create SpriteCollection.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class SpriteCollection {
    private ArrayList sprites;

    /**
     * This function constructor SpriteCollection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList();
    }

    /**
     * This function add sprite to the collection.
     *
     * @param s is sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * This function remove sprite to the collection.
     *
     * @param s is sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * This function notify all the sprites that time has passed.
     *
     * @param dt is double
     */
    // call timePassed() on all sprites.
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < this.sprites.size(); i++) {
            ((Sprite) this.sprites.get(i)).timePassed(dt);
        }
    }

    /**
     * This function draw all the sprites on the given DrawSurface.
     *
     * @param d is DrawSurface
     */
    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < sprites.size(); i++) {
            ((Sprite) this.sprites.get(i)).drawOn(d);
        }
    }
}
