package geometry;

import java.util.ArrayList;

/**
 * This class create rectangle.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;
    private Line upperLine;
    private Line leftLine;
    private Line rightLine;
    private Line lowerLine;

    /**
     * This function constructor Rectangle.
     *
     * @param upperLeft is Point
     * @param width     is double
     * @param height    is double
     */
    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.upperLine = new Line(upperLeft, upperRight);
        this.leftLine = new Line(upperLeft, lowerLeft);
        this.rightLine = new Line(upperRight, lowerRight);
        this.lowerLine = new Line(lowerLeft, lowerRight);
    }

    /**
     * This function the intersection Points with line.
     *
     * @param line is Line
     * @return a list of points
     */
    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.ArrayList intersectionPoints(Line line) {
        ArrayList pointList = new ArrayList();
        Point intersection = this.upperLine.intersectionWith(line);
        if (intersection != null) {
            pointList.add(intersection);
        }
        intersection = this.lowerLine.intersectionWith(line);
        if (intersection != null) {
            pointList.add(intersection);
        }
        intersection = this.leftLine.intersectionWith(line);
        if (intersection != null) {
            pointList.add(intersection);
        }
        intersection = this.rightLine.intersectionWith(line);
        if (intersection != null) {
            pointList.add(intersection);
        }
        return pointList;
    }

    /**
     * This function get width.
     *
     * @return the width
     */
    // Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * This function get height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * This function get upper left point.
     *
     * @return the upper left point
     */
    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * This function get left Line.
     *
     * @return the left Line
     */
    public Line getLeftLine() {
        return this.leftLine;
    }

    /**
     * This function get lower Line.
     *
     * @return the lower Line
     */
    public Line getLowerLine() {
        return this.lowerLine;
    }

    /**
     * This function get right Line.
     *
     * @return the right Line
     */
    public Line getRightLine() {
        return this.rightLine;
    }

    /**
     * This function get upper Line.
     *
     * @return the upper Line
     */
    public Line getUpperLine() {
        return this.upperLine;
    }
}

