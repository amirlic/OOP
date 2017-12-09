package information;

/**
 * this class is Property.
 */
public class Property {
    private String key;
    private String value;

    /**
     * This function constructor Property.
     *
     * @param key   is String
     * @param value is String
     */
    public Property(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * This function return the key.
     *
     * @return the key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * This function return the value.
     *
     * @return the value
     */
    public String getValue() {
        return this.value;
    }
}
