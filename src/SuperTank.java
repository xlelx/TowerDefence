import bagel.util.Point;

/**
 * Represents a Super Tank type of tower.
 */
public class SuperTank extends Tower {
    private static final String IMAGE_FILE = "res/images/supertank.png";
    private static final int PROJ_COOLDOWN = 500;
    private static final int ATTACK_RANGE = 150;
    private static final int PRICE = 600;

    /**
     * Creates an instance of a super tank
     *
     * @param location The location of the tower
     * @param level    A Level object containing information about the level.
     */
    public SuperTank(Point location, Level level) {
        super(location, IMAGE_FILE, PROJ_COOLDOWN, ATTACK_RANGE, PRICE, level);
    }

    /**
     * Creates a super tank projectile.
     *
     * @param target An Enemy that the projectile is travelling towards.
     * @return a SuperTankProjectile
     */
    @Override
    public Projectile createProjectile(Enemy target) {
        return new SuperTankProjectile(getCenter(), target);
    }
}
