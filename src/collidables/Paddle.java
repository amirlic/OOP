package collidables;

import java.awt.Color;

import game.Boundary;
import game.GameLevel;
import geometry.Rectangle;
import geometry.Point;
import information.LevelInformation;
import information.Velocity;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;

/**
 * This class create Paddle.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Color color;
    private Point upperLeft;
    private Rectangle paddle;
    private Boundary bound;
    private int speed;
    private int width, height;

    /**
     * This function constructor Paddle.
     *
     * @param keyboard         is KeyboardSensor
     * @param bound            is Boundary
     * @param levelInformation is LevelInformation
     */
    public Paddle(KeyboardSensor keyboard, Boundary bound, LevelInformation levelInformation) {
        this.keyboard = keyboard;
        this.bound = bound;
        this.color = color.yellow;
        this.width = levelInformation.paddleWidth();
        this.upperLeft = new Point(((this.bound.getLeftbound() + this.bound.getRightbound())
                / 2) - (this.width / 2), this.bound.getUpbound() - 10);
        this.height = 15;
        this.paddle = new Rectangle(this.upperLeft, this.width, this.height);
        this.speed = levelInformation.paddleSpeed();
    }

    /**
     * This function move left the paddle.
     *
     * @param dt is double
     */
    public void moveLeft(double dt) {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) && leftBoundary()) {
            this.upperLeft = new Point(this.upperLeft.getX() - this.speed * dt, this.upperLeft.getY());
            this.paddle = new Rectangle(this.upperLeft, this.width, this.height);
        }
    }

    /**
     * This function move right the paddle.
     *
     * @param dt is double
     */
    public void moveRight(double dt) {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY) && rightBoundary()) {
            this.upperLeft = new Point(this.upperLeft.getX() + this.speed * dt, this.upperLeft.getY());
            this.paddle = new Rectangle(this.upperLeft, this.width, this.height);
        }
    }

    /**
     * This function timer.
     *
     * @param dt is double
     */
    // Sprite
    public void timePassed(double dt) {
        moveLeft(dt);
        moveRight(dt);
    }

    /**
     * This function draw the paddle on the given DrawSurface.
     *
     * @param d is DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), this.width, this.height);
        // draw the line's block
        d.setColor(Color.BLACK);
        d.drawLine((int) this.paddle.getUpperLine().start().getX(), (int) this.paddle.getUpperLine().start().getY()
                , (int) this.paddle.getUpperLine().end().getX(), (int) this.paddle.getUpperLine().end().getY());
        d.drawLine((int) this.paddle.getLeftLine().start().getX(), (int) this.paddle.getLeftLine().start().getY()
                , (int) this.paddle.getLeftLine().end().getX(), (int) this.paddle.getLeftLine().end().getY());
        d.drawLine((int) this.paddle.getRightLine().start().getX(), (int) this.paddle.getRightLine().start().getY()
                , (int) this.paddle.getRightLine().end().getX(), (int) this.paddle.getRightLine().end().getY());
        d.drawLine((int) this.paddle.getLowerLine().start().getX(), (int) this.paddle.getLowerLine().start().getY()
                , (int) this.paddle.getLowerLine().end().getX(), (int) this.paddle.getLowerLine().end().getY());
    }

    /**
     * This function get collision rectangle.
     *
     * @return Rectangle
     */
    // Collidable
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * This function check if happen hit and set the correct velocity.
     *
     * @param hitter          is Ball
     * @param collisionPoint  is Point
     * @param currentVelocity is Velocity
     * @return velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        // check if collision point in the upper line
        if (region(collisionPoint) == 1) {
            // update the velocity of the ball
            currentVelocity = Velocity.fromAngleAndSpeed(300, Velocity.velocityToSpeed(dx, dy));
        } else if (region(collisionPoint) == 2) {
            // update the velocity of the ball
            currentVelocity = Velocity.fromAngleAndSpeed(330, Velocity.velocityToSpeed(dx, dy));
        } else if (region(collisionPoint) == 3) {
            // update the velocity of the ball
            //   currentVelocity = Velocity.fromAngleAndSpeed(180, currentVelocity.getDy());
            currentVelocity = Velocity.fromAngleAndSpeed(0, Velocity.velocityToSpeed(dx, dy));
        } else if (region(collisionPoint) == 4) {
            // update the velocity of the ball
            currentVelocity = Velocity.fromAngleAndSpeed(30, Velocity.velocityToSpeed(dx, dy));
        } else if (region(collisionPoint) == 5) {
            currentVelocity = Velocity.fromAngleAndSpeed(60, Velocity.velocityToSpeed(dx, dy));
        } else if (collisionPoint.getY() >= this.paddle.getLeftLine().start().getY()
                && collisionPoint.getY() <= this.paddle.getLeftLine().end().getY()
                && this.paddle.getLeftLine().end().getX() == collisionPoint.getX()) {
            // update the velocity of the ball
            currentVelocity = new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
        } else if (collisionPoint.getY() >= this.paddle.getRightLine().start().getY()
                && collisionPoint.getY() <= this.paddle.getRightLine().end().getY()
                && this.paddle.getRightLine().end().getX() == collisionPoint.getX()) {
            // update the velocity of the ball
            currentVelocity = new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * This function check when happen hit.
     *
     * @param collisionPoint is Point
     * @return region integer
     */
    public int region(Point collisionPoint) {
        // check if the hit happen in region 1
        if (this.paddle.getUpperLine().start().getX() <= collisionPoint.getX()
                && (((this.paddle.getWidth() / 5))
                + (this.paddle.getLeftLine().start().getX())) >= collisionPoint.getX()
                && this.paddle.getUpperLine().end().getY() == collisionPoint.getY()) {
            return 1;
            // check if the hit happen in region 2
        } else if ((this.paddle.getUpperLine().start().getX() + (this.paddle.getWidth() / 5)) < collisionPoint.getX()
                && (((this.paddle.getWidth() / 5) * 2)
                + (this.paddle.getLeftLine().start().getX())) > collisionPoint.getX()
                && this.paddle.getUpperLine().end().getY() == collisionPoint.getY()) {
            return 2;
            // check if the hit happen in region 3
        } else if (((this.paddle.getUpperLine().start().getX()
                + (this.paddle.getWidth() / 5 * 2))) < collisionPoint.getX()
                && (((this.paddle.getWidth() / 5) * 3)
                + (this.paddle.getLeftLine().start().getX())) > collisionPoint.getX()
                && this.paddle.getUpperLine().end().getY() == collisionPoint.getY()) {
            return 3;
            // check if the hit happen in region 4
        } else if (((this.paddle.getUpperLine().start().getX()
                + (this.paddle.getWidth() / 5 * 3))) < collisionPoint.getX()
                && (((this.paddle.getWidth() / 5) * 4)
                + (this.paddle.getLeftLine().start().getX())) > collisionPoint.getX()
                && this.paddle.getUpperLine().end().getY() == collisionPoint.getY()) {
            return 4;
            // check if the hit happen in region 5
        } else if (((this.paddle.getUpperLine().start().getX()
                + (this.paddle.getWidth() / 5 * 4))) < collisionPoint.getX()
                && ((this.paddle.getUpperLine().end().getX()) >= collisionPoint.getX()
                && this.paddle.getUpperLine().end().getY() == collisionPoint.getY())) {
            return 5;
        }
        return 0;
    }

    /**
     * This function return true if paddle in left bound.
     *
     * @return true if paddle in left bound
     */
    public Boolean leftBoundary() {
        double xLeftPosition = this.paddle.getUpperLeft().getX();
        if (xLeftPosition > this.bound.getLeftbound()) {
            return true;
        }
        return false;
    }

    /**
     * This function return true if paddle in right bound.
     *
     * @return true if paddle in right bound
     */
    public Boolean rightBoundary() {
        double xRightPosition = paddle.getUpperLeft().getX() + this.paddle.getWidth();
        if (xRightPosition < this.bound.getRightbound()) {
            return true;
        }
        return false;
    }

    /**
     * This function add the object to the game.
     *
     * @param g is Game
     */
    // Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * This function remove the object to the game.
     *
     * @param game is Game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }
}