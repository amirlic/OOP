package collidables;

import geometry.Rectangle;
import geometry.Point;
import information.Velocity;

/**
 * This interface create Collidable.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public interface Collidable {
    // Return the "collision shape" of the object.

    /**
     * This function get collision rectangle.
     *
     * @return Rectangle
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * This function check if happen hit and set the correct velocity.
     *
     * @param hitter          is Ball
     * @param collisionPoint  is Point
     * @param currentVelocity is Velocity
     * @return velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
