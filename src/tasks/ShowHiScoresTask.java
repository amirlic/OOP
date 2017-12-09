package tasks;

import animations.Animation;
import animations.AnimationRunner;
import animations.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import game.GameFlow;
/**
 * This class is tasks.
 */

/**
 * Created by Amir Lichter on 09/06/2016.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private KeyboardSensor ks;
    private GameFlow gameFlow;

    /**
     * This function constructor ShowHiScoresTask.
     *
     * @param runner              is AnimationRunner
     * @param highScoresAnimation is Animation
     * @param ks                  is KeyboardSensor
     * @param gameFlow            is GameFlow
     */
    public ShowHiScoresTask(
            AnimationRunner runner, Animation highScoresAnimation, KeyboardSensor ks, GameFlow gameFlow) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
        this.ks = ks;
        this.gameFlow = gameFlow;
    }

    /**
     * This function run.
     *
     * @return null
     */
    public Void run() {
        this.runner.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY
                , this.highScoresAnimation));
        this.gameFlow.setMenu();
        return null;
    }
}