import bagel.*;
import bagel.util.*;

public class ShadowBounce extends AbstractGame {

    public static final int NUM_PEGS = 50;

    public static final boolean DEBUG_CONTROLS = true;

    public Peg[] pegs;
    public Ball ball;

    public Peg demo;

    //private static final String backgroundFile = "";
    //private Image background;

    /**
     * Game constructor
     */
    public ShadowBounce() {
        ball = new Ball();
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
                ball = new Ball();
                ball.setOnScreen(true);
            }
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
        } else {
            /* calculate all of the movement */

            /* The ball then needs to fall under the influence of gravity */
            ball.moveBy(0,- Ball.gravityAcceleration);
        }



        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }

//        if (distanceFromToSquared(x, y, balloonX, balloonY) <= 50.0 * 50.0) {
//            balloonX = random.nextDouble() * Window.getWidth();
//            balloonY = random.nextDouble() * Window.getHeight();
//        }

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

    private double distanceFromToSquared(double x1, double y1, double x2, double y2) {
        /**
         * return the distance between two points squared
         */
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

}