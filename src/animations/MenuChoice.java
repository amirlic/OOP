package animations;

/**
 * This class is MenuChoice<T>.
 *
 * @param <T>
 */
public class MenuChoice<T> {
    private String key;
    private String print;
    private T returnVal;
    private Boolean isSub;

    /**
     * This function constructor MenuChoice.
     *
     * @param k     is String
     * @param p     is String
     * @param r     is <T>
     * @param isSub is Boolean
     */
    public MenuChoice(String k, String p, T r, Boolean isSub) {
        this.key = k;
        this.print = p;
        this.returnVal = r;
        this.isSub = isSub;
    }

    /**
     * This function return the value.
     *
     * @return the value
     */
    public T getReturnVal() {
        return this.returnVal;
    }

    /**
     * This function return a print.
     *
     * @return a print
     */
    public String getPrint() {
        return print;
    }

    /**
     * This function return the key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * This function return if its sub.
     *
     * @return if its sub
     */
    public Boolean getSub() {
        return this.isSub;
    }
}