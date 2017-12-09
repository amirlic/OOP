package information;

import levels.LevelInformationImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is LevelSpecificationReader.
 */
public class LevelSpecificationReader {

    /**
     * This function return the list.
     *
     * @param reader is java.io.Reader
     * @return a list
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levelInformations = new ArrayList<LevelInformation>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("START_LEVEL")) {
                    stringBuilder = new StringBuilder();
                } else if (line.equals("END_LEVEL")) {
                    levelInformations.add(new LevelInformationImpl(stringBuilder));
                } else {
                    stringBuilder.append(line + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return levelInformations;
    }
}
