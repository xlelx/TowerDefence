import bagel.util.Point;

import java.util.List;

/**
 * Represents a apex slicer type of enemy
 */
public class ApexSlicer extends Enemy {
    private static final String IMAGE_FILE = "res/images/apexslicer.png";
    private static final int nChildren = 2;
    public static final int HEALTH = 25 * Slicer.HEALTH;
    public static final int PENALTY = nChildren * MegaSlicer.PENALTY;
    private static final int REWARD = 150;
    public static final double SPEED = 0.5 * MegaSlicer.SPEED;
    private static final String childType = "megaslicer";

    /**
     * Creates an instance of a apex slicer
     *
     * @param polyline         a list of points that the enemies follow
     * @param startingPoint    The starting spawn point of the enemies
     * @param targetPointIndex The index of the polyline specifying the target point for the slicer to travel towards
     * @param level            A Level object containing information about the level.
     */
    public ApexSlicer(List<Point> polyline, Point startingPoint, int targetPointIndex, Level level) {
        super(polyline, startingPoint, targetPointIndex, IMAGE_FILE, HEALTH, PENALTY, REWARD, SPEED, childType, nChildren, level);
    }
}
