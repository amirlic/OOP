package geometry;

import game.GameLevel;

/**
 * This class create line.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class Line {
    private Point start;
    private Point end;
    private Boolean vertical;
    private Boolean horizontal;
    // constructors

    /**
     * This function constructor line.
     *
     * @param start is Point
     * @param end   is Point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        if (GameLevel.comperation(start.getX(), end.getX())) {
            this.vertical = true;
        } else {
            this.vertical = false;
        }
        if (GameLevel.comperation(start.getY(), end.getY())) {
            this.horizontal = true;
        } else {
            this.horizontal = false;
        }
    }

    /**
     * This function constructor line.
     *
     * @param x1 is double
     * @param y1 is double
     * @param x2 is double
     * @param y2 is double
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        if (GameLevel.comperation(x1, x2)) {
            this.vertical = true;
        } else {
            this.vertical = false;
        }
        if (GameLevel.comperation(start.getY(), end.getY())) {
            this.horizontal = true;
        } else {
            this.horizontal = false;
        }
    }

    /**
     * This function return tangent.
     *
     * @return tangent
     */
    // return the tangent
    public double tangent() {
        if (!this.vertical) {
            double tan = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
            return tan;
        } else {
            return 1;
        }
    }

    /**
     * This function return constent.
     *
     * @return constent
     */
    // return the constent
    public double constent() {
        double constent = this.start.getY() - (this.start.getX() * this.tangent());
        return constent;
    }

    /**
     * This function get the point intersection.
     *
     * @param other is Line
     * @return intersection point
     */
    // Returns true if the lines intersect, false otherwise
    public Point getGeneralIntersectionPoint(Line other) {
        if (this.vertical && this.horizontal) {
            return new Point(this.start.getX(), other.constent());
        }
        if (other.vertical && this.horizontal) {
            return new Point(other.start.getX(), this.constent());
        }
        if (this.vertical) {
            return new Point(this.start.getX(), other.tangent() * this.start().getX() + other.constent());
        }
        if (other.vertical) {
            return new Point(other.start.getX(), this.tangent() * other.start().getX() + this.constent());
        }
        if (this.horizontal) {
            return new Point((this.start().getY() - other.constent()) / other.tangent(), this.start.getY());
        }
        if (other.horizontal) {
            return new Point((other.start().getY() - this.constent()) / this.tangent(), other.start.getY());
        }
        double thistangent = this.tangent();
        double othertangent = other.tangent();
        double thisconstant = this.constent();
        double otherconstant = other.constent();
        double xInstrectionPoint = (otherconstant - thisconstant) / (thistangent - othertangent);
        double yInstrectionPoint = thistangent * xInstrectionPoint + thisconstant;
        return new Point(xInstrectionPoint, yInstrectionPoint);
    }

    /**
     * This function return length.
     *
     * @return the length
     */
    // Return the length of the line
    public double length() {
        return start.distance(end);
    }

    /**
     * This function return middle.
     *
     * @return the middle
     */
    // Returns the middle point of the line
    public Point middle() {
        Point middle = new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
        return middle;
    }

    /**
     * This function return start.
     *
     * @return the point start
     */
    // Returns the start point of the line
    public Point start() {
        return this.start;
    }

    /**
     * This function return end.
     *
     * @return the point end
     */
    // Returns the end point of the line
    public Point end() {
        return this.end;
    }

    /**
     * This function check intersection.
     *
     * @param other is Line
     * @return true if intersect else false
     */
    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        if (GameLevel.comperation(this.tangent(), other.tangent())) {
            return false;
        } else {
            double x1 = this.getGeneralIntersectionPoint(other).getX();
            double y1 = this.getGeneralIntersectionPoint(other).getY();
            double minX = Math.min(this.end.getX(), this.start.getX());
            double maxX = Math.max(this.end.getX(), this.start.getX());
            double minY = Math.min(this.end.getY(), this.start.getY());
            double maxY = Math.max(this.end.getY(), this.start.getY());
            double minX2 = Math.min(other.end.getX(), other.start.getX());
            double maxX2 = Math.max(other.end.getX(), other.start.getX());
            double minY2 = Math.min(other.end.getY(), other.start.getY());
            double maxY2 = Math.max(other.end.getY(), other.start.getY());
            if ((GameLevel.biggerComperation(x1, minX) && GameLevel.lessComperation(x1, maxX))
                    && (GameLevel.lessComperation(y1, maxY) && GameLevel.biggerComperation(y1, minY))
                    && (GameLevel.biggerComperation(x1, minX2) && GameLevel.lessComperation(x1, maxX2))
                    && (GameLevel.lessComperation(y1, maxY2) && GameLevel.biggerComperation(y1, minY2))) {
                return true;
            }
            return false;
        }
    }

    /**
     * This function return point intersection.
     *
     * @param other is Line
     * @return return the point intersection
     */
    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other)) {
            return this.getGeneralIntersectionPoint(other);
        }
        return null;
    }

    /**
     * This function check equal.
     *
     * @param other is Line
     * @return true if equal else false
     */
    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if (this.isIntersecting(other) && (this.tangent() == other.tangent())
                && (this.start.equals(other.start) && this.end.equals(other.end))) {
            return true;
        }
        return false;
    }

    /**
     * This function check the closest intersection point.
     *
     * @param rect is Rectangle
     * @return true if equal else false
     */
    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point closesPoint = null;
        double mindistance = 100000;
        double currentdistance = 0;
        // check if left collision point is the closest
        Point leftintrection = this.intersectionWith(rect.getLeftLine());
        if (leftintrection != null) {
            currentdistance = leftintrection.distance(this.start());
            if (currentdistance < mindistance) {
                mindistance = currentdistance;
                closesPoint = leftintrection;
            }
        }
        // check if lower collision point is the closest
        Point lowerintrection = this.intersectionWith(rect.getLowerLine());
        if (lowerintrection != null) {
            currentdistance = lowerintrection.distance(this.start());
            if (currentdistance < mindistance) {
                mindistance = currentdistance;
                closesPoint = lowerintrection;
            }
        }
        // check if right collision point is the closest
        Point rightintrection = this.intersectionWith(rect.getRightLine());
        if (rightintrection != null) {
            currentdistance = rightintrection.distance(this.start());
            if (currentdistance < mindistance) {
                mindistance = currentdistance;
                closesPoint = rightintrection;
            }
        }
        // check if upper collision point is the closest
        Point upperintrection = this.intersectionWith(rect.getUpperLine());
        if (upperintrection != null) {
            currentdistance = upperintrection.distance(this.start());
            if (currentdistance < mindistance) {
                closesPoint = upperintrection;
            }
        }
        return closesPoint;
    }
}