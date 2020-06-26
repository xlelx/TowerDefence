import bagel.*;
import bagel.util.Colour;
import bagel.util.Point;
import bagel.util.Rectangle;

/**
 * Represents an status panel entity.
 */
public class StatusPanel extends Panel {
    private Image panel;
    private final double wordY;
    private final int greenTSval = 2;
    private final String FONT_FILE = "res/fonts/DejaVuSans-Bold.ttf";
    private final String PANEL_FILE = "res/images/statuspanel.png";

    private DrawOptions drawOptions;

    private Font wordFont = new Font(FONT_FILE, 15);

    /**
     * Creates an instance of a status panel
     *
     * @param level a Level object containing information about the level.
     */
    public StatusPanel(Level level) {
        super(level);
        panel = new Image(PANEL_FILE);
        wordY = Window.getHeight() - panel.getHeight() / 2 + 5;
        drawOptions = new DrawOptions();
    }

    /**
     * Changes the color of the timescale if its more than 1.
     */
    private void setTimeScaleColor() {
        if (ShadowDefend.getTimescale() >= greenTSval) drawOptions.setBlendColour(Colour.GREEN);
        else drawOptions.setBlendColour(Colour.WHITE);
    }

    /**
     * Updates the panel information to be shown.
     *
     * @param input an Input object containing all of the user's inputs.
     */
    @Override
    public void update(Input input) {
        panel.draw(Window.getWidth() / 2, Window.getHeight() - panel.getHeight() / 2);
        wordFont.drawString(String.format("Wave: %d", getLevel().getWaveID()), 5, wordY);
        setTimeScaleColor();
        wordFont.drawString(String.format("Time Scale: %d.0", ShadowDefend.getTimescale()), 250, wordY, drawOptions);
        wordFont.drawString(String.format("Status: %s", getLevel().getWaveStatus()), 500, wordY);
        wordFont.drawString(String.format("Lives: %d", getLevel().getLives()), Window.getWidth() - 150, wordY);
    }

    /**
     * Gets the Rectangle associated with the panel
     *
     * @return Rectangle object for the panel
     */
    @Override
    public Rectangle getBoundingBox() {
        return panel.getBoundingBoxAt(new Point(Window.getWidth() / 2, Window.getHeight() - panel.getHeight() / 2));
    }
}
