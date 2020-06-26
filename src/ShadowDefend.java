import bagel.*;

/**
 * ShadowDefend, a tower defence game, some parts from the Solution of project 1
 */
public class ShadowDefend extends AbstractGame {
    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;

    // Change to suit system specifications. This could be
    // dynamically determined but that is out of scope.
    public static final double FPS = 144;
    public static double frameCount;
    public static final double MILLISECONDS = 1000;
    private int levelNo = 1;
    private String waveFile = "waves";
    private static final int INITIAL_TIMESCALE = 1;
    // Timescale is made static because it is a universal property of the game and the specification
    // says everything in the game is affected by this
    public static int timescale = INITIAL_TIMESCALE;
    private Level level;
    private boolean finished = false;

    /**
     * Creates a new instance of the ShadowDefend game
     */
    public ShadowDefend() {
        super(WIDTH, HEIGHT, "ShadowDefend");
        nextLevel(levelNo, waveFile);
    }

    /**
     * Get the next level, which should be named an + 1 to the current level's number. Eg. 1.tmx would have 2.tmx as its next level, 3.tmx to 4.tmx
     * @param levelNo the levelNumber to be loaded
     * @param waveFile the waveFile associated with the level
     */
    private void nextLevel(int levelNo, String waveFile) {
        Level newlevel;
        try {
            newlevel = new Level("res/levels/" + levelNo + ".tmx", "res/levels/" + waveFile + ".txt");
            level = newlevel;
        } catch (Exception e) {
            finished = true;
            level.setWaveStatus(Level.WINNER);
        }
    }

    /**
     * The entry-point for the game
     *
     * @param args Optional command-line arguments
     */
    public static void main(String[] args) {
        new ShadowDefend().run();
    }

    public static int getTimescale() {
        return timescale;
    }

    /**
     * Increases the timescale
     */
    private void increaseTimescale() {
        timescale++;
    }

    /**
     * Decreases the timescale but doesn't go below the base timescale
     */
    private void decreaseTimescale() {
        if (timescale > INITIAL_TIMESCALE) {
            timescale--;
        }
    }

    /**
     * Update the state of the game, potentially reading from input
     *
     * @param input The current mouse/keyboard state
     */
    @Override
    protected void update(Input input) {
        // Increase the frame counter by the current timescale
        frameCount += getTimescale();
        // Draw map from the top left of the window
        // Handle key presses
        if (input.wasPressed(Keys.L)) {
            increaseTimescale();
        }

        if (input.wasPressed(Keys.K)) {
            decreaseTimescale();
        }
        level.update(input, finished);
        if (level.isFinished()) {
            levelNo += 1;
            nextLevel(levelNo, waveFile);
        }
    }
}
