package tasks;

/**
 * This class is Exit.
 */

public class Exit implements Task<Void> {

    /**
     * This function constructor Exit.
     */
    public Exit() {
    }

    /**
     * This function run.
     *
     * @return null
     */
    public Void run() {
        System.exit(0);
        return null;
    }
}