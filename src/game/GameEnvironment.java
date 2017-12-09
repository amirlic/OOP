package game;

import collidables.Collidable;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import information.CollisionInfo;

import java.util.ArrayList;

/**
 * This class create GameEnvironment.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class GameEnvironment {
    private ArrayList collidableList;

    /**
     * This function constructor Game Environment.
     *
     * @param collidableList is ArrayList
     */
    public GameEnvironment(ArrayList collidableList) {
        this.collidableList = collidableList;
    }

    /**
     * This function add colidable.
     *
     * @param c is Collidable
     */
    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * This function remove colidable.
     *
     * @param c is Collidable
     */
    // remove the given collidable to the environment.
    public void remomveCollidable(Collidable c) {
        this.collidableList.remove(c);
    }

    /**
     * This function get info about the collision.
     *
     * @param trajectory is Line
     * @return CollisionInfo
     */
    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        for (int i = 0; i < this.collidableList.size(); i++) {
            // get the rectangle that is the block
            Rectangle collisionRectangle = ((Collidable) this.collidableList.get(i)).getCollisionRectangle();
            // check if happen collision
            if (collisionRectangle.intersectionPoints(trajectory).size() > 0) {
                Point collisionPoint = trajectory.closestIntersectionToStartOfLine(collisionRectangle);
                CollisionInfo collisionInfo = new CollisionInfo(collisionPoint,
                        (Collidable) this.collidableList.get(i));
                return collisionInfo;
            }
        }
        return null;
    }
}