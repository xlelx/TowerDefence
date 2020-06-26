import bagel.*;
import bagel.Font;
import bagel.Image;
import bagel.Window;
import bagel.util.Colour;
import bagel.util.Point;
import bagel.util.Rectangle;
import java.util.ArrayList;

/**
 * Represents an buy panel entity.
 */
public class BuyPanel extends Panel {
    private final String FONT_FILE = "res/fonts/DejaVuSans-Bold.ttf";
    private final String PANEL_FILE = "res/images/buypanel.png";
    private final String TANK_FILE = "res/images/tank.png";
    private final String SUPERTANK_FILE = "res/images/supertank.png";
    private final String AIRSUPPORT_FILE = "res/images/airsupport.png";
    private final String keyBind = "Key binds: ";
    private final String startWave = "S - Start Wave";
    private final String incTime = "L - Increase Timescale";
    private final String decTime = "K - Decrease TimeScale";
    private final String priceTank = "$250";
    private final String priceSuperTank = "$600";
    private final String priceAirSupport = "$500";

    private final int tankPrice = 250;
    private final int superTankPrice = 600;
    private final int airSupportPrice = 500;

    private Font keyBindFont = new Font(FONT_FILE, 15);
    private Font priceFont = new Font(FONT_FILE, 20);
    private Font moneyFont = new Font(FONT_FILE, 50);

    private Image panel;
    private Image tank;
    private Image superTank;
    private Image airSupport;


    private DrawOptions drawOptions;

    private double tankX = 64;
    private double superTankX = 64 + 120;
    private double airSupportX = 64 + 120 * 2;
    private double towersY;


    /**
     * Creates an instance of a buy panel
     *
     * @param level a Level object containing information about the level.
     */
    public BuyPanel(Level level) {
        super(level);
        panel = new Image(PANEL_FILE);
        tank = new Image(TANK_FILE);
        superTank = new Image(SUPERTANK_FILE);
        airSupport = new Image(AIRSUPPORT_FILE);
        drawOptions = new DrawOptions();
        towersY = panel.getHeight() / 2 - 10;
    }

    /**
     * Render text for buy panel.
     */
    private void renderKeyBinds() {
        keyBindFont.drawString(keyBind, Window.getWidth() / 2 - 50, 20);
        keyBindFont.drawString(startWave, Window.getWidth() / 2 - 50, 40);
        keyBindFont.drawString(incTime, Window.getWidth() / 2 - 50, 55);
        keyBindFont.drawString(decTime, Window.getWidth() / 2 - 50, 70);
    }

    /**
     * Render towers in the buy panel.
     */
    private void renderTowers() {

        tank.draw(tankX, towersY);
        setPriceColor(tankPrice);
        priceFont.drawString(priceTank, tankX - tank.getWidth() / 2, towersY + 50, drawOptions);
        superTank.draw(superTankX, towersY);
        setPriceColor(superTankPrice);
        priceFont.drawString(priceSuperTank, superTankX - superTank.getWidth() / 2, towersY + 50, drawOptions);
        airSupport.draw(airSupportX, towersY);
        setPriceColor(airSupportPrice);
        priceFont.drawString(priceAirSupport, airSupportX - airSupport.getWidth() / 2, towersY + 50, drawOptions);
    }

    /**
     * Change the color of the price depending of the player's money.
     * @param price price of the tower
     */
    private void setPriceColor(int price) {
        if (getLevel().getMoney() >= price) drawOptions.setBlendColour(Colour.GREEN);
        else drawOptions.setBlendColour(Colour.RED);
    }

    /**
     * Render player's money in buy panel.
     */
    private void renderMoney() {
        moneyFont.drawString(String.format("$%d", getLevel().getMoney()), panel.getWidth() - 200, 65);
    }

    /**
     * Checks where the mouse pointer is at when it tries to buy a tower/plane. Returns relevant information
     * about the item that is being attempted to be bought.
     *
     * @param input
     * @return an ArrayList<String> object containing wave status information at index 0 and tower information at index 1
     */
    public ArrayList<String> buyAction(Input input) {
        ArrayList<String> result = new ArrayList<>();
        if (input.wasPressed(MouseButtons.LEFT)) {
            if (tank.getBoundingBoxAt(new Point(tankX, towersY)).intersects(input.getMousePosition())) {
                result.add(Level.PLACING);
                result.add("tank");
                return result;
            } else if (superTank.getBoundingBoxAt(new Point(superTankX, towersY)).intersects(input.getMousePosition())) {
                result.add(Level.PLACING);
                result.add("supertank");
                return result;
            } else if (airSupport.getBoundingBoxAt(new Point(airSupportX, towersY)).intersects(input.getMousePosition())) {
                result.add(Level.PLACING);
                result.add("airsupport");
                return result;
            }
        } else if (input.wasPressed(MouseButtons.RIGHT) && getLevel().getWaveStatus().equals(Level.PLACING)) {
            result.add("refresh");
            result.add("");
            return result;
        }
        return result;
    }

    /**
     * Gets the Rectangle associated with the panel
     *
     * @return Rectangle object for the panel
     */
    @Override
    public Rectangle getBoundingBox() {
        return panel.getBoundingBoxAt(new Point(Window.getWidth() / 2, panel.getHeight() / 2));
    }

    /**
     * Updates the panel information to be shown.
     *
     * @param input an Input object containing all of the user's inputs.
     */
    @Override
    public void update(Input input) {
        panel.draw(Window.getWidth() / 2, panel.getHeight() / 2);
        renderKeyBinds();
        renderTowers();
        renderMoney();
    }
}
