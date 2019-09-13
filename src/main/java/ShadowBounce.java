import bagel.*;
import bagel.util.*;
import java.lang.Math;

public class ShadowBounce extends AbstractGame {

    /* the number of pegs to create */
    public static final int NUM_PEGS = 50;
    /* the number of pegs left on the screen */
    private int numPegsRemaining;

    /* store the game items */
    public Peg[] pegs;
    public Ball ball;


    /**
     * Game constructor
     */
    public ShadowBounce() {
        ball = new Ball(new Vector2(0,0));
        pegs = new Peg[NUM_PEGS];
        for (int i=0; i<NUM_PEGS; i++){
            pegs[i] = new Peg();
        }
        numPegsRemaining = NUM_PEGS;
    }

    /**
     * Start the show
     */
    public static void main(String args[]) {
        ShadowBounce game = new ShadowBounce();
        game.run();
    }

    /**
     * Game loop: Update the ball's velocity and run collision checks. Initialises the ball if need be.
     */
    @Override
    public void update(Input input) {

        /* Check if there is a ball initialised */
        if (! ball.isOnScreen()){
            /* check if a new one needs to be made
            * NB: This needs to be 2 separate conditions, there was a bug where when these were in one condition an
            * invisible wall would destroy some pegs at the start
            */
            if (input.isDown(MouseButtons.LEFT)) {
                Vector2 mousePos = input.getMousePosition().asVector();
                ball = new Ball(velocityFromMouse(mousePos));
                ball.setOnScreen(true);
            }
        } else {
            /* the ball is already on the screen: adjust its acceleration according to gravity, then move it */
            ball.velocity = ball.velocity.add(new Vector2(0, - Ball.gravityAcceleration));
            ball.moveBy(ball.velocity);
        }

        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }

        /* see if the ball hit anything */
        checkCollisions();

        /* render everything to the screen */
        renderToScreen();
    }

    /**
     * Check if the ball has collided with any of the remaining pegs, end the game if there are none left.
     */
    private void checkCollisions() {
        /* check if there is a collision between the ball and a peg */
        for (Peg peg : pegs){
            if (ball.rectangle.intersects(peg.rectangle) && ! peg.isDestroyed()){
                peg.destroy();
                numPegsRemaining--;
            }
        }

        /* if there are no pegs left we should end the game */
        if (numPegsRemaining <= 0){
            Window.close();
        }
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

    /**
     * Find the initial velocity of the ball from the mouse coordinates
     * @param mouse
     * @return
     */
    public static Vector2 velocityFromMouse(Vector2 mouse){
        double distance = Math.sqrt( (mouse.x - Ball.DEFAULT_X) * (mouse.x - Ball.DEFAULT_X) +
                                     (mouse.y - Ball.DEFAULT_Y) * (mouse.y - Ball.DEFAULT_Y) );
        double velX = (mouse.x - Ball.DEFAULT_X) * Ball.initialVelocity / distance;
        double velY = (mouse.y - Ball.DEFAULT_Y) * Ball.initialVelocity / distance;
        return new Vector2(velX, velY);
    }

}