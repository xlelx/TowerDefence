import bagel.util.Point;

/**
 * Class used to generates instances of tower or air support.
 */
public class TowerGenerator {
    private static boolean vertical = true;

    /**
     * Generates an instance of an tower or airsupport according to the properties.
     *
     * @param startingPoint The location of the tower or airsupprt.
     * @param towerType     The type of tower.
     * @param level         A Level object containing information about the level.
     * @return A Sprite instance of the tower or airsupport.
     */
    public static Sprite spawnTower(Point startingPoint, String towerType, Level level) {
        if (towerType.equals("tank")) {
            return new Tank(startingPoint, level);
        }
        if (towerType.equals("supertank")) {
            return new SuperTank(startingPoint, level);
        }
        if (towerType.equals("airsupport")) {
            vertical = !vertical;
            return new AirSupport(startingPoint, vertical, level);
        }
        return null;
    }

    /**
     * Gets the price of a specific type of tower.
     *
     * @param towerType A String specifying the type of tower.
     * @return int value of the price
     */
    public static int getPrice(String towerType) {
        if (towerType.equals("tank")) {
            return 250;
        }
        if (towerType.equals("supertank")) {
            return 600;
        }
        if (towerType.equals("airsupport")) {
            return 500;
        }
        return Integer.MAX_VALUE;
    }
}
