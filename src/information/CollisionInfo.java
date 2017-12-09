package information;

import collidables.Collidable;
import geometry.Point;

/**
 * This class create CollisionInfo.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * This function constructor CollisionInfo.
     *
     * @param collisionPoint  is Point
     * @param collisionObject is Collidable
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * This function return the point at which the collision occurs.
     *
     * @return Point collisionPoint
     */
    // the point at which the collision occurs.
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * This function return the point at which the collision occurs.
     *
     * @return Collidable collisionObject
     */
    // the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}