package collidables;

import game.GameEnvironment;
import game.GameLevel;
import geometry.Line;
import information.CollisionInfo;
import information.Velocity;
import biuoop.DrawSurface;
import geometry.Point;

import java.awt.Color;

/**
 * This class create Ball.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity speed;
    private GameEnvironment gameEnvironment;

    /**
     * This function constructor Ball.
     *
     * @param center          is Point
     * @param r               is int
     * @param color           is Color
     * @param gameEnvironment is GameEnvironment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.radius = r;
        this.center = center;
        this.color = color;
        speed = new Velocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * This function constructor Ball.
     *
     * @param x               is double
     * @param y               is double
     * @param r               is int
     * @param color           is Color
     * @param gameEnvironment is GameEnvironment
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        speed = new Velocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * This function return x of the center.
     *
     * @return x of the center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * This function return y of the center.
     *
     * @return y of the center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * This function return radius.
     *
     * @return the radius
     */
    public int getSize() {
        return (int) this.radius;
    }

    /**
     * This function return color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }


    /**
     * This function update velocity.
     *
     * @param v is Velocity
     */
    public void setVelocity(Velocity v) {
        this.speed = v;
    }

    /**
     * This function update velocity.
     *
     * @param dx is double
     * @param dy is double
     */
    public void setVelocity(double dx, double dy) {
        this.speed = new Velocity(dx, dy);
    }

    /**
     * This function return velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.speed;
    }

    /**
     * This function move the ball one step.
     *
     * @param dt is double
     */
    public void moveOneStep(double dt) {
        Point currentposition = this.center;
        Point applyPoint = getVelocity().applyToPoint(currentposition, dt);
        Point endTragectory = new Point(applyPoint.getX(), applyPoint.getY());
        // create trajectory
        Line trajectory = new Line(currentposition, endTragectory);
        CollisionInfo collisionInfo;
        collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        // if happen collision update the ball velocity
        if (collisionInfo != null) {
            this.setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.speed));
        } else {
            this.center = this.getVelocity().applyToPoint(this.center, dt);
            if (this.center.getX() > 900 || this.center.getX() < 0) {
                this.center = this.getVelocity().applyToPoint(new Point(100, 100), dt);
            }
        }
    }

    /**
     * This function draw the ball on the given DrawSurface.
     *
     * @param surface is DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * This function timer.
     *
     * @param dt is double
     */
    public void timePassed(double dt) {
        moveOneStep(dt);
    }

    /**
     * This function add the object to the game.
     *
     * @param g is Game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * This function remove the object to the game.
     *
     * @param game is Game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
