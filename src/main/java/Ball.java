import bagel.*;
import bagel.util.*;

public class Ball extends MovingEntity {

    private static final String normalImagePath = "res/ball.png";
    private static final String fireBallImagePath = "res/fireball.png";

    /* initial values to spawn the ball at */
    public static final double DEFAULT_X = 512;
    public static final double DEFAULT_Y = 32;

    public static final double initialVelocity = 10f;
    public static final double gravityAcceleration = 0.15f;

    /* determines if the ball is to rendered */
    private boolean isOnScreen;

    /* determines if a ball should interact as a fireball */
    private boolean isFireBall;

    /* determines if this ball has previously hit the bucket, so we only get one bonus from each ball */
    private boolean hasHitBucket;

    /**
     * Constructor for a ball
     */
    public Ball(Vector2 velocityVector) {
        /* Set up the Entity at the default coordinates */
        super(normalImagePath, DEFAULT_X, DEFAULT_Y);

        /* get the initial velocity components via the velocity vector */
        //velocity = new Vector2(velocityVector.x, velocityVector.y);   // old code
        setVelocity(new Vector2(velocityVector.x, velocityVector.y));
        /* shouldn't render the ball until there is a left click */
        isOnScreen   = false;
        isFireBall   = false;
        hasHitBucket = false;
    }

    /**
     * Ball Constructor for a ball that takes in the initial coordinate for the ball to spawn at and its type
     */
    public Ball(Vector2 position, Vector2 velocityVector, boolean isFireBall) {
        /* Set up the Entity at the default coordinates */
        super(imagePathFromIsFireBall(isFireBall), position.x, position.y);
        this.isFireBall = isFireBall;
        /* get the initial velocity components via the velocity vector */
        setVelocity(new Vector2(velocityVector.x, velocityVector.y));
        /* a ball set up in this fashion should already be on the screen */
        isOnScreen = true;
        hasHitBucket = false;
    }

    /**
     * Fire Ball Constructor for a ball that takes in previous ball and returns a fireball in its place.
     * if isFireBall is set to false it returns a normal ball (making it essentially a copy constructor)
     * @param old -- The ball to replace
     * @param isFireBall -- determines if making a fire ball or not
     */
    public Ball(Ball old, boolean isFireBall){
        /* because super constructor must go first verifying the boolean to be true has to be done externally.
         * if it's false, we just use the normal image and this is essentially a copy constructor
         */
        super(imagePathFromIsFireBall(isFireBall), old.getX(), old.getY());
        this.isFireBall = isFireBall;
        setVelocity(new Vector2(old.getVelocity().x, old.getVelocity().y));
        /* a ball set up in this fashion should already be on the screen */
        isOnScreen = true;
        hasHitBucket = false;
    }

    /** Get the file path to use for a ball depending on if it is a fireball or not
     * @param isFireBall -- if the ball is a fire ball
     * @return the file path for the ball type
     */
    private static String imagePathFromIsFireBall(boolean isFireBall){
        if (isFireBall) return fireBallImagePath;
        return normalImagePath;
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
     * Determines if a ball should interact as a fireball or just as a regular ball
     * @return
     */
    public boolean isFireBall() {
        return isFireBall;
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

    /** See if a particular ball has hit the bucket before
     */
    public boolean getHasHitBucket() {
        return hasHitBucket;
    }

    /** set hasHitBucket so that no more bonus balls can be awarded from this ball. Will only set the value to true.
     * @param hasHitBucket -- boolean that tells if the ball has hit the bucket
     */
    public void setHasHitBucket(boolean hasHitBucket) {
        if (hasHitBucket)
            this.hasHitBucket = true;
    }
}
