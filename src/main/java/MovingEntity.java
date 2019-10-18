import bagel.*;
import bagel.util.*;

/** abstract class to store a moving entity */
public abstract class MovingEntity extends Entity {

    /* the MovingEntity's current velocity */
    private Vector2 velocity;

    /** method to move a MovingEntity by a given distance
     * @param change  the vector by which to move the entity
     */
    public abstract void moveBy(Vector2 change);

    /** Constructor
     * @param imagePath location of the image file on disk
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public MovingEntity(String imagePath, double x, double y) {
        super(imagePath, x, y);
    }

    /** Constructor
     * @param imagePath location of the image file on disk
     * @param position the position vector
     */
    public MovingEntity(String imagePath, Vector2 position) {
        super(imagePath, position.x, position.y);
    }

    /** get the velocity of the moving entity
     *
     * @return the velocity
     */
    public Vector2 getVelocity() {
        return new Vector2(velocity.x, velocity.y);
    }

    /** set the velocity of the moving entity
     * @param velocity the velocity to set it to
     */
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
}
