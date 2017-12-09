package levels;

import collidables.Block;
import information.BlockCreator;

import java.util.Map;

/**
 * This class BlocksFromSymbolsFactory.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * This function constructor.
     *
     * @param spacerWidths  is Map<String, Integer>
     * @param blockCreators is Map<String, BlockCreator>
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    // returns true if 's' is a valid space symbol.

    /**
     * This function return if 's' is a valid space symbol.
     *
     * @param s is String
     * @return is 's'  is a valid space symbol
     */
    public boolean isSpaceSymbol(String s) {
        if (this.spacerWidths.get(s) != null) {
            return true;
        }
        return false;
    }

    // returns true if 's' is a valid block symbol.

    /**
     * This function if 's' is a valid block symbol.
     *
     * @param s is String
     * @return if 's' is a valid block symbol
     */
    public boolean isBlockSymbol(String s) {
        if (this.blockCreators.get(s) != null) {
            return true;
        }
        return false;
    }

    // Return a block according to the definitions associated
    // with symbol s. The block will be located at position (xpos, ypos).

    /**
     * This function return block.
     *
     * @param s    is String
     * @param xpos is int
     * @param ypos is int
     * @return a block
     */
    public Block getBlock(String s, int xpos, int ypos) {
        BlockCreator blockCreator = this.blockCreators.get(s);
        Block block = blockCreator.create(xpos, ypos);
        return block;
    }

    // Returns the width in pixels associated with the given spacer-symbol.

    /**
     * This function return the space width.
     *
     * @param s is String
     * @return the space width
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}