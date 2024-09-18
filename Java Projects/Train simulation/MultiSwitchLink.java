package hw4;

import api.*;

/**
 * MultiSwitchLink is a class that extends the AbstractLink class, representing
 * a switch link with multiple connections between different points. It defines
 * the behavior for getting connected points, switching connections, shifting points,
 * and the number of paths.
 * @author neha2004
 */
public class MultiSwitchLink extends AbstractLink {
    private PointPair[] pairs;

    /**
     * Constructs a new MultiSwitchLink with the given array of PointPair objects.
     *
     * @param inputPairs The array of PointPair objects representing the connections.
     */
    public MultiSwitchLink(PointPair[] inputPairs) {
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
            if (point.equals(pair.getPointA())) {
                return pair.getPointB();
            } else if (point.equals(pair.getPointB())) {
                return pair.getPointA();
            }
        }
        return null;
    }

    /**
     * Switches the connection at the specified index with a new PointPair.
     *
     * @param newPair The new PointPair for the connection.
     * @param index The index of the connection to be replaced.
     */
    public void switchConnection(PointPair newPair, int index) {
        if (index >= 0 && index < pairs.length) {
            pairs[index] = newPair;
        }
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
        Point nextPtA = getConnectedPoint(posVector.getPointB());
        Point nextPtB = (nextPtA.getPointIndex() == 0) ? nextPtA.getPath().getPointByIndex(1) : nextPtA.getPath().getPointByIndex(nextPtA.getPointIndex() - 1);

        posVector.setPointA(nextPtA);
        posVector.setPointB(nextPtB);
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

