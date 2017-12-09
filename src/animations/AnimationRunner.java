package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * This class create AnimationRunner.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class AnimationRunner {
    private int framesPerSecond;
    private Sleeper sleeper;
    private GUI gui;

    /**
     * This function constructor.
     *
     * @param gui is GUI
     */
    //constactor
    public AnimationRunner(GUI gui) {
        this.sleeper = new biuoop.Sleeper();
        this.framesPerSecond = 60;
        this.gui = gui;
    }

    /**
     * This function run.
     *
     * @param animation is Animation
     */
    // ...
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.getFramesPerSecond();
        double secondsPassedSinceLastFrame = 0;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = this.gui.getDrawSurface();

            animation.doOneFrame(d, secondsPassedSinceLastFrame);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
                secondsPassedSinceLastFrame = (double) millisecondsPerFrame / 1000;
            } else {
                secondsPassedSinceLastFrame = (double) usedTime / 1000;
            }
        }
    }

    /**
     * This function getFramesPerSecond.
     *
     * @return int
     */
    public int getFramesPerSecond() {
        return this.framesPerSecond;
    }
}