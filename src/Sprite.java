import bagel.DrawOptions;
import bagel.Image;
import bagel.Input;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

/**
 * Represents a game entity, parts from the Solution of project 1
 */
public abstract class Sprite {

    private final Image image;
    private Rectangle rect;
    private double angle;
    private boolean finished = false;

    /**
     * Creates a new Sprite (game entity)
     *
     * @param point    The starting point for the entity
     * @param imageSrc The image which will be rendered at the entity's point
     */
    public Sprite(Point point, String imageSrc) {
        this.image = new Image(imageSrc);
        this.rect = image.getBoundingBoxAt(point);
        this.angle = 0;
    }

    /**
     * Get the bounding rectangle of the sprite
     * @return Rectangle object
     */
    public Rectangle getRect() {
        return rect;
    }


    /**
     * Get the center of the sprite
     * @return Point object
     */
    public Point getCenter() {
        return getRect().centre();
    }

    /**
     * Set the angle of the sprite
     * @param angle angle in radians
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Get the name of the class of the object.
     * @return String of the class name
     */
    public String getType() {
        return this.getClass().getName();
    }

    /**
     * Check whether the Sprite has finished eg. Reached the end of polyline, HP below Zero, Explosive has detonated etc.
     * @return boolean
     */
    public boolean hasFinished() {
        return finished;
    }

    /**
     * End the Sprite.
     */
    public void end() {
        finished = true;
    }

    /**
     * Updates the Sprite. Default behaviour is to render the Sprite at its current position.
     */
    public void update(Input input) {
        image.draw(getCenter().x, getCenter().y, new DrawOptions().setRotation(angle));
    }
}