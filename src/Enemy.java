import bagel.Input;
import bagel.util.Point;
import bagel.util.Vector2;

import java.util.List;

/**
 * A regular slicer, parts from solution of Project 1
 */
public abstract class Enemy extends MovableSprite {

    private final List<Point> polyline;
    private int targetPointIndex;
    private int healthPoint;
    private int penalty;
    private int reward;
    private double speed;
    private Level level;
    private String childType;
    private int nChildren;

    /**
     * Creates a new Enemy
     *
     * @param polyline The polyline that the slicer must traverse (must have at least 1 point)
     */
    public Enemy(List<Point> polyline, Point startingPoint, int targetPointIndex, String image_file, int healthPoint, int penalty, int reward,
                 double speed, String childType, int nChildren, Level level) {
        super(startingPoint, image_file);
        this.polyline = polyline;
        this.targetPointIndex = targetPointIndex;
        this.healthPoint = healthPoint;
        this.penalty = penalty;
        this.reward = reward;
        this.speed = speed;
        this.childType = childType;
        this.nChildren = nChildren;
        this.level = level;
    }

    /**
     * Updates the current state of the slicer. The slicer moves towards its next target point in
     * the polyline at its specified movement rate.
     */
    @Override
    public void update(Input input) {
        if (hasFinished()) return;
        if (healthPoint <= 0) {
            end();
            onDeath();
            return;
        }
        // Obtain where we currently are, and where we want to be
        Point currentPoint = getCenter();
        Point targetPoint = polyline.get(targetPointIndex);
        // Convert them to vectors to perform some very basic vector math
        Vector2 target = targetPoint.asVector();
        Vector2 current = currentPoint.asVector();
        Vector2 distance = target.sub(current);
        // Distance we are (in pixels) away from our target point
        double magnitude = distance.length();
        // Check if we are close to the target point
        if (magnitude < speed * ShadowDefend.getTimescale()) {
            // Check if we have reached the end
            if (targetPointIndex == polyline.size() - 1) {
                end();
                level.loseLives(penalty);
                return;
            } else {
                // Make our focus the next point in the polyline
                targetPointIndex += 1;
            }
        }
        // Move towards the target point
        // We do this by getting a unit vector in the direction of our target, and multiplying it
        // by the speed of the slicer (accounting for the timescale)
        super.move(distance.normalised().mul(speed * ShadowDefend.getTimescale()));
        // Update current rotation angle to face target point
        setAngle(Math.atan2(targetPoint.y - currentPoint.y, targetPoint.x - currentPoint.x));
        super.update(input);
    }

    /**
     * Executes actions that trigger if Enemy is killed by towers. This includes rewarding the player,
     * and spawning children enemies.
     */
    public void onDeath() {
        level.addMoney(reward);
        Spawn spawn = (Spawn) level.getWave();
        for (int i = 0; i < nChildren; i++) {
            spawn.addEnemy(SlicerGenerator.spawnSlicer(childType, polyline, this.getCenter(), this.getTargetPointIndex(), level));
        }
    }

    /**
     * Reduce the health of the enemy by some amount.
     *
     * @param damage The amount of health lost by the slicer
     */
    public void takeDamage(int damage) {
        healthPoint -= damage;
    }

    /**
     * Get the slicer's current polyline target.
     *
     * @return index of the polyline
     */
    public int getTargetPointIndex() {
        return targetPointIndex;
    }

    @Override
    public String getType() {
        return "Enemy";
    }
}
