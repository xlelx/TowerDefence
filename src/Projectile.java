import bagel.Input;
import bagel.util.Point;
import bagel.util.Vector2;

/**
 * Represents an abstract Projectile entity. It can be either a tank projectile or supertank projectile.
 */
public abstract class Projectile extends MovableSprite {
    private final double SPEED = 10;
    private int damage;
    private Enemy target;

    /**
     * @param image_file    String information about the path to the image file.
     * @param startingPoint Starting point of the projectile
     * @param damage        The damage the projectile deals if it hits its target.
     * @param target        The target of the projectile.
     */
    public Projectile(String image_file, Point startingPoint, int damage, Enemy target) {
        super(startingPoint, image_file);
        this.damage = damage;
        this.target = target;
    }

    /**
     * Updates and renders the position of the projectile object and deals damage to the enemy if the projectile has hit.
     *
     * @@param input Input object containing all of the user's input information.
     */
    @Override
    public void update(Input input) {
        if (hasFinished()) return;

        // Obtain where we currently are, and where we want to be
        Point currentPoint = getCenter();
        Point targetPoint = target.getCenter();
        // Convert them to vectors to perform some very basic vector math
        Vector2 targetVec = targetPoint.asVector();
        Vector2 current = currentPoint.asVector();
        Vector2 distance = targetVec.sub(current);
        // Distance we are (in pixels) away from our target point
        double magnitude = distance.length();
        // Check if we are close to the target point
        if (magnitude < SPEED * ShadowDefend.getTimescale()) {
            end();
            target.takeDamage(damage);
            return;
        }
        // Move towards the target point
        // We do this by getting a unit vector in the direction of our target, and multiplying it
        // by the speed of the slicer (accounting for the timescale)
        super.move(distance.normalised().mul(SPEED * ShadowDefend.getTimescale()));
        // Update current rotation angle to face target point
        setAngle(Math.atan2(targetPoint.y - currentPoint.y, targetPoint.x - currentPoint.x));
        super.update(input);
    }
}
