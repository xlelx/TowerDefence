import bagel.util.Point;

import java.util.List;

/**
 * Represents a Slicer type of enemy
 */
public class Slicer extends Enemy {
    private static final String IMAGE_FILE = "res/images/slicer.png";
    public static final int HEALTH = 1;
    public static final int PENALTY = 1;
    private static final int REWARD = 2;
    public static final double SPEED = 2;
    private static final String childType = "";
    private static final int nChildren = 0;

    /**
     * Creates an instance of a Slicer
     *
     * @param polyline         a list of points that the enemies follow
     * @param startingPoint    The starting spawn point of the enemies
     * @param targetPointIndex The index of the polyline specifying the target point for the slicer to travel towards
     * @param level            A Level object containing information about the level.
     */
    public Slicer(List<Point> polyline, Point startingPoint, int targetPointIndex, Level level) {
        super(polyline, startingPoint, targetPointIndex, IMAGE_FILE, HEALTH, PENALTY, REWARD, SPEED, childType, nChildren, level);
    }
}
