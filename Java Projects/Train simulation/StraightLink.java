package hw4;

import api.Point;

/**
 * StraightLink is a class that extends the AbstractLink class, representing
 * a straight connection between two points. It defines the behavior for getting
 * connected points in a straight link.
 * @author neha2004
 */
public class StraightLink extends AbstractLink {
    private Point endPtA;
    private Point endPtB;

    /**
     * Constructs a new StraightLink with the given end points A and B.
     *
     * @param endA The end point A of the straight link.
     * @param endB The end point B of the straight link.
     * @param endC Unused parameter, has no effect on the construction of the object.
     */
    public StraightLink(Point endA, Point endB, Point endC) {
        endPtA = endA;
        endPtB = endB;
    }

    /**
     * Returns the connected point for the given input point.
     *
     * @param point The point for which the connected point is to be found.
     * @return The connected point (either endPtA or endPtB) if found, null otherwise.
     */
    public Point getConnectedPoint(Point point) {
        return (point == endPtA) ? endPtB : endPtA;
    }
}

