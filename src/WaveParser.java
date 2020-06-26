import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class that handles reading in input from the wave file.
 */
public class WaveParser {
    private ArrayList<WaveInfo> waves = new ArrayList<>();
    private int waveIndex = 0;

    /**
     * Reads information for the wave file and stores them
     *
     * @param filename A string of the path to the wave file.
     */
    public WaveParser(String filename) {
        BufferedReader objReader = null;
        try {
            String strCurrentLine;

            objReader = new BufferedReader(new FileReader(filename));

            while ((strCurrentLine = objReader.readLine()) != null) {
                waves.add(new WaveInfo(strCurrentLine));
            }
        } catch (IOException e) {
            System.out.println("Cannot read Wave file.");
        }
    }

    /**
     * Get information about the next wave.
     * @return WaveInfo Object
     */
    public WaveInfo getNextWave() {
        if (waveIndex >= waves.size()) {
            return null;
        }
        WaveInfo res = waves.get(waveIndex);
        waveIndex++;
        return res;
    }
}
