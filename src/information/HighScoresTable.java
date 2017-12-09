package information;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class is HighScoresTable.
 */
public class HighScoresTable {
    private int maxSize;
    private List<ScoreInfo> scores;

    // Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.

    /**
     * This function constructor HighScoresTable.
     *
     * @param size is int
     */
    public HighScoresTable(int size) {
        this.maxSize = size;
        this.scores = new ArrayList<ScoreInfo>(this.maxSize);
    }

    // Add a high-score.

    /**
     * This function add high-score.
     *
     * @param score is ScoreInfo
     */
    public void add(ScoreInfo score) {
        if (this.scores.size() < this.maxSize) {
            this.scores.add(score);
        } else {
            if (this.scores.get(this.size() - 1).getScore() < score.getScore()) {
                this.scores.remove(this.size() - 1);
                this.scores.add(score);
            }
        }
    }

    // Return table size.

    /**
     * This function return the size.
     *
     * @return the size
     */
    public int size() {
        return this.maxSize;
    }

    // Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.

    /**
     * This function return the high-scores list.
     *
     * @return the high-scores list
     */
    public List<ScoreInfo> getHighScores() {
        return sort();
    }

    // return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.

    /**
     * This function return the rank.
     *
     * @param score is int
     * @return the rank
     */
    public int getRank(int score) {
        sort();
        int rank = 1;
        for (int i = 0; i < this.scores.size(); i++) {
            if (score > this.scores.get(i).getScore()) {
                break;
            }
            rank++;
        }
        return rank;
    }

    // Clears the table

    /**
     * This function clear the list.
     */
    public void clear() {
        this.scores.clear();
    }

    // Load table data from file.
    // Current table data is cleared.

    /**
     * This function load the file.
     *
     * @param filename is File
     * @throws IOException e
     */
    public void load(File filename) throws IOException {
        clear();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String scoreLine = null;
        while ((scoreLine = reader.readLine()) != null) {
            String name = scoreLine.substring(0, scoreLine.indexOf(':'));
            int score = Integer.parseInt(scoreLine.substring(scoreLine.indexOf(':') + 1));
            this.scores.add(new ScoreInfo(name, score));
        }
        reader.close();
    }

    // Save table data to the specified file.

    /**
     * This function save the file.
     *
     * @param filename is File
     * @throws IOException e
     */
    public void save(File filename) throws IOException {
        PrintWriter writer = new PrintWriter(filename);
        sort();
        for (ScoreInfo scoreInfo : this.scores) {
            writer.write(scoreInfo.getName() + ":" + scoreInfo.getScore() + System.lineSeparator());
        }
        writer.close();
    }

    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.

    /**
     * This function load the highScoresTable.
     *
     * @param filename is File
     * @return the highScoresTable
     */
    public static HighScoresTable loadFromFile(File filename) {
        try {
            HighScoresTable highScoresTable = new HighScoresTable(10);
            highScoresTable.load(filename);
            return highScoresTable;
        } catch (IOException e) {
            return new HighScoresTable(10);
        }
    }

    /**
     * This function sort the list.
     *
     * @return the sorted list
     */
    private List<ScoreInfo> sort() {
        Collections.sort(this.scores, new Comparator<ScoreInfo>() {
            @Override
            public int compare(ScoreInfo o1, ScoreInfo o2) {
                return (o2.getScore() - o1.getScore());
            }
        });
        return this.scores;
    }
}
