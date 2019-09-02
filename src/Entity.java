import bagel.*;
import bagel.util.*;

public abstract class Entity {
    private Image image;
    protected Point point;

    public abstract void move(double dx, double dy);

    public Point getPoint() {
        return point;
    }

}