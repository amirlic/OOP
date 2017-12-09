package information;

/**
 * this class is ScoreInfo.
 */
public class ScoreInfo {
    private String name;
    private int score;

    /**
     * This function constructor ScoreInfo.
     *
     * @param name  is String
     * @param score is int
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * This function return the name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * This function return the score.
     *
     * @return the score
     */
    public int getScore() {
        return this.score;
    }
}