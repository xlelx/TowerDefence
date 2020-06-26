import bagel.util.Point;

import java.util.List;

/**
 * Represents a super slicer type of enemy
 */
public class SuperSlicer extends Enemy {
    private static final String IMAGE_FILE = "res/images/superslicer.png";
    private static final int nChildren = 2;
    public static final int HEALTH = Slicer.HEALTH;
    public static final int PENALTY = nChildren * Slicer.PENALTY;
    private static final int REWARD = 15;
    public static final double SPEED = 0.75 * Slicer.SPEED;
    private static final String childType = "slicer";

    /**
     * Creates an instance of a super slicer
     *
     * @param polyline         a list of points that the enemies follow
     * @param startingPoint    The starting spawn point of the enemies
     * @param targetPointIndex The index of the polyline specifying the target point for the slicer to travel towards
     * @param level            A Level object containing information about the level.
     */
    public SuperSlicer(List<Point> polyline, Point startingPoint, int targetPointIndex, Level level) {
        super(polyline, startingPoint, targetPointIndex, IMAGE_FILE, HEALTH, PENALTY, REWARD, SPEED, childType, nChildren, level);
    }

}
