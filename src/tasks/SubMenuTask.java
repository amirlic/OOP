package tasks;


import game.GameFlow;
import information.LevelInformation;
import information.LevelSpecificationReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * This class is SubMenuTask.
 */
public class SubMenuTask implements Task<Void> {
    private String path;
    private GameFlow gameflow;

    /**
     * This function constructor.
     *
     * @param path     is String
     * @param gameflow is GameFlow
     */
    public SubMenuTask(String path, GameFlow gameflow) {
        this.path = path;
        this.gameflow = gameflow;
    }

    /**
     * This function run.
     *
     * @return null
     */
    public Void run() {
        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.path);
        List<LevelInformation> levels = levelSpecificationReader.fromReader(new InputStreamReader(is));
        this.gameflow.runLevels(levels);
        return null;
    }
}
