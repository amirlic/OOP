package information;

import geometry.Point;

/**
 * This class create Velocity.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double dx;
    private double dy;

    /**
     * This function constructor Velocity.
     *
     * @param dx is double
     * @param dy is double
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This function return dx.
     *
     * @return the dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * This function return dy.
     *
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * This function check equals.
     *
     * @param p  is Point
     * @param dt is double
     * @return Take a point with position (x,y) and return a new point
     */
    public Point applyToPoint(Point p, double dt) {
        p = new Point(p.getX() + this.dx * dt, p.getY() + this.dy * dt);
        return p;
    }

    /**
     * This function static constarctor Velocity.
     *
     * @param angle is double
     * @param speed is double
     * @return Take a angle and speed and return Velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        if (angle == 45) {
            angle = 46;
        }
        double angleInRadians = Math.PI * (angle / 180);
        double dx = speed * Math.sin(angleInRadians);
        double dy = -speed * Math.cos(angleInRadians);
        return new Velocity(dx, dy);
    }

    /**
     * This function return speed.
     *
     * @param radius is int
     * @return the speed
     */
    public static double speed(int radius) {
        double speed;
        if (radius < 50) {
            speed = 100 / radius;
        } else {
            speed = 100 / 50;
        }
        return speed;
    }

    /**
     * This function calculate speed vector with Pitagoras .
     *
     * @param dx is double
     * @param dy is double
     * @return speed
     */
    public static double velocityToSpeed(double dx, double dy) {
        double speed = (dx * dx) + (dy * dy);
        speed = Math.sqrt(speed);
        return speed;
    }
}