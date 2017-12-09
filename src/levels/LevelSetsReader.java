package levels;

import information.Property;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amir Lichter on 13/06/2016.
 */
public class LevelSetsReader {
    /**
     * This function return a list.
     *
     * @param reader          is java.io.reader
     * @return a list
     */
    public static List<Sets> fromReader(java.io.Reader reader) {
        int count = 0;
        List<Sets> setList = new ArrayList<Sets>();
        List<String> descriptionList = new ArrayList<String>();
        List<Property> propertyList = new ArrayList<Property>();
        BufferedReader bufferedReader = null;
        String line;
        try {
            bufferedReader = new BufferedReader(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //String[] lines = stringBuilder.toString().split(System.lineSeparator());
        try {
            while ((line = bufferedReader.readLine()) != null) {
                count++;
                if (line.contains(":")) {
                    String key = line.split(":")[0];
                    String value = line.split(":")[1];
                    propertyList.add(new Property(key, value));
                } else if (!line.equals("definitions") && !line.equals("")) {
                    descriptionList.add(line);
                } else if (line.equals("definitions") || line.equals("")) {
                    count--;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < (count / 2); i++) {
            Property property = propertyList.get(i);
            String description = descriptionList.get(i);
            setList.add(i, new Sets(property, description));
        }
        return setList;
    }
}
