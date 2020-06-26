import bagel.util.Point;

import java.util.List;

/**
 * A class used to spawn generate slicer given a string type.
 */
public class SlicerGenerator {
    /**
     * Generates an instance of an enemy according to the properties.
     *
     * @param enemyType        String of enemyType
     * @param polyline         List of points which the enemies are to follow
     * @param startingPoint    The starting point of enemy
     * @param targetPointIndex The index of a point in the polyline
     * @param level            A Level object that has information about the level.
     * @return
     */
    public static Enemy spawnSlicer(String enemyType, List<Point> polyline, Point startingPoint, int targetPointIndex, Level level) {
        if (enemyType.equals("slicer")) {
            return new Slicer(polyline, startingPoint, targetPointIndex, level);
        }
        if ((enemyType.equals("superslicer"))) {
            return new SuperSlicer(polyline, startingPoint, targetPointIndex, level);
        }
        if ((enemyType.equals("megaslicer"))) {
            return new MegaSlicer(polyline, startingPoint, targetPointIndex, level);
        } else {
            return new ApexSlicer(polyline, startingPoint, targetPointIndex, level);
        }
    }
}
