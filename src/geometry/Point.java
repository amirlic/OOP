package geometry;

/**
 * This class create point.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class Point {
    private double x;
    private double y;

    // constructor

    /**
     * This function constructor line.
     *
     * @param x is double
     * @param y is double
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This function calculate distance.
     *
     * @param other is Point
     * @return distance
     */
    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        return Math.sqrt(((this.getX() - other.getX())
                * (this.getX() - other.getX())) + ((this.getY() - other.getY())
                * (this.getY() - other.getY())));
    }

    /**
     * This function check equals.
     *
     * @param other is Point
     * @return true if equals else false
     */
    // equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        if ((this.getX() == other.getX()) && (this.getY() == other.getY())) {
            return true;
        }
        return false;
    }

    /**
     * This function return x value.
     *
     * @return x value
     */
    // Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * This function return y value.
     *
     * @return y value
     */
    public double getY() {
        return this.y;
    }
}