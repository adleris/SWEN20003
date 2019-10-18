import bagel.*;
import bagel.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Board {
    private static final int INITIAL_SHOTS = 4;
    private int shotsRemaining;

    private static final String FILE_PATTERN = "Boards/#.csv";

    private ArrayList<Peg> pegs;
    private Bucket bucket;
    private PowerUp powerup;
    private ArrayList<Ball> balls;

    /**
     * constructor for the Board
     *
     * @param boardNumber -- The board number to read from the CSV file
     */
    public Board(int boardNumber) {
        // set up the peg array
        pegs = new ArrayList<>();
        readInBoard(boardNumber);
        turnPegsRed(RedPeg.PROPORTION_TO_RED);

        balls = new ArrayList<>();
        powerup = new PowerUp();
        bucket = new Bucket();
        shotsRemaining = INITIAL_SHOTS;
    }

    /**
     * Update the components in Board -- The movement of the bucket and power up (if it exists)
     *
     * @param velocityFromMouse -- The vector velocity from the ball origin to the mouse. If this is null, it
     *                          indicates that the mouse wasn't clicked
     */
    public void update(Vector2 velocityFromMouse) {

        ArrayList<Ball> ballsToRemove = new ArrayList<>();

        /* If there are no balls in the array list, see if we should make a new one.
         * if a left click is made, try to make a new ball and add it to the arraylist
         */
        if (balls.size() == 0 && velocityFromMouse != null) {
            /* first subtract 1 from our remaining shots. If we have no more shots, end the game */
            //if (shotsRemaining-- <= 0) Window.close();
            shotsRemaining--;
            System.out.println(shotsRemaining);
            if (shotsRemaining < 0) Window.close();
            /* make the new ball */
            Ball newBall = new Ball(velocityFromMouse);
            newBall.setOnScreen(true);
            balls.add(newBall);
        }

        /* run through all active balls and move them, this does nothing if array list is empty (no balls) */
        for (Ball ball : balls) {
            /*
             * there are some balls on the screen: adjust their acceleration according to
             * gravity, then move them
             */
            ball.setVelocity(ball.getVelocity().add(new Vector2(0, Ball.gravityAcceleration)));
            ball.moveBy(ball.getVelocity());

            /* if this move sends the ball off of the screen, schedule ball to be removed from the ball array */
            if (!ball.isOnScreen()) {
                ballsToRemove.add(ball);
            }
        }

        /* remove any balls that left the screen */
        for (Ball ball : ballsToRemove) {
            balls.remove(ball);
        }

        /* let the moving things do their thing */
        if (powerup != null) powerup.moveBy(powerup.getVelocity());
        bucket.moveBy(bucket.getVelocity());

        /* check if the ball hits anything */
        checkCollisions();

        /* render everything */
        renderScreen(balls);
    }


    public void checkCollisions() {

        /* check the collisions of every ball with all possible objects */
        for (int i = 0; i < balls.size(); i++) {
            for (Peg peg : pegs) {
                if (balls.get(i).getRectangle().intersects(peg.getRectangle()) && !peg.isDestroyed()) {
                    /* in both of these collision scenarios, pegs will mark themselves as destroyed */
                    //peg.collideWith(ball);
                    if (balls.get(i).isFireBall()) {
                        destroyPegsInRadius(balls.get(i));
                    }
                }
            }

            /* check if the ball intersects with the bucket, if so, add a shot
             * The flag is needed as otherwise the ball will hit the bucket multiple times as it passes through
             */
            if (!balls.get(i).getHasHitBucket() && balls.get(i).getRectangle().intersects(bucket.getRectangle())) {
                balls.get(i).setHasHitBucket(true);
                shotsRemaining++;
                System.out.format("You hit the bucket! Shots remaining increased to %d\n", shotsRemaining);
            }

            /* check if the ball intersects with the power up */
            if (powerup != null && balls.get(i).getRectangle().intersects(powerup.getRectangle())) {
                /* delete the power up and make a fire ball */
                powerup = null;
                balls.set(i, new Ball(balls.get(i), true));
            }
        }


        /* collect all of the destroyed pegs and remove them from the array list */
        ArrayList<Peg> pegsToRemove = new ArrayList<>();
        for (Peg peg : pegs) {
            if (peg.isDestroyed()) {
                pegsToRemove.add(peg);
            }
        }
        for (Peg destroyedPeg : pegsToRemove) {
            pegs.remove(destroyedPeg);
        }
    }

    /**
     * Render all sprites to the screen
     */
    public void renderScreen(ArrayList<Ball> balls) {

        // pegs
        for (Peg peg : pegs) {
            if (!peg.isDestroyed()) {
                peg.draw();
            }
        }

        // balls
        for (Ball ball : balls) {
            if (ball.isOnScreen()) {
                ball.draw();
            }
        }

        // bucket
        bucket.draw();

        // power up
        if (powerup != null) {
            // temporary: draw red peg at position
            //Image pu_img = new Image("res/red-peg.png");

            //pu_img.draw(powerup.destination.x, powerup.destination.y);
            powerup.draw();
        }
    }

    /**
     * Determines if the current board should be finished (All red pegs destroyed)
     *
     * @return if the game should be ended
     */
    public boolean shouldEndBoard() {
        return RedPeg.shouldEndGame();
    }

    public void readInBoard(int boardNumber) {
        String filename = FILE_PATTERN.replace("#", Integer.toString(boardNumber));


        // open up the file, iterate through it's lines and save them into our Peg array
        // set up the pegs array inside here as well with "new"

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String row;
            String[] columns;

            while ((row = br.readLine()) != null) {
                columns = row.split(",");
                // make sure we have a name, x, y
                if (columns.length != 3) {
                    throw new Exception();
                }
                // add a new peg with type based on its name, x, and y
                pegs.add(newPegFromName(columns[0], Double.parseDouble(columns[1]), Double.parseDouble(columns[2])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Peg newPegFromName(String type, double x, double y) {
        if (type.contains("blue")) {
            return new BluePeg(type, x, y);
        } else if (type.contains("grey")) {
            return new GreyPeg(type, x, y);
        }
        return null;        // This will only happen in an error
    }


    public void destroyPegsInRadius(Ball ball) {
        // make a box / circle (using euclidean distance) and then check intersections
        // with every peg using the intersect method
    }


    /** Turn 1 / proportion of the blue pegs to be red
     * @param proportion The Proportion of pegs to turn red
     */
    private void turnPegsRed(int proportion) {
        int index;
        // the number of pegs we have to convert:
        int numPegsToChange = pegs.size() / proportion;

        while (numPegsToChange > 0) {
            // pick a random index, and see if that's a blue peg
            index = (int) Peg.randomInRange(0, (double) pegs.size());

            if (pegs.get(index) instanceof BluePeg) {
                // if so, convert that peg to a red peg
                BluePeg bp = (BluePeg) pegs.get(index);
                pegs.set(index, bp.transformRed());
                numPegsToChange--;
            }
        }
    }
}

