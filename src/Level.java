import bagel.*;
import bagel.map.TiledMap;
import bagel.util.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Level represents a single level specified by a map_file and waves.txt. Some part of the code can be credited to the
 * solution of Project 1.
 */
public class Level {
    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;

    /**
     * Represents game state where level has yet to start.
     */
    public static final String AWAIT_START = "Awaiting Start";

    /**
     * Represents game state where level is in progress.
     */
    public static final String WAVE_IN_PROGRESS = "Wave In Progress";

    /**
     * Represents game state where the player is placing a tower.
     */
    public static final String PLACING = "Placing";

    /**
     * Represents game state where game has end and the player has won.
     */
    public static final String WINNER = "Winner!";

    private int money;
    private int lives;

    private final TiledMap map;
    private final List<Point> polyline;
    private Wave wave;
    private WaveParser waveReader;
    private WaveInfo currentWave;
    private ArrayList<Sprite> toRender = new ArrayList<>();

    private int waveID;
    private String waveStatus;

    private final BuyPanel buyPanel;
    private final StatusPanel statusPanel;

    private String buyingType = "";
    private Image buyImage;
    private boolean finished = false;

    /**
     * Constructor for a Level object.
     *
     * @param mapFile  The path to the map file
     * @param waveFile The path to the wave file that specifies the wave information.
     */
    public Level(String mapFile, String waveFile) {
        this.map = new TiledMap(mapFile);
        this.polyline = map.getAllPolylines().get(0);
        waveReader = new WaveParser(waveFile);
        buyPanel = new BuyPanel(this);
        statusPanel = new StatusPanel(this);
        this.money = 500;
        waveReader = new WaveParser(waveFile);
        lives = 25;
        waveStatus = AWAIT_START;
        waveID = 0;
        updateWaveInfo();
    }

    /**
     * Handles the tower buying logic.
     *
     * @param input Input object containing information about the user's inputs.
     */
    private void buyUpdate(Input input) {
        //Buying action
        boolean canPlace = true;
        Point p = input.getMousePosition();
        if (getWaveStatus().equals(PLACING) && (p.x >= 0 && p.x <= Window.getWidth()) && (p.y >= 0 && p.y <= Window.getHeight())){
            if (!buyPanel.getBoundingBox().intersects(p) && !statusPanel.getBoundingBox().intersects(p) &&
                    !map.getPropertyBoolean((int) p.x, (int) p.y, "blocked", false)) {
                for (Sprite s : toRender) {
                    if (s.getRect().intersects(p)) {
                        canPlace = false;
                        break;
                    }
                }
            } else {
                canPlace = false;
            }
        }

        if (getWaveStatus().equals(PLACING)) {
            if (input.wasPressed(MouseButtons.LEFT)) {
                if (canPlace) {
                    int tPrice = TowerGenerator.getPrice(buyingType);
                    if (getMoney() >= tPrice) {
                        toRender.add(TowerGenerator.spawnTower(p, buyingType, this));
                        money -= tPrice;
                        if (wave.hasWaveStarted()) setWaveStatus(WAVE_IN_PROGRESS);
                        else setWaveStatus(AWAIT_START);
                    }
                }
            }
        }
        if (getWaveStatus().equals(PLACING) && canPlace) {
            buyImage = new Image("res/images/" + buyingType + ".png");
            buyImage.draw(input.getMouseX(), input.getMouseY());
        }
        ArrayList<String> buying = buyPanel.buyAction(input);
        if (buying.size() != 0) {
            if (buying.get(0).equals("refresh")) {
                if (wave.hasWaveStarted()) setWaveStatus(WAVE_IN_PROGRESS);
                else setWaveStatus(AWAIT_START);
            } else {
                setWaveStatus(PLACING);
                buyingType = buying.get(1);
            }
        }
    }

    /**
     * Updates the next wave's information.
     */
    public void updateWaveInfo() {
        currentWave = waveReader.getNextWave();
        if (currentWave == null) {
            finished = true;
            return;
        }
        if (currentWave.getType().equals("spawn")) {
            if (waveID != 0) {
                addMoney(150 + 100 * getWaveID());
            }
            wave = new Spawn(currentWave.getEnemyType(), currentWave.getNumEnemy(), currentWave.getSpawnDelay(), polyline, this);
        } else {
            wave = new Delay(currentWave.getSpawnDelay());
        }
        waveID = currentWave.getId();
    }

    /**
     * Updates the next wave's information.
     *
     * @param input  Input information from the user/player
     * @param winner Boolean value stating whether or not the level has ended.
     */
    public void update(Input input, boolean winner) {
        map.draw(0, 0, 0, 0, WIDTH, HEIGHT);
        if (!winner) {
            if (input.wasPressed(Keys.S)) {
                wave.start();
                waveStatus = WAVE_IN_PROGRESS;
            }
            wave.update(input);
            if (wave.hasWaveEnded()) {
                updateWaveInfo();
                wave.start();
            }
            buyUpdate(input);
        }
        //Render all sprites such as Towers, bombs and projectiles
        for (int i = toRender.size() - 1; i >= 0; i--) {
            Sprite s = toRender.get(i);
            s.update(input);
            if (s.hasFinished()) {
                toRender.remove(i);
            }
        }
        if (getLives() <= 0) System.exit(0);
        buyPanel.update(input);
        statusPanel.update(input);
    }

    /**
     * Getter function for the wave number.
     *
     * @return an integer value for the wave number.
     */
    public int getWaveID() {
        return waveID;
    }

    /**
     * Getter function for the wave status.
     *
     * @return an String value for the wave status.
     */
    public String getWaveStatus() {
        return waveStatus;
    }

    public void setWaveStatus(String waveStatus) {
        this.waveStatus = waveStatus;
    }

    /**
     * Getter function for the number of remaining lives.
     *
     * @return an integer value for the number of remaining lives.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Getter function for the amount of money the player has.
     *
     * @return an integer value for the amount of money left.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Getter function for the wave object containing enemies.
     *
     * @return an Wave object for the level's current wave, containing all enemies on the map.
     */
    public Wave getWave() {
        return wave;
    }

    /**
     * Add a sprite to the list of objects for level to render.
     *
     * @param sprite a sprite for level to render
     */
    public void addToRender(Sprite sprite) {
        toRender.add(sprite);
    }

    /**
     * Change the current money by some amount
     *
     * @param amount the amount change in money.
     */
    public void addMoney(int amount) {
        money += amount;
    }

    /**
     * Subtract the player's lives by a given amount
     *
     * @param amount the amount of lives lost.
     */
    public void loseLives(int amount) {
        lives -= amount;
    }

    /**
     * Check whether the level has ended
     *
     * @return a boolean value of whether the level has ended.
     */
    public boolean isFinished() {
        return finished;
    }
}
