package hw4;

import api.*;

/**
 * DeadEndLink is a class that extends the AbstractLink class, representing
 * a dead-end link where there is no connected point. It defines the behavior
 * for getting connected points, shifting points, and the number of paths.
 * @author neha2004
 */
public class DeadEndLink extends AbstractLink {

    /**
     * Returns the connected point for the given input point.
     * Since this is a dead-end link, it always returns null.
     *
     * @param point The point for which the connected point is to be found.
     * @return null, as there is no connected point in a dead-end link.
     */
    @Override
    public Point getConnectedPoint(Point point) {
        return null;
    }

    /**
     * Returns the number of paths associated with this link.
     * In this case, it returns 1, since there is only one path (the dead-end path).
     *
     * @return The number of paths (1).
     */
    @Override
    public int getNumPaths() {
        int pathIndex = 1;
        return pathIndex;
    }

    /**
     * Handles the logic for shifting points in a PositionVector.
     * Default implementation is empty, as no specific logic is required for this class.
     *
     * @param positionVector The PositionVector to update.
     */
    @Override
    public void shiftPoints(PositionVector positionVector) {
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
