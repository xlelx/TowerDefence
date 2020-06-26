import bagel.util.Point;

/**
 * Represents a Tank type of tower.
 */
public class Tank extends Tower {
    private static final String IMAGE_FILE = "res/images/tank.png";
    private static final int PROJ_COOLDOWN = 1000;
    private static final int ATTACK_RANGE = 100;
    private static final int PRICE = 250;

    /**
     * Creates an instance of a tank
     *
     * @param location The location of the tower
     * @param level    A Level object containing information about the level.
     */
    public Tank(Point location, Level level) {
        super(location, IMAGE_FILE, PROJ_COOLDOWN, ATTACK_RANGE, PRICE, level);
    }

    /**
     * Creates a tank projectile.
     *
     * @param target An Enemy that the projectile is travelling towards.
     * @return a TankProjectile
     */
    @Override
    public Projectile createProjectile(Enemy target) {
        return new TankProjectile(getCenter(), target);
    }
}
