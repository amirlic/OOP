package information;

import levels.BlocksFromSymbolsFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is BlocksDefinitionReader.
 */
public class BlocksDefinitionReader {

    /**
     * This function constructor BlocksDefinitionReader.
     *
     * @param reader is java.io.Reader
     * @return BlocksFromSymbolsFactory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        List<Property> defaultList = new ArrayList<Property>();
        Map<String, Integer> spacerWidths = new HashMap<String, Integer>();
        Map<String, BlockCreator> blockCreators = new HashMap<String, BlockCreator>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.indexOf(' ') != -1) {
                    String defualt = line.substring(0, line.indexOf(' '));
                    if (defualt.equals("default")) {
                        String defualtprop = line.substring(line.indexOf(' ') + 1);
                        String[] defults = defualtprop.split(" ");
                        for (int i = 0; i < defults.length; i++) {
                            defaultList.add(new Property(defults[i].substring(0, defults[i].indexOf(':')),
                                    defults[i].substring(defults[i].indexOf(':') + 1)));
                        }
                    }
                    String def = line.substring(0, line.indexOf(' '));
                    if (def.equals("bdef")) {
                        String bdef = line.substring(line.indexOf(' ') + 1);
                        String[] properties = bdef.split(" ");
                        List<Property> propertyList = new ArrayList<Property>();
                        for (int i = 0; i < properties.length; i++) {
                            propertyList.add(new Property(properties[i].substring(0, properties[i].indexOf(':')),
                                    properties[i].substring(properties[i].indexOf(':') + 1)));
                        }
                        for (int i = 0; i < defaultList.size(); i++) {
                            propertyList.add(defaultList.get(i));
                        }
                        blockCreators.put(propertyList.get(0).getValue(), new BlockCreatorImpl(propertyList));
                    } else if (def.equals("sdef")) {
                        String sdef = line.substring(line.indexOf(' ') + 1);
                        String[] properties = sdef.split(" ");
                        String symbol = properties[0].substring(properties[0].indexOf(':') + 1);
                        int width = Integer.parseInt(properties[1].substring(properties[1].indexOf(':') + 1));
                        spacerWidths.put(symbol, width);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }
}
