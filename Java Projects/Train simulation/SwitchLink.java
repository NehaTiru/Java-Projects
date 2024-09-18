package hw4;
import api.*;

/**
 * SwitchLink is a class that extends the AbstractLink class, representing
 * a link with switchable connections between three points. It defines the
 * behavior for getting connected points and switching connections in a switch link.
 * @author neha2004
 */
public class SwitchLink extends AbstractLink {
    private Point pt1;
    private Point pt2;
    private Point pt3;
    private boolean turn;

    /**
     * Constructs a new SwitchLink with the given points A, B, and C.
     *
     * @param pointA The point A of the switch link.
     * @param pointB The point B of the switch link.
     * @param pointC The point C of the switch link.
     */
    public SwitchLink(Point pointA, Point pointB, Point pointC) {
        pt1 = pointA;
        pt2 = pointB;
        pt3 = pointC;
        turn = false;
    }

    /**
     * Sets the turn state for the switch link.
     *
     * @param newTurn The new turn state to set.
     */
    public void setTurn(boolean newTurn) {
        turn = newTurn;
    }

    /**
     * Returns the connected point for the given input point.
     *
     * @param inputPt The point for which the connected point is to be found.
     * @return The connected point if found, null otherwise.
     */
    @Override
    public Point getConnectedPoint(Point inputPt) {
        if (inputPt.equals(pt1)) {
            return turn ? pt3 : pt2;
        } else if (inputPt.equals(pt2) || inputPt.equals(pt3)) {
            return pt1;
        }
        return null;
    }

    /**
     * Returns the number of paths in the switch link.
     *
     * @return The number of paths (fixed at 3).
     */
    @Override
    public int getNumPaths() {
        return 3;
    }

    /**
     * Shifts the points for the position vector.
     * Not implemented for the SwitchLink class.
     *
     * @param posVector The position vector to shift.
     */
    @Override
    public void shiftPoints(PositionVector posVector) {
       
    }

    /**
     * Handles the behavior when a train enters the crossing.
     * Not implemented for the SwitchLink class.
     */
    @Override
    public void trainEnteredCrossing() {
     
    }

    /**
     * Handles the behavior when a train exits the crossing.
     * Not implemented for the SwitchLink class.
     */
    @Override
    public void trainExitedCrossing() {
       
    }
}

