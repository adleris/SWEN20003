import bagel.*;
import bagel.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Board {
    private static final int INITIAL_SHOTS = 20;
    private int numShots;

    private static final String FILE_PATTERN = "Boards/#.csv";

    private ArrayList<Peg> pegs;
    private Bucket bucket;
    private PowerUp powerup;
    private ArrayList<Ball> balls;

    /** constructor for the Board
     * @param boardNumber -- The board number to read from the CSV file
     */
    public Board(int boardNumber){
        // set up the peg array
        pegs = new ArrayList<>();
        readInBoard(boardNumber);
        balls = new ArrayList<>();
        powerup = new PowerUp();
        bucket = new Bucket();
        numShots = INITIAL_SHOTS;
    }

    /** Update the components in Board -- The movement of the bucket and power up (if it exists)
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
            if (numShots-- <= 0) Window.close();

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
            if (! ball.isOnScreen()) {
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
//        // the insides of this can be tidied up
//        for (Ball ball : balls) {
//            if (ball.isFireBall()) {
//                // need to explode on intersect
//                for (Peg peg : pegs) {
//                    if (ball.getRectangle().intersects(peg.getRectangle())) {
//                        if (! peg.getColour() == "grey") {
//                            DestroyPegsInRadius(ball);
//                        }
//                        if (peg.getColour() == "Green"){
//                            this.Balls = GreenPegSummonBall(ball);      // this aint right but that's ok for now
//                            // if we hit a green peg there should have only been one ball
//                        }
//                    }
//                }
//            } else {
//                for (Peg peg : pegs) {
//                    // normal intersect handling
//                    if (! peg.getColour() == "grey") {
//                        peg.destroy();
//                    }
//                    if (peg.getColour() == "Green"){
//                        this.Balls = GreenPegSummonBall(ball);      // this aint right but that's ok for now
//                        // if we hit a green peg there should have only been one ball
//                    }
//                }
//            }
//        }
//
//    //     here, we will also check the intersections of the bucket and powerUp
    }

    /** Render all sprites to the screen */
    public void renderScreen(ArrayList<Ball> balls) {

        // pegs
        for (Peg peg : pegs) {
            if (! peg.isDestroyed()){
                peg.draw();
            }
        }

        // balls
        for (Ball ball : balls) {
            if (ball.isOnScreen()){
                ball.draw();
            }
        }

        // bucket
        bucket.draw();

        // power up
        if (powerup != null) {
            // temporary: draw red peg at position
            Image pu_img = new Image("res/red-peg.png");

            pu_img.draw(powerup.destination.x, powerup.destination.y);
            powerup.draw();
        }
    }

    /** Determines if the current board should be finished (All red pegs destroyed)
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

    private Peg newPegFromName(String type, double x, double y){
        if (type.contains("blue")) {
            return new BluePeg(type, x, y);
        }
        else if (type.contains("grey")) {
            return new GreyPeg(type, x, y);
        }
        return null;        // This will only happen in an error
    }


    public void DestroyPegsInRadius(Ball ball) {
        // make a box / circle (using euclidean distance) and then check intersections
        // with every peg using the intersect method
    }

    /** go through our list of pegs and return a random one of them */
    public BluePeg getRandomBlue() {
        /* choose a Blue Peg: not technically random but we can sort that out later if we want */
        for (Peg peg : pegs){
            if (peg instanceof BluePeg) {
                /* we want to cast this peg to BluePeg and return a pointer to it, because that's what's on the Board */
                return (BluePeg)peg;
            }
        }
        return null;
    }
}







