import bagel.Input;
import bagel.util.Rectangle;

/**
 * Represents an abstract panel entity. It can either be a status or buy panel.
 */
public abstract class Panel {
    private Level level;

    /**
     * Constructor for a panel abstract class
     *
     * @param level a Level object containing information about the level.
     */
    public Panel(Level level) {
        this.level = level;
    }

    /**
     * Getter function to get the level object.
     *
     * @return a Level object that contains information about the level.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Updates the panel information to be shown.
     *
     * @param input an Input object containing all of the user's inputs.
     */
    public abstract void update(Input input);

    /**
     * Gets the Rectangle associated with the panel
     *
     * @return Rectangle object for the panel
     */
    public abstract Rectangle getBoundingBox();
}