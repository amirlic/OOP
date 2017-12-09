package animations;

import collidables.SpriteCollection;
import biuoop.Sleeper;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * This class create CountdownAnimation.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) secods, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int currentNum;
    private boolean running;

    /**
     * This function constructor.
     *
     * @param numOfSeconds is double
     * @param countFrom    is int
     * @param gameScreen   is SpriteCollection
     */
    //constactor
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.currentNum = countFrom;
        this.gameScreen = gameScreen;
        this.running = true;
    }

    /**
     * This function doOneFrame.
     *
     * @param d  is DrawSurface
     * @param dt is double
     */
    public void doOneFrame(DrawSurface d, double dt) {
        biuoop.Sleeper sleeper = new Sleeper();
        long startTime = System.currentTimeMillis();
        gameScreen.drawAllOn(d);
        d.setColor(Color.BLUE);
        if (this.currentNum > -1) {
            if (this.currentNum > 0) {
                d.drawText((d.getWidth() / 2) - 20, d.getHeight() / 2, Integer.toString(this.currentNum / 100 + 1), 60);
            } else {
                d.drawText((d.getWidth() / 2) - 40, d.getHeight() / 2, "GO!!", 60);
            }
        } else {
            this.running = false;
        }
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = (long) (this.numOfSeconds / (this.countFrom + 1) - usedTime);
        if (milliSecondLeftToSleep > 0) {
            sleeper.sleepFor(milliSecondLeftToSleep);
        }
        this.currentNum--;
    }

    /**
     * This function shouldStop.
     *
     * @return Boolean
     */
    public boolean shouldStop() {
        return !this.running;
    }
}
