package information;

import collidables.Block;

/**
 * This class is Interface.
 */
public interface BlockCreator {

    // Create a block at the specified location.

    /**
     * This function create block in the location.
     *
     * @param xpos is int
     * @param ypos is int
     * @return a block in the location
     */
    Block create(int xpos, int ypos);
}
