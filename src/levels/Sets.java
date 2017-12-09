package levels;

import information.Property;

/**
 * Created by Amir Lichter on 13/06/2016.
 */
public class Sets {
    private Property property;
    private String path;

    /**
     * This function constructor.
     *
     * @param property          is Property
     * @param path              is String
     */
    public Sets(Property property, String path) {
        this.property = property;
        this.path = path;
    }

    /**
     * This function return the property.
     *
     * @return the property
     */
    public Property getProperty() {
        return this.property;
    }

    /**
     * This function return the path.
     *
     * @return the path
     */
    public String getPath() {
        return this.path;
    }
}
