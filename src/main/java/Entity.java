import bagel.*;
import bagel.util.*;

public abstract class Entity {

    /**
     * Generic screen sizes we can store the entity in. A generic entity can be anywhere on the screen.
     * Accessed using getters, not directly
     */
    private static final double ENTITY_X_MIN = 0;
    private static final double ENTITY_X_MAX = Window.getWidth();
    /* Y_MIN is an arbitrary height above the screen so that the ball can go above it */
    private static final double ENTITY_Y_MIN = - Window.getHeight();
    private static final double ENTITY_Y_MAX = Window.getHeight();

    private Image image;
    private Vector2 position;
    private Rectangle rectangle;

    /* method to move an Entity by a given distance */
    public abstract void moveBy(Vector2 change);

    /**
     * Constructor for an Entity
     * @param imagePath
     * @param x
     * @param y
     */
    public Entity(String imagePath, double x, double y) {
        /* image from file */
        image = new Image(imagePath);

        /* position from coordinates */
        position = new Vector2(x,y);

        /* rectangle from box at position */
        rectangle = new Rectangle(image.getBoundingBoxAt(getPoint()));
    }

    public String toString() {
        return String.format("Entity at %f, %f", position.x, position.y);
    }

    /**
     * Draw an entity to the screen.
     */
    public void draw() {
        image.draw(position.x, position.y);
    }

    /**
     * Get the image associated with an Entity
     * @return
     */
    public Image getImage() {
        return image;
    }

    /**
     * Getter for Entity's rectangle
     * @return
     */
    public Rectangle getRectangle() {
        return new Rectangle(rectangle);
    }

    /**
     * Setter for Entity's Rectangle
     * @param rectangle
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
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
     * Getter for position, returns a Vector2
     * @return
     */
    public Vector2 getPosition() {
        return new Vector2(position.x, position.y);
    }

    /**
     * Setter for position, takes in a position
     * @param position
     */
    public void setPosition(Vector2 position) {
          if (isValidPosition(position)){
            this.position = position;
        }
    }

    /**
     * Setter for position. Takes in coordinate pair
     * @param x
     * @param y
     */
    public void setPosition(double x, double y) {
        if (isValidPosition(x,y)){
            this.position = new Vector2(x,y);
        }
    }

    /**
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