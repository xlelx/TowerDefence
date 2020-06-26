import bagel.Input;
import bagel.util.Point;
import bagel.util.Vector2;
import bagel.Window;

import java.util.Random;

/**
 * Represents an Air Support entity.
 */
public class AirSupport extends MovableSprite {
    private static final double SPEED = 3;
    private static final String IMAGE_FILE = "res/images/airsupport.png";
    private static final int MAX_BOMB_CD = 2;
    private boolean vertical;
    private Point basePoint;
    private Point targetPoint;
    private Random rand = new Random();
    private double bombCooldown;
    private int frameCount = 0;
    private Level level;

    /**
     * Creates an instance of an Air Support passive tower.
     *
     * @param startingPoint A base point where the plane flies over.
     * @param vertical      boolean for whether the plane is to fly vertically or horizontally
     * @param level         A Level object containing information about the level.
     */
    public AirSupport(Point startingPoint, boolean vertical, Level level) {
        super(vertical ? new Point(startingPoint.x, -50) : new Point(-50, startingPoint.y), IMAGE_FILE);
        basePoint = startingPoint;
        this.vertical = vertical;
        if (vertical) targetPoint = new Point(startingPoint.x, Window.getHeight() + 50);
        else targetPoint = new Point(Window.getWidth(), startingPoint.y);
        setAngle(vertical ? Math.PI : Math.PI / 2);
        getNextBombCD();
        this.level = level;

    }

    private void getNextBombCD() {
        bombCooldown = 1 + rand.nextDouble() * (MAX_BOMB_CD - 1);
    }

    private void dropBomb() {
        level.addToRender(new Explosive(getCenter(), level));
    }

    /**
     * Updates and moves the plane position and drops a bomb randomly between 1 to 2 seconds.
     *
     * @param input Input object containing all of the user's input information.
     */
    @Override
    public void update(Input input) {
        if (hasFinished()) return;
        // Obtain where we currently are, and where we want to be
        if (frameCount / ShadowDefend.FPS >= bombCooldown) {
            dropBomb();
            frameCount = 0;
            getNextBombCD();
        }
        Point currentPoint = getCenter();
        // Convert them to vectors to perform some very basic vector math
        Vector2 targetVec = targetPoint.asVector();
        Vector2 current = currentPoint.asVector();
        Vector2 distance = targetVec.sub(current);
        // Distance we are (in pixels) away from our target point
        double magnitude = distance.length();
        // Check if we are close to the target point
        if (magnitude < SPEED * ShadowDefend.getTimescale()) {
            end();
            return;
        }
        // Move towards the target point
        // We do this by getting a unit vector in the direction of our target, and multiplying it
        // by the speed of the slicer (accounting for the timescale)
        super.move(distance.normalised().mul(SPEED * ShadowDefend.getTimescale()));
        frameCount += ShadowDefend.getTimescale();
        super.update(input);
    }
}
