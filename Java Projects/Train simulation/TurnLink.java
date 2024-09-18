package hw4;
import api.*;

/**
 * TurnLink is a class that extends the AbstractLink class, representing a link
 * with a single connection between two points in a turn-like structure.
 * It defines the behavior for getting connected points in a turn link.
 * @author neha2004
 */
public class TurnLink extends AbstractLink {
    private PointPair pair;

    /**
     * Constructs a new TurnLink with the given points 1, 2, and 3.
     * Only points 1 and 3 are used to form the connection.
     *
     * @param point1 The point 1 of the turn link.
     * @param point2 Unused point in the TurnLink.
     * @param point3 The point 3 of the turn link.
     */
    public TurnLink(Point point1, Point point2, Point point3) {
        PointPair constructedPair = new PointPair(point1, point3);
        this.pair = constructedPair;
    }

    /**
     * Returns the connected point for the given input point.
     *
     * @param point The point for which the connected point is to be found.
     * @return The connected point if found, null otherwise.
     */
    @Override
    public Point getConnectedPoint(Point point) {
        Point connectionPoint = null;
        if (point != null) {
            boolean pointEqualsPointA = point.equals(pair.getPointA());
            boolean pointEqualsPointB = point.equals(pair.getPointB());

            if (pointEqualsPointA) {
                connectionPoint = pair.getPointB();
            } else {
                if (pointEqualsPointB) {
                    connectionPoint = pair.getPointA();
                } else {
                    connectionPoint = null;
                }
            }
        }

        return connectionPoint;
    }

    /**
     * Returns the number of paths in the turn link.
     *
     * @return The number of paths (fixed at 1).
     */
    @Override
    public int getNumPaths() {
        int x = 1;
        return x;
    }

    /**
     * Handles the behavior when a train enters the crossing.
     * Not implemented for the TurnLink class.
     */
    @Override
    public void trainEnteredCrossing() {
        
    }

    /**
     * Handles the behavior when a train exits the crossing.
     * Not implemented for the TurnLink class.
     */
    @Override
    public void trainExitedCrossing() {
        
    }
}
