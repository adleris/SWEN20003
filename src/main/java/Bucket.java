import bagel.*;
import bagel.util.*;

public class Bucket extends MovingEntity {
    private static final String imgPath = "res/bucket.png";

    /** The magnitude of the velocity of the Bucket */
    public static final double VELOCITY_MAGNITUDE = 4f;

    /** The intial coordinate that the bucket is spawned at */
    public static final Vector2 INITIAL_COORD = new Vector2(512,744);

    /* determines if the buckey is to rendered */
    private boolean isOnScreen;


    /**
     * Constructor for a Bucket.
     */
    public Bucket() {
        /* Set up the bucket up at its start coordinate */
        super(imgPath, INITIAL_COORD);

        /* set the initial velocity. Negative as we initially go left */
        setVelocity(new Vector2(-VELOCITY_MAGNITUDE,0));
        /* Buckets is always on screen */
        isOnScreen = true;
    }


    //todo this is currently just the balls move and will run it off the screen
    /**
     * Attempt to move the ball by a (dx, dy) vector
     *
     * @param change
     */
    @Override
    public void moveBy(Vector2 change) {

        /* If the move is valid, move */
        if (isValidPosition(getPosition().add(change))) {
            setPosition(getPosition().add(change));
            /* move the image to the current position */
            setRectangle(getImage().getBoundingBoxAt(getPoint()));
        }

        /* if we would have passed over the edge, reverse x velocity */
        if (change.x + getX() <= getXMin() || change.x + getX() >= getXMax()) {
            //velocity = new Vector2(-velocity.x, velocity.y);          // old code
            setVelocity(new Vector2( - getVelocity().x, getVelocity().y));
        }

        /* If the ball is off the screen delete it */
        if (getY() + change.y > getYMax()) {
            isOnScreen = false;
        }
    }

    /**
     * See if the ball is on screen and should be rendered
     *
     * @return
     */
    public boolean isOnScreen() {
        return isOnScreen;
    }

    /**
     * See ball to be on screen, doesn't allow hiding the ball Hiding the ball is
     * done internally in moveBy()
     *
     * @return
     */
    public void setOnScreen(boolean val) {
        if (val) {
            isOnScreen = true;
        }
    }
}
