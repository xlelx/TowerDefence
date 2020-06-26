import bagel.Input;
import bagel.util.Point;

import java.util.List;

/**
 * Represents an abstract Tower Entity. Can either be a tank or super tank.
 */
public abstract class Tower extends Sprite {
    private int projCooldown;
    private int attackRange;
    private int price;
    private Level level;
    private int cooldown;

    /**
     * Creates an instance of a tower that attacks the closest enemy in range.
     *
     * @param point        location of the tower
     * @param imageSrc     path to image file of the tower
     * @param projCooldown The amount of time between every attack.
     * @param attackRange  Only enemies within the range can be attacked.
     * @param price        The cost of building a tower.
     * @param level        A Level object containing information about the level.
     */
    public Tower(Point point, String imageSrc, int projCooldown, int attackRange, int price, Level level) {
        super(point, imageSrc);
        this.projCooldown = projCooldown;
        this.attackRange = attackRange;
        this.price = price;
        this.level = level;
        cooldown = projCooldown;
    }

    /**
     * Creates an instance of a projectile specific to the tower type.
     *
     * @param target An Enemy that the projectile is travelling towards.
     * @return Projectile object with a target.
     */
    public abstract Projectile createProjectile(Enemy target);

    /**
     * Create a new projectile object which is added to the level.
     * @param target The target of the projectile.
     */
    private void attack(Enemy target) {
        Projectile proj = createProjectile(target);
        level.addToRender(proj);
    }

    /**
     * Retrieves the closest Enemy to the Tower from Level.
     * @return Enemy object
     */
    private Enemy getClosestEnemy() {
        Wave wave = level.getWave();
        if (wave.getType().equals("Spawn")) {
            Spawn spawn = (Spawn) wave;
            List<Enemy> enemies = spawn.getEnemies();
            Enemy closest = null;
            double closestDistance = Double.MAX_VALUE;
            Point currentPoint = getCenter();
            for (Enemy enemy : enemies) {
                Point enemyPoint = enemy.getCenter();
                double distance = currentPoint.distanceTo(enemyPoint);
                if (distance <= attackRange && (closest == null || closestDistance < distance)) {
                    closest = enemy;
                    closestDistance = distance;
                }
            }
            return closest;
        }
        return null;
    }

    /**
     * Updates the direction of the tower to be facing its closest enemy and renders it. Shoots a projectile if not
     * in cooldown.
     *
     * @param input Input object containing all of the player's input.
     */
    public void update(Input input) {
        Point currentPoint = getCenter();
        Enemy target = getClosestEnemy();
        if (cooldown / ShadowDefend.FPS >= projCooldown / ShadowDefend.MILLISECONDS && target != null) {
            Point targetPoint = target.getCenter();
            attack(target);
            cooldown = 0;
            setAngle(Math.atan2(targetPoint.y - currentPoint.y, targetPoint.x - currentPoint.x) + Math.PI / 2);
        }
        // Update current rotation angle to face target point

        cooldown += ShadowDefend.getTimescale();
        super.update(input);
    }
}
