import bagel.*;
import bagel.util.*;

/** abstract class to hold data on a game entity */
public abstract class Entity {

    /**
     * Generic screen sizes we can store the entity in. A generic entity can be anywhere on the screen.
     * Accessed using getters, not directly
     */
    private static final double ENTITY_X_MIN = 0;
    private static final double ENTITY_X_MAX = Window.getWidth();
    private static final double ENTITY_Y_MIN =  - Window.getHeight();
    private static final double ENTITY_Y_MAX = Window.getHeight();

    private Image image;
    private Vector2 position;
    private Rectangle rectangle;

    /**
     * Constructor for an Entity
     * @param imagePath lovation of image file on disk
     * @param x x coordinate
     * @param y y coordinate
     */
    public Entity(String imagePath, double x, double y) {
        /* image from file */
        image = new Image(imagePath);

        /* position from coordinates */
        position = new Vector2(x,y);

        /* rectangle from box at position */
        rectangle = new Rectangle(image.getBoundingBoxAt(getPoint()));
    }

    /**
     * Draw an entity to the screen.
     */
    public void draw() {
        image.draw(position.x, position.y);
    }

    /**
     * Get the image associated with an Entity
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Getter for Entity's rectangle
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        return new Rectangle(rectangle);
    }

    /**
     * Setter for Entity's Rectangle
     * @param rectangle the new rectangle
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Getter to return the current position as a point
     * @return the point
     */
    public Point getPoint() {
        return new Point(position.x, position.y);
    }

    /**
     * Getter to return x coordinate
     * @return the x coordinate
     */
    public double getX() {
        return position.x;
    }

    /**
     * Getter to return y coordinate
     * @return the y coordinate
     */
    public double getY() {
        return position.y;
    }

    /**
     * Getter for position, returns a Vector2
     * @return the position vector
     */
    public Vector2 getPosition() {
        return new Vector2(position.x, position.y);
    }

    /**
     * Setter for position, takes in a position
     * @param position the position
     */
    public void setPosition(Vector2 position) {
          if (isValidPosition(position)){
            this.position = position;
        }
    }

    /**
     * Need some other getters to account for the differing max values. This reduces duplicate code in eg. Peg class
     */

    /** get the min x coordinate of an entity
     * @return the value
     */
    public static double getXMin() {
        return ENTITY_X_MIN;
    }

    /** get the max x coordinate of an entity
     * @return the value
     */
    public static double getXMax() {
        return ENTITY_X_MAX;
    }

    /** get the min y coordinate of an entity
     * @return the value
     */
    public static double getYMin() {
        return ENTITY_Y_MIN;
    }

    /** get the max y coordinate of an entity
     * @return the value
     */
    public static double getYMax() {
        return ENTITY_Y_MAX;
    }

    /**
     * Check if the specified point is valid
     * @param pos the position
     * @return if it's valid
     */
    public static boolean isValidPosition(Vector2 pos){
        if (pos.x >= getXMin() && pos.x <= getXMax() && pos.y >= getYMin() && pos.y <= getYMax()){
            return true;
        }
        return false;
    }
}