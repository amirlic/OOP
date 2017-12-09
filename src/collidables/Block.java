package collidables;

import game.GameLevel;
import information.BlockImages;
import information.Velocity;
import listener.HitListener;
import listener.HitNotifier;
import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Image;

import geometry.Rectangle;
import geometry.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * This class create Block.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle block;
    private Color color;
    private int collisionCounter;
    private List<HitListener> hitListeners;
    private TreeMap<Integer, Color> fill;
    private HashMap<Integer, String> images;
    private Color stroke;

    /**
     * This function constructor Block.
     *
     * @param upperLeft        is Point
     * @param width            is double
     * @param height           is double
     * @param collisionCounter is integer
     * @param color            is Color
     */
    public Block(Point upperLeft, double width, double height, int collisionCounter, Color color) {
        this.block = new Rectangle(upperLeft, width, height);
        this.collisionCounter = collisionCounter;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * This function constructor Block.
     *
     * @param upperLeft is Point
     * @param width     is double
     * @param height    is double
     * @param hits      is integer
     * @param fill      is TreeMap
     * @param stroke    is Color
     */
    public Block(Point upperLeft, double width, double height, int hits, TreeMap<Integer, Color> fill, Color stroke) {
        this.block = new Rectangle(upperLeft, width, height);
        this.collisionCounter = hits;
        this.fill = fill;
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = stroke;
    }

    /**
     * This function constructor Block.
     *
     * @param upperLeft is Point
     * @param width     is double
     * @param height    is double
     * @param hits      is integer
     * @param images    is TreeMap
     * @param stroke    is Color
     */
    public Block(Point upperLeft, double width, double height, int hits
            , HashMap<Integer, String> images, Color stroke) {
        this.block = new Rectangle(upperLeft, width, height);
        this.collisionCounter = hits;
        this.images = images;
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = stroke;
    }


    /**
     * This function get collision rectangle.
     *
     * @return Rectangle
     */
    // Return the "collision shape" of the object.
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * This function draw the block on the given DrawSurface.
     *
     * @param surface is DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        if (this.collisionCounter == 0) {
            surface.setColor(this.color);
            surface.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY()
                    , (int) this.block.getWidth(), (int) this.block.getHeight());
        }
        // draw the line's block
        if (this.collisionCounter != 0) {
            if (this.fill != null) {
                surface.setColor(this.fill.get(this.collisionCounter));
                surface.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY()
                        , (int) this.block.getWidth(), (int) this.block.getHeight());
            } else if (this.images != null) {
                String fileName = this.images.get(this.collisionCounter);
                fileName = fileName.substring(fileName.indexOf('(') + 1, fileName.indexOf(')'));
                Image img = BlockImages.getImage(fileName);
                surface.drawImage((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(), img);
            }
            if (this.stroke != null) {
                surface.setColor(this.stroke);
                surface.drawLine((int) this.block.getUpperLine().start().getX(),
                        (int) this.block.getUpperLine().start().getY()
                        , (int) this.block.getUpperLine().end().getX(),
                        (int) this.block.getUpperLine().end().getY());
                surface.drawLine((int) this.block.getLeftLine().start().getX(),
                        (int) this.block.getLeftLine().start().getY()
                        , (int) this.block.getLeftLine().end().getX(),
                        (int) this.block.getLeftLine().end().getY());
                surface.drawLine((int) this.block.getRightLine().start().getX(),
                        (int) this.block.getRightLine().start().getY()
                        , (int) this.block.getRightLine().end().getX(),
                        (int) this.block.getRightLine().end().getY());
                surface.drawLine((int) this.block.getLowerLine().start().getX(),
                        (int) this.block.getLowerLine().start().getY()
                        , (int) this.block.getLowerLine().end().getX(),
                        (int) this.block.getLowerLine().end().getY());
            }
        }
    }

    /**
     * This function check if happen hit and set the correct velocity.
     *
     * @param hitter          is Ball
     * @param collisionPoint  is Point
     * @param currentVelocity is Velocity
     * @return velocity
     */
    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // check if collision point in the upper line
        if (GameLevel.lessComperation(this.block.getUpperLine().start().getX(), collisionPoint.getX())
                && GameLevel.biggerComperation(this.block.getUpperLine().end().getX(), collisionPoint.getX())
                && GameLevel.comperation(this.block.getUpperLine().end().getY(), collisionPoint.getY())) {
            // update the velocity of the ball
            currentVelocity = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
            if (this.collisionCounter >= 1) {
                this.collisionCounter--;
            }
            // check if collision point in the lower line
        } else if (GameLevel.lessComperation(this.block.getLowerLine().start().getX(), collisionPoint.getX())
                && GameLevel.biggerComperation(this.block.getLowerLine().end().getX(), collisionPoint.getX())
                && GameLevel.comperation(this.block.getLowerLine().end().getY(), collisionPoint.getY())) {
            // update the velocity of the ball
            currentVelocity = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
            if (this.collisionCounter >= 1) {
                this.collisionCounter--;
            }
            // check if collision point in the left line
        } else if (GameLevel.biggerComperation(collisionPoint.getY(), this.block.getLeftLine().start().getY())
                && GameLevel.lessComperation(collisionPoint.getY(), this.block.getLeftLine().end().getY())
                && GameLevel.comperation(this.block.getLeftLine().end().getX(), collisionPoint.getX())) {
            // update the velocity of the ball
            currentVelocity = new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
            if (this.collisionCounter >= 1) {
                this.collisionCounter--;
            }
            // check if collision point in the right line
        } else if (GameLevel.biggerComperation(collisionPoint.getY(), this.block.getRightLine().start().getY())
                && GameLevel.lessComperation(collisionPoint.getY(), this.block.getRightLine().end().getY())
                && GameLevel.comperation(this.block.getRightLine().end().getX(), collisionPoint.getX())) {
            // update the velocity of the ball
            currentVelocity = new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
            if (this.collisionCounter >= 1) {
                this.collisionCounter--;
            }
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * This function timer.
     *
     * @param dt is double
     */
    public void timePassed(double dt) {
        return;
    }

    /**
     * This function add the object to the GameLevel.
     *
     * @param g is GameLevel
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * This function remove the object to the GameLevel.
     *
     * @param gameLevel is GameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * This function is notifyHit.
     *
     * @param hitter is Ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    /**
     * This function is addHitListener.
     *
     * @param hl is HitListener
     */
    // Add hl as a listener to hit events.
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    /**
     * This function is removeHitListener.
     *
     * @param hl is HitListener
     */
    // Remove hl from the list of listeners to hit events.
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * This function is getHitPoints.
     *
     * @return int
     */
    public int getHitPoints() {
        return this.collisionCounter;
    }
}