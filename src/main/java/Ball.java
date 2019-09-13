import bagel.*;
import bagel.util.*;

public class Ball extends Entity {

    private static final String imgPath = "res/ball.png";

    /* initial values to spawn the ball at */
    public static final double DEFAULT_X = 512;
    public static final double DEFAULT_Y = 32;

    public static final double initialVelocity = 10f;
    public static final double gravityAcceleration = 0.15f;

    /* the ball's current velocity */
    private Vector2 velocity;

    /* determines if the ball is to rendered */
    private boolean isOnScreen;

    /**
     * Constructor for a ball
     */
    public Ball(Vector2 velocityVector) {
        /* Set up the Entity at the default coordinates */
        super(imgPath, DEFAULT_X, DEFAULT_Y);

        /* get the initial velocity components via the velocity vector */
        velocity = new Vector2(velocityVector.x, velocityVector.y);

        /* shouldn't render the ball until there is a left click */
        isOnScreen = false;
    }

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
            velocity = new Vector2(-velocity.x, velocity.y);
        }

        /* If the ball is off the screen delete it */
        if (getY() + change.y > getYMax()) {
            isOnScreen = false;
        }
    }

    public Vector2 getVelocity() {
        return new Vector2(velocity.x, velocity.y);
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
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
     * See ball to be on screen, doesn't allow hiding the ball
     * Hiding the ball is done internally in moveBy()
     *
     * @return
     */
    public void setOnScreen(boolean val) {
        if (val) {
            isOnScreen = true;
        }
    }
}

