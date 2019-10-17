import bagel.*;
import bagel.util.*;

public class PowerUp extends MovingEntity {
    private static final String imgPath = "res/powerup.png";

    /** 1/chance that a powerup spawns at the start of a turn */
    public static final int CREATION_CHANCE = 10;

    /** The magnitude of the velocity of the PowerUp */
    public static final double VELOCITY_MAGNITUDE = 3f;

    /** The distance that the powerup will pick a new spot to go to. The spec says to have this set to 1px but there
     * were some issues (floating point?) and making it larger fixed the issue */
    public static final double DIST_TO_DEST = 5f;

    /* the destination the the point travels to after being initialised */
    public Vector2 destination;

    /* determines if the powerup is to rendered */
    private boolean isOnScreen;


    /**
     * Constructor for a PowerUp. Generates a random initial coordinate
     */
    public PowerUp() {
        /* Set up the powerup at random coordinates */
        super(imgPath, randomPosition());

        /* pick a random destination */
        destination = randomPosition();

        /* figure out the velocity vector between the generated start position and destination */
        setVelocity(velocityFromSourceToDest(getPosition(), destination));

        /* if the powerup exists, it is on screen */
        isOnScreen = true;
    }


    //todo this is currently just the balls move and will run it off the screen
    /**
     * Attempt to move the power-up by a (dx, dy) vector
     *
     * @param change The change to move the power up by
     */
    @Override
    public void moveBy(Vector2 change) {
        /* figure out the distance to the destination */
        double distance = Math.sqrt((destination.x - getX()) * (destination.x - getX())
                + (destination.y - getY()) * (destination.y - getY()));

        /* if is valid position and distance > DIST_TO_DEST move; else get new coord */
        if (isValidPosition(getPosition().add(change)) && distance > DIST_TO_DEST) {
            setPosition(getPosition().add(change));
            /* move the image to the current position */
            // todo: inside moving entity, make a method to move the rectangle to the point
            setRectangle(getImage().getBoundingBoxAt(getPoint()));
        } else if (distance <= DIST_TO_DEST) {
            destination = randomPosition();
            setVelocity(velocityFromSourceToDest(getPosition(), destination));
        }


    }

    /** Get a random Vector2 position within the bound sof the game window */
    private static Vector2 randomPosition() {
        return new Vector2(Peg.randomInRange(getXMin(), getXMax()), Peg.randomInRange(getYMin(), getYMax()));
    }


    /**
     * Find the velocity of the powerup based on current position and destination
     *
     * @param source source position
     * @param dest   destination position
     * @return       vector2 with the velocity needed between the points
     */
    public static Vector2 velocityFromSourceToDest(Vector2 source, Vector2 dest) {
        double distance = Math.sqrt((dest.x - source.x) * (dest.x - source.x)
                + (dest.y - source.y) * (dest.y - source.y));
        double velX = (dest.x - source.x) * VELOCITY_MAGNITUDE / distance;
        double velY = (dest.y - source.y) * VELOCITY_MAGNITUDE / distance;
        return new Vector2(velX, velY);
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
