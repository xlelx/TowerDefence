/**
 * A class that stores the represent's a Wave's properties
 */
public class WaveInfo {
    private int id;
    private String type;
    private int nEnemy;
    private String enemyType;
    private int spawnDelay;

    /**
     * Create a instance of WaveInfo by parsing a line of String from the wave file.
     *
     * @param line A single line from the wave file that represents a wave.
     */
    public WaveInfo(String line) {
        String[] info = line.split(",");
        id = Integer.parseInt(info[0]);
        type = info[1];
        if (type.equals("spawn")) {
            nEnemy = Integer.parseInt(info[2]);
            enemyType = info[3];
            spawnDelay = Integer.parseInt(info[4]);
        } else {
            spawnDelay = Integer.parseInt(info[2]);
        }
    }

    /**
     * Getter for waveID
     *
     * @return int value of the waveID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the number of enemies in this wave
     *
     * @return int value for the number of enemies
     */
    public int getNumEnemy() {
        return nEnemy;
    }

    /**
     * Gets the delay between enemies to be spawned
     *
     * @return time in milliseconds
     */
    public int getSpawnDelay() {
        return spawnDelay;
    }

    /**
     * Gets the wave type.
     *
     * @return A string specifying either spawn or delay wave type.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the type of enemies in this wave
     *
     * @return a String specifying the type of enemy
     */
    public String getEnemyType() {
        return enemyType;
    }
}
