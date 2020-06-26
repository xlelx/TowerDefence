import bagel.Input;
import bagel.util.Point;

import java.util.ArrayList;
import java.util.List;


/**
 * Spawn class that represents an array of enemies, parts from the Solution of project 1
 */
public class Spawn extends Wave {
    private final int nEnemy;
    // The spawn delay (in seconds) to spawn enemies
    private double spawn_delay;
    private final List<Enemy> enemies;
    private int spawnedEnemy;
    private List<Point> polyline;
    private String enemyType;
    private Level level;

    /**
     * A Spawn Entity that represents a group of enemies to be spawned in the wave.
     *
     * @param enemyType   Represents the type of enemy.
     * @param nEnemy      Number of Enemies in this wave.
     * @param spawn_delay The delay between each enemy is spawned.
     * @param polyline    The path of the enemies.
     * @param level       Contains level informaton.
     */
    public Spawn(String enemyType, int nEnemy, int spawn_delay, List<Point> polyline, Level level) {
        super();
        this.enemies = new ArrayList<>();
        this.spawnedEnemy = 0;
        this.polyline = polyline;
        this.spawn_delay = spawn_delay / ShadowDefend.MILLISECONDS;
        this.nEnemy = nEnemy;
        this.enemyType = enemyType;
        this.level = level;
    }

    /**
     * Checks for whether a new enemy should be spawned and updates the position of all existing enemies in the wave.
     *
     * @param input Input object containing all of the user's input information.
     */
    public void update(Input input) {
        // Check if it is time to spawn a new slicer (and we have some left to spawn)
        if (this.hasWaveStarted() && ShadowDefend.frameCount / ShadowDefend.FPS >= spawn_delay && spawnedEnemy != nEnemy) {
            enemies.add(SlicerGenerator.spawnSlicer(enemyType, polyline, polyline.get(0), 1, level));
            spawnedEnemy += 1;
            // Reset frame counter
            ShadowDefend.frameCount = 0;
        }
        // Update all sprites, and remove them if they've finished
        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy s = enemies.get(i);
            s.update(input);
            if (s.hasFinished()) {
                enemies.remove(i);
            }
        }
        if (spawnedEnemy == nEnemy && enemies.isEmpty()) {
            this.endWave();
        }
    }

    /**
     * Get a list of enemies that are currently active in this spawn wave.
     * @return List of enemies
     */
    public List<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Add an Enemy to the spawn wave.
     * @param enemy Enemy object
     */
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    /**
     * Start the current wave.
     */
    public void start() {
        this.startWave();
    }
}
