import bagel.Input;
import bagel.util.Point;

import java.util.List;

/**
 * Represents an Explosive entity dropped by an AirSupport entity.
 */
public class Explosive extends Sprite {
    private static final String IMAGE_FILE = "res/images/explosive.png";
    private static final int DAMAGE = 500;
    private static final int RADIUS = 200;
    private static final int DETONATE_TIME = 2;
    private int frameCount;
    private Level level;

    /**
     * Creates an instance of an explosive
     * @param pt Location of explosive
     * @param level Level object containing level data
     */
    public Explosive(Point pt, Level level) {
        super(pt, IMAGE_FILE);
        this.level = level;
        frameCount = 0;
    }

    /**
     * Deal damage to all enemies with bomb radius.
     */
    private void explode() {
        Wave wave = level.getWave();
        if (wave.getType().equals("Spawn")) {
            Spawn spawn = (Spawn) wave;
            List<Enemy> enemies = spawn.getEnemies();
            Point currentPoint = getCenter();
            for (Enemy enemy : enemies) {
                Point enemyPoint = enemy.getCenter();
                double distance = currentPoint.distanceTo(enemyPoint);
                if (distance <= RADIUS) {
                    enemy.takeDamage(DAMAGE);
                }
            }
        }
    }

    /**
     * Updates the state of the bomb and explodes after a fixed time.
     *
     * @param input Input object containing all of the user's input information.
     */
    @Override
    public void update(Input input) {
        if (hasFinished()) return;
        if (frameCount / ShadowDefend.FPS >= DETONATE_TIME) {
            end();
            explode();
            return;
        }
        frameCount += ShadowDefend.getTimescale();
        super.update(input);
    }
}
