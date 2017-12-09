package information;

import collidables.Block;

import geometry.Point;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * This class is BlockCreatorImpl.
 */
public class BlockCreatorImpl implements BlockCreator {
    private List<Property> properties;

    /**
     * This function constructor BlockCreatorImpl.
     *
     * @param properties is List<Property>
     */
    public BlockCreatorImpl(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    /**
     * This function create a block in the location.
     *
     * @param xpos          is int
     * @param ypos          is int
     * @return a block in the location
     */
    public Block create(int xpos, int ypos) {
        TreeMap<Integer, Color> fill = new TreeMap<Integer, Color>();
        HashMap<Integer, String> images = new HashMap<Integer, String>();
        Point upperLeft = new Point(xpos, ypos);
        double width = -1;
        double height = -1;
        int hits = 1;
        Color stroke = null;
        ColorsParser colorsParser = new ColorsParser();
        for (Property property : this.properties) {
            if (property.getKey().equals("width") && width == -1) {
                width = Integer.parseInt(property.getValue());
            } else if (property.getKey().equals("height") && height == -1) {
                height = Integer.parseInt(property.getValue());
            } else if (property.getKey().equals("hit_points")) {
                hits = Integer.parseInt(property.getValue());
            } else if (property.getKey().equals("stroke") && stroke == null) {
                stroke = colorsParser.colorFromString(property.getValue());
            } else {
                int temp = hits;
                for (int i = temp; i > 0; i--) {
                    if (property.getValue().startsWith("color")) {
                        if (property.getKey().equals("fill-" + String.valueOf(i)) && fill.get(i) == null) {
                            fill.put(i, colorsParser.colorFromString(property.getValue()));
                            break;
                        } else {
                            for (int j = 0; j < this.properties.size(); j++) {
                                if (this.properties.get(j).getKey().equals("fill")) {
                                    fill.put(i, colorsParser.colorFromString(this.properties.get(j).getValue()));
                                    break;
                                }
                            }
                        }
                    } else if (property.getValue().startsWith("image")) {
                        if (property.getKey().equals("fill-" + String.valueOf(i)) && images.get(i + 1) == null) {
                            images.put(i, property.getValue());
                            break;
                        } else {
                            for (int j = 0; j < this.properties.size(); j++) {
                                if (this.properties.get(j).getKey().equals("fill")) {
                                    images.put(i, this.properties.get(j).getValue());
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (fill.size() != 0) {
            return new Block(upperLeft, width, height, hits, fill, stroke);
        } else if (images.size() != 0) {
            return new Block(upperLeft, width, height, hits, images, stroke);
        } else {
            return null;
        }
    }
}