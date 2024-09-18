package hw4;

import api.*;

/**
 * MultiFixedLink is a class that extends the AbstractLink class, representing
 * a fixed link with multiple connections between different points. It defines
 * the behavior for getting connected points, shifting points, and the number of paths.
 * @author neha2004
 */
public class MultiFixedLink extends AbstractLink {
    private PointPair[] pairs;

    /**
     * Constructs a new MultiFixedLink with the given array of PointPair objects.
     *
     * @param inputPairs The array of PointPair objects representing the connections.
     */
    public MultiFixedLink(PointPair[] inputPairs) {
        pairs = inputPairs;
    }

    /**
     * Returns the connected point for the given input point.
     *
     * @param point The point for which the connected point is to be found.
     * @return The connected point if found, null otherwise.
     */
    @Override
    public Point getConnectedPoint(Point point) {
        for (PointPair pair : pairs) {
            if (pair.getPointA().equals(point)) {
                return pair.getPointB();
            } else if (pair.getPointB().equals(point)) {
                return pair.getPointA();
            }
        }
        return null;
    }

    /**
     * Returns the number of paths associated with this link.
     *
     * @return The number of paths (equal to the length of the pairs array).
     */
    @Override
    public int getNumPaths() {
        return pairs.length;
    }

    /**
     * Handles the logic for shifting points in a PositionVector.
     *
     * @param posVector The PositionVector to update.
     */
    @Override
    public void shiftPoints(PositionVector posVector) {
        Point initialPtB = posVector.getPointB();
        Point connectedPt = getConnectedPoint(initialPtB);

        if (connectedPt != null) {
            posVector.setPointA(initialPtB);
            posVector.setPointB(connectedPt);
        }
    }

    /**
     * Handles the logic for when a train enters a crossing.
     * Default implementation is empty, as no specific logic is required for this class.
     */
    @Override
    public void trainEnteredCrossing() {
    }

    /**
     * Handles the logic for when a train exits a crossing.
     * Default implementation is empty, as no specific logic is required for this class.
     */
    @Override
    public void trainExitedCrossing() {
    }
}

