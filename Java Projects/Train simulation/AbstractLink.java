package hw4;


import api.Crossable;
import api.Point;
import api.PositionVector;

/**
 * AbstractLink is an abstract class that implements the Crossable interface.
 * It provides a basic implementation for shifting points and handling train
 * crossings. Subclasses should implement the getConnectedPoint method and
 * potentially override other methods as needed.
 * @author neha2004
 *
 */
public abstract class AbstractLink implements Crossable {

    /**
     * Default constructor for the AbstractLink class.
     */
    public AbstractLink() {
    }

    /**
     * Shifts the points in the given PositionVector based on the connected point.
     * It sets point A as the connected point and calculates the next point B accordingly.
     *
     * @param positionVector The PositionVector to update.
     */
    public void shiftPoints(PositionVector positionVector) {
        Point nextPointA = this.getConnectedPoint(positionVector.getPointB());
        int nextIndexB = nextPointA.getPointIndex() == 0 ? 1 : nextPointA.getPointIndex() - 1;
        Point nextPointB = nextPointA.getPath().getPointByIndex(nextIndexB);

        positionVector.setPointA(nextPointA);
        positionVector.setPointB(nextPointB);
    }

    /**
     * Returns the number of paths associated with this link.
     * Default implementation returns 0, subclasses should override this method
     * to provide the correct number of paths.
     *
     * @return The number of paths.
     */
    public int getNumPaths() {
        return 0;
    }

    /**
     * Handles the logic for when a train enters a crossing.
     * Default implementation is empty, subclasses should override this method
     * if additional logic is required.
     */
    public void trainEnteredCrossing() {
    }

    /**
     * Handles the logic for when a train exits a crossing.
     * Default implementation is empty, subclasses should override this method
     * if additional logic is required.
     */
    public void trainExitedCrossing() {
    }
}
