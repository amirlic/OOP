package game;

/**
 * This class create Counter.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class Counter {
    private int count;

    /**
     * This function constructor Counter.
     *
     * @param value is int
     */
    public Counter(int value) {
        this.count = value;
    }

    /**
     * This function increase.
     *
     * @param number is int
     */
    // add number to current count.
    public void increase(int number) {
        this.count += number;
    }

    /**
     * This function decrease.
     *
     * @param number is int
     */
    // subtract number from current count.
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * This function fetValue.
     *
     * @return int count
     */
    // get current count.
    public int getValue() {
        return this.count;
    }
}