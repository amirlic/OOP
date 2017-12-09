package game;

import animations.AnimationRunner;
import animations.KeyPressStoppableAnimation;
import animations.MenuAnimation;
import animations.Menu;
import animations.EndScreen;
import animations.HighScoresAnimation;
import biuoop.DialogManager;
import geometry.Point;
import indicator.LevelIndicator;
import indicator.LivesIndicator;
import indicator.ScoreIndicator;
import information.HighScoresTable;
import information.LevelInformation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import information.ScoreInfo;
import levels.SetLevels;
import levels.Sets;
import tasks.Exit;
import tasks.ShowHiScoresTask;
import tasks.SubMenuTask;
import tasks.Task;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This class create GameFlow.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class GameFlow {
    private KeyboardSensor ks;
    private final AnimationRunner ar;
    private Counter scoreCounter;
    private Counter numOfLives;
    private GUI gui;
    private HighScoresTable highScoresTable;
    private File filename;
    private SetLevels setlevels;

    /**
     * This function constructor GameFlow.
     *
     * @param ar              is AnimationRunner
     * @param ks              is KeyboardSensor
     * @param gui             is GUI
     * @param highScoresTable is HighScoresTable
     * @param filename        is File
     * @param setlevels       is SetLevels
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, HighScoresTable highScoresTable, File filename
            , SetLevels setlevels) {
        this.ks = ks;
        this.ar = ar;
        this.gui = gui;
        this.scoreCounter = new Counter(0);
        this.numOfLives = new Counter(7);
        this.highScoresTable = highScoresTable;
        this.filename = filename;
        this.setlevels = setlevels;

    }

    /**
     * This function setMenu.
     */
    public void setMenu() {
        this.highScoresTable = HighScoresTable.loadFromFile(filename);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Welcome to the game", this.ks);
        Menu<Task<Void>> sub = new MenuAnimation<Task<Void>>("subMenu", this.ks);
        for (Sets sets : this.setlevels.getSet()) {
            String fileName = sets.getPath();
            sub.addSelection(sets.getProperty().getKey(), sets.getProperty().getValue()
                    , new SubMenuTask(fileName, this));
        }
        menu.addSubMenu("s", "Play", sub);
        menu.addSelection("h", "High scores",
                new ShowHiScoresTask(this.ar, new HighScoresAnimation(this.highScoresTable), this.ks, this));
        menu.addSelection("q", "quit", new Exit());
        this.ar.run(menu);
        // wait for user selection
        Task<Void> task = menu.getStatus();
        if (task != null) {
            task.run();
        } else {
            this.ar.run(sub);
            Task<Void> task2 = sub.getStatus();
            task2.run();
        }
    }

    /**
     * This function runLevels.
     *
     * @param levels is List of LevelInformation
     */
    public void runLevels(List<LevelInformation> levels) {
        // ...
        // the parameters to addSelection are:
        // key to wait for, line to print, what to return
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar, this.gui, this.scoreCounter, this.numOfLives);
            level.initialize();
            String levelName = level.getLevelInformation().levelName();
            ScoreIndicator score = new ScoreIndicator(new Point(0, 0), 800, 20, Color.lightGray, this.scoreCounter);
            LivesIndicator lives = new LivesIndicator(level.getNumberOfLives());
            LevelIndicator levelIndicator = new LevelIndicator(levelName);
            score.addToGame(level);
            lives.addToGame(level);
            levelIndicator.addToGame(level);
            while (level.getBlockCounter().getValue() > 0 && this.numOfLives.getValue() > 0) {
                level.playOneTurn();
            }
            if (level.getBlockCounter().getValue() == 0) {
                this.scoreCounter.increase(100);
            }
            if (this.numOfLives.getValue() == 0) {
                this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY
                        , new EndScreen(this.ks, this.numOfLives, this.scoreCounter)));
                DialogManager dialog = this.gui.getDialogManager();
                String name = dialog.showQuestionDialog("Name", "What is your name?", "");
                if (this.highScoresTable.getRank(this.scoreCounter.getValue()) < this.highScoresTable.size()) {
                    ScoreInfo scoreInfo = new ScoreInfo(name, this.scoreCounter.getValue());
                    this.highScoresTable.add(scoreInfo);
                    try {
                        this.highScoresTable.save(this.filename);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY
                        , new HighScoresAnimation(this.highScoresTable)));
                this.scoreCounter = new Counter(0);
                this.numOfLives = new Counter(7);
                this.setMenu();
            }
        }
        this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY
                , new EndScreen(this.ks, this.numOfLives, this.scoreCounter)));
        DialogManager dialog = this.gui.getDialogManager();
        String name = dialog.showQuestionDialog("Name", "What is your name?", "");
        if (this.highScoresTable.getRank(this.scoreCounter.getValue()) < this.highScoresTable.size()) {
            ScoreInfo scoreInfo = new ScoreInfo(name, this.scoreCounter.getValue());
            this.highScoresTable.add(scoreInfo);
            try {
                this.highScoresTable.save(this.filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY
                , new HighScoresAnimation(this.highScoresTable)));
        this.scoreCounter = new Counter(0);
        this.numOfLives = new Counter(7);
        this.setMenu();
    }
}