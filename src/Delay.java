import bagel.Input;

/**
 * Represents a delay type wave
 */
public class Delay extends Wave {
    private double timer;
    private double delay;

    /**
     * Creates an instance of a delay Wave
     *
     * @param delay The amount of time in milliseconds delayed/
     */
    public Delay(int delay) {
        super();
        this.delay = delay / ShadowDefend.MILLISECONDS;
    }

    /**
     * Starts the timer of the delay wave.
     */
    public void start() {
        timer = ShadowDefend.frameCount;
    }

    /**
     * Updates the whether or not the delay has ended.
     *
     * @param input Input object containing all information about a user's input.
     */
    public void update(Input input) {
        if ((ShadowDefend.frameCount - timer) / ShadowDefend.FPS >= delay) {
            this.endWave();
        }
    }

}
