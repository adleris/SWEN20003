import bagel.*;
import bagel.util.*;
import java.lang.Math;

public class ShadowBounce extends AbstractGame {

    public static final int NUM_PEGS = 50;

    public static final boolean DEBUG_CONTROLS = true;

    public Peg[] pegs;
    public Ball2 ball;

    private Vector2 velocity;

    /**
     * Game constructor
     */
    public ShadowBounce() {
        ball = new Ball2(new Vector2(0,0));
        pegs = new Peg[NUM_PEGS];
        for (int i=0; i<NUM_PEGS; i++){
            pegs[i] = new Peg();
        }
    }

    /**
     * The entry point for the program.
     */
    public static void main(String args[]) {
        ShadowBounce game = new ShadowBounce();
        game.run();
    }


    /**
     * Performs a state update. This simple example shows an image that can be controlled with the arrow keys, and
     * allows the game to exit when the escape key is pressed.
     */
    @Override
    public void update(Input input) {
        double speed = Ball.initialVelocity;

        if (! ball.isOnScreen()){
            /* Check if we should make a new ball */
            if (input.isDown(MouseButtons.LEFT)) {
                Vector2 mousepos = input.getMousePosition().asVector();
                ball = new Ball2(velocityFromMouse(mousepos));
                ball.setOnScreen(true);

                //System.out.format("At %s\n", velocity.toString());
            }
        } else {
            /* calculate all of the movement */
            if (DEBUG_CONTROLS){
                /*
                 * calculate new positions
                 */
                if (input.isDown(Keys.LEFT) && (ball.getX() - speed >= 0)) {
                    ball.moveBy( -speed,0);
                }
                if (input.isDown(Keys.RIGHT) && (ball.getX() + speed <= Window.getWidth())) {
                    ball.moveBy(speed, 0);
                }
                if (input.isDown(Keys.UP) && (ball.getY() - speed >= 0)) {
                    ball.moveBy(0, -speed);
                }
                if (input.isDown(Keys.DOWN) && (ball.getY() + speed <= Window.getHeight())) {
                    ball.moveBy(0,speed);
                }
            }
            ball.velocity.add(new Vector2(0, - ball.gravityAcceleration));
            ball.moveBy(ball.velocity);
        }



        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }

        /* check if there is a collision between the ball and a peg */
        for (Peg peg : pegs){
            if (ball.rectangle.intersects(peg.rectangle)){
                peg.destroy();
            }
        }

        /* Render everything to the screen */
        renderToScreen();
    }

    /**
     * Render All entities and score to the screen
     */
    public void renderToScreen(){
        if (ball.isOnScreen()) {
            ball.draw();
        }
        for (int i=0; i<NUM_PEGS;i++){
            if (! pegs[i].isDestroyed()) {
                pegs[i].draw();
            }
        }
    }

    private Vector2 velocityFromMouse(Vector2 mouse){
        System.out.format("\n\n\n%f, %f \n\n\n", mouse.x, mouse.y);
        double distance = Math.sqrt( (mouse.x - ball.getX()) * (mouse.x - ball.getX()) +
                                     (mouse.y - ball.getY()) * (mouse.y - ball.getY()) );
        double velX = (ball.DEFAULT_X + (mouse.x - ball.DEFAULT_X) * ball.initialVelocity) / distance;
        double velY = (ball.DEFAULT_Y + (mouse.y - ball.DEFAULT_Y) * ball.initialVelocity) / distance;
        return new Vector2(velX, velY);
    }


    private double distanceFromToSquared(double x1, double y1, double x2, double y2) {
        /**
         * return the distance between two points squared
         */
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

}