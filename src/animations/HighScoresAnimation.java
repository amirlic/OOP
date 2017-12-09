package animations;

import biuoop.DrawSurface;
import information.HighScoresTable;
import information.ScoreInfo;

/**
 * This class is HighScoresAnimation.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;
    private boolean stop;

    /**
     * This function constructor HighScoresAnimation.
     *
     * @param scores is HighScoresTable
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
        this.stop = false;
    }

    /**
     * This function doOneFrame.
     *
     * @param d  is DrawSurface
     * @param dt is double
     */
    public void doOneFrame(DrawSurface d, double dt) {
        int i = 0;
        for (ScoreInfo scoreInfo : this.scores.getHighScores()) {
            d.drawText(50, 50 + (30 * i), scoreInfo.getName() + ":" + scoreInfo.getScore(), 32);
            i++;
        }
    }

    /**
     * This function shouldStop.
     *
     * @return Boolean
     */
    public boolean shouldStop() {
        return this.stop;
    }
}