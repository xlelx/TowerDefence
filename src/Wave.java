import bagel.Input;

/**
 * Represents an abstract Entity of a wave. It can either be a Spawn or Delay type wave.
 */
public abstract class Wave {
    private boolean waveEnded = false;
    private boolean waveStarted = false;

    public abstract void start();

    /**
     * Updates the state of the wave.
     *
     * @param input Input object containing all information about a user's input.
     */
    public abstract void update(Input input);

    /**
     * End the current wave
     */
    public void endWave() {
        waveEnded = true;
    }

    /**
     * Start the current wave
     */
    public void startWave() {
        waveStarted = true;
    }

    /**
     * Check if the wave has started
     *
     * @return boolean of whether the wave has started
     */
    public boolean hasWaveStarted() {
        return waveStarted;
    }

    /**
     * Check if the wave has ended. This is not equivalent to a wave that has not started.
     * Eg. A wave that hasn't started also has not finished.
     *
     * @return a boolean of whether the wave has ended.
     */
    public boolean hasWaveEnded() {
        return waveEnded;
    }

    /**
     * Get the specific type of wave.
     *
     * @return
     */
    public String getType() {
        return this.getClass().getName();
    }
}
