import bagel.*;
import bagel.util.*;

public abstract class MovingEntity extends Entity {

    /* the MovingEntity's current velocity */
    private Vector2 velocity;

    /* method to move a MovingEntity by a given distance */
    public abstract void moveBy(Vector2 change);

    public MovingEntity(String imagePath, double x, double y) {
        super(imagePath, x, y);
    }

    public Vector2 getVelocity() {
        return new Vector2(velocity.x, velocity.y);
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
}
