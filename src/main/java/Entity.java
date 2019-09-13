import bagel.*;
import bagel.util.*;

public abstract class Entity {

    /**
     * Generic screen sizes we can store the entity in. A generic entity can be anywhere on the screen.
     */
    public static final double ENTITY_X_MIN = 0;
    public static final double ENTITY_X_MAX = Window.getWidth();
    public static final double ENTITY_Y_MIN = - Window.getHeight();  // an arbitrary height
    public static final double ENTITY_Y_MAX = Window.getHeight();

    public Image image;
    private Vector2 position;
    public Rectangle rectangle;

    public abstract void moveBy(double dx, double dy);

    public Entity() {
        /* instantiate a temporary position */
        position = new Vector2();
        /* instantiate a temporary empty rectangle */
        rectangle = new Rectangle(0,0,0,0);
    }

    /**
     * Draw an entity to the screen.
     */
    public void draw() {
        image.draw(position.x, position.y);
    }

    /**
     * Getter to return the current position as a point
     * @return
     */
    public Point getPoint() {
        return new Point(position.x, position.y);
    }

    /**
     * Getter to return x coordinate
     * @return
     */
    public double getX() {
        return position.x;
    }

    /**
     * Getter to return y coordinate
     * @return
     */
    public double getY() {
        return position.y;
    }

    /**
     * Setter for the Entity's point. Only modifies if the new point is in the screen. Takes in a Point
     * @param point
     */
    public void setPoint(Point point) {
        if (isValidPosition(point.asVector())){
            this.position = point.asVector();
        }

    }

    public Vector2 getPosition() {
        return new Vector2(position.x, position.y);
    }

    public void setPosition(Vector2 position) {
          if (isValidPosition(position)){
            this.position = position;
        }
    }

    /**
     * Setter for the Entity's point. Only modifies if the new point is in the screen. Takes in coordinate pair
     * @param x
     * @param y
     */
    public void setPosition(double x, double y) {
        if (isValidPosition(x,y)){
            this.position = new Vector2(x,y);
        }
    }

    /*
     * Need some other getters to account for the differing max values. This reduces duplicate code in eg. Peg class
     */

    public static double getXMin() {
        return ENTITY_X_MIN;
    }

    public static double getXMax() {
        return ENTITY_X_MAX;
    }

    public static double getYMin() {
        return ENTITY_Y_MIN;
    }

    public static double getYMax() {
        return ENTITY_Y_MAX;
    }

    /**
     * Check if the specified point is valid
     * @param pos
     * @return
     */
    public static boolean isValidPosition(Vector2 pos){
        if (pos.x >= getXMin() && pos.x <= getXMax() && pos.y >= getYMin() && pos.y <= getYMax()){
            return true;
        }
        return false;

    }

    /**
     * Check if the specified point is valid
     * @param x
     * @param y
     * @return
     */
    public static boolean isValidPosition(double x, double y){
        if (x >= getXMin() && x <= getXMax() && y >= getYMin() && y <= getYMax()){
            return true;
        }
        return false;
    }

}