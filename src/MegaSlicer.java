import bagel.util.Point;

import java.util.List;

/**
 * Represents a mega slicer type of enemy
 */
public class MegaSlicer extends Enemy {
    private static final String IMAGE_FILE = "res/images/megaslicer.png";
    private static final int nChildren = 2;
    public static final int HEALTH = 2 * SuperSlicer.HEALTH;
    public static final int PENALTY = nChildren * SuperSlicer.PENALTY;
    private static final int REWARD = 10;
    public static final double SPEED = SuperSlicer.SPEED;
    private static final String childType = "superslicer";


    /**
     * Creates an instance of a mega slicer
     *
     * @param polyline         a list of points that the enemies follow
     * @param startingPoint    The starting spawn point of the enemies
     * @param targetPointIndex The index of the polyline specifying the target point for the slicer to travel towards
     * @param level            A Level object containing information about the level.
     */
    public MegaSlicer(List<Point> polyline, Point startingPoint, int targetPointIndex, Level level) {
        super(polyline, startingPoint, targetPointIndex, IMAGE_FILE, HEALTH, PENALTY, REWARD, SPEED, childType, nChildren, level);
    }
}
