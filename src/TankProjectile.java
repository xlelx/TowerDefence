import bagel.util.Point;

/**
 * Represents a projectile entity of a tank.
 */
public class TankProjectile extends Projectile {
    private static final String IMAGE_FILE = "res/images/tank_projectile.png";
    private static final int DAMAGE = 1;

    /**
     * Creates an instance of a tank projectile
     *
     * @param startingPoint The starting point of the projectile
     * @param target        The target of the projectile
     */
    public TankProjectile(Point startingPoint, Enemy target) {
        super(IMAGE_FILE, startingPoint, DAMAGE, target);
    }
}
