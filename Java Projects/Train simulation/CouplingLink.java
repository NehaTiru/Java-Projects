package hw4;
import api.*;

/**
 * CouplingLink is a class that extends the AbstractLink class, representing
 * a link between two points. It has a fixed number of paths (2) and defines
 * the behavior for getting connected points and shifting points.
 *
 * @author neha2004
 */

public class CouplingLink extends AbstractLink {
    private Point point1;
    private Point point2;
    private static final int NUM_PATHS = 2;

    /**
     * Constructs a CouplingLink object with the provided points.
     *
     * @param pointA The first point of the link.
     * @param pointB The second point of the link.
     */
    public CouplingLink(Point pointA, Point pointB) {
        point1 = pointA;
        point2 = pointB;
    }

    /**
     * Returns the connected point for the given input point.
     *
     * @param inputPoint The point for which the connected point is to be found.
     * @return The connected point if inputPoint is either point1 or point2, otherwise null.
     */
    @Override
    public Point getConnectedPoint(Point inputPoint) {
        return inputPoint.equals(point1) ? point2 : (inputPoint.equals(point2) ? point1 : null);
    }

    /**
     * Shifts the points in the given PositionVector based on the connected point.
     *
     * @param position The PositionVector to update.
     */
    @Override
    public void shiftPoints(PositionVector position) {
        Point connectedPt = getConnectedPoint(position.getPointB());

        if (connectedPt != null) {
            position.setPointA(connectedPt);
            int connectedPtIndex = connectedPt.getPointIndex();
            Point newPathPt = connectedPtIndex == 0 ? connectedPt.getPath().getPointByIndex(1) : connectedPt.getPath().getPointByIndex(connectedPtIndex - 1);
            position.setPointB(newPathPt);
        }
    }

    /**
     * Returns the number of paths associated with this link.
     *
     * @return The number of paths (2).
     */
    @Override
    public int getNumPaths() {
        return NUM_PATHS;
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
