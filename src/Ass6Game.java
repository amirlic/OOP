import animations.AnimationRunner;
import game.GameFlow;
import biuoop.GUI;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import information.HighScoresTable;
import levels.LevelSetsReader;
import levels.SetLevels;

/**
 * This class create AssGame6.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class Ass6Game {

    /**
     * This function is main.
     *
     * @param args is String array
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        final AnimationRunner animationRunner = new AnimationRunner(gui);
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
        SetLevels setLevels = new SetLevels(LevelSetsReader.fromReader(new InputStreamReader(is)));
        final HighScoresTable highScoresTable = new HighScoresTable(10);
        File highScore = new File("src\\highScore.txt");
        GameFlow game = new GameFlow(animationRunner, keyboard, gui, highScoresTable, highScore, setLevels);
        game.setMenu();
    }
}

