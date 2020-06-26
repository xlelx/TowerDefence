import bagel.util.Point;

/**
 * Represents a projectile entity of a super tank.
 */
public class SuperTankProjectile extends Projectile {
    private static final String IMAGE_FILE = "res/images/supertank_projectile.png";
    private static final int DAMAGE = 3;

    /**
     * Creates an instance of a super tank projectile
     *
     * @param startingPoint The starting point of the projectile
     * @param target        The target of the projectile
     */
    public SuperTankProjectile(Point startingPoint, Enemy target) {
        super(IMAGE_FILE, startingPoint, DAMAGE, target);
    }
}
