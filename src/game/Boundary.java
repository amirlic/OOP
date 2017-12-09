package game;

import geometry.Point;

/**
 * This class create Boundary.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class Boundary {
    private int rightbound;
    private int leftbound;
    private int upbound;
    private int downbound;

    /**
     * This function constructor Boundery.
     *
     * @param right is int
     * @param left  is int
     * @param up    is int
     * @param down  is int
     */
    public Boundary(int right, int left, int up, int down) {
        this.rightbound = right;
        this.leftbound = left;
        this.upbound = up;
        this.downbound = down;
    }

    /**
     * This function return right bound.
     *
     * @return right bound
     */
    public int getRightbound() {
        return this.rightbound;
    }

    /**
     * This function return left bound.
     *
     * @return left bound
     */
    public int getLeftbound() {
        return this.leftbound;
    }

    /**
     * This function return up bound.
     *
     * @return up bound
     */
    public int getUpbound() {
        return this.upbound;
    }

    /**
     * This function return down bound.
     *
     * @return down bound
     */
    public int getDownbound() {
        return this.downbound;
    }

    /**
     * This function return true if X in bound.
     *
     * @param p      is point
     * @param radius is int
     * @return true if X in bound
     */
    public boolean inXBound(Point p, int radius) {
        if (p.getX() > (this.leftbound + radius) && p.getX() < (this.rightbound - radius)) {
            return true;
        }
        return false;
    }

    /**
     * This function return true if Y in bound.
     *
     * @param p      is point
     * @param radius is int
     * @return true if Y in bound
     */
    public boolean inYBound(Point p, int radius) {
        if (p.getY() < (this.downbound - radius) && p.getY() > (this.upbound + radius)) {
            return true;
        }
        return false;
    }

}