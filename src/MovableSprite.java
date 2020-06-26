import bagel.util.Point;
import bagel.util.Vector2;

/**
 * Represents an abstract entity of a sprite that can move.
 */
public abstract class MovableSprite extends Sprite {
    /**
     * Creates an instance of a sprite that can move
     * @param point Location of sprite
     * @param imageSrc Image file of sprite
     */
    public MovableSprite(Point point, String imageSrc) {
        super(point, imageSrc);
    }

    /**
     * Moves the Sprite by a specified delta
     *
     * @param dx The move delta vector
     */
    public void move(Vector2 dx) {
        getRect().moveTo(getRect().topLeft().asVector().add(dx).asPoint());
    }
}
