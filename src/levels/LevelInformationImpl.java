package levels;

import collidables.Block;
import collidables.Sprite;
import information.LevelInformation;
import information.Velocity;
import information.ColorsParser;
import information.BackgroundSpriteImpl;
import information.BlocksDefinitionReader;

import javax.imageio.ImageIO;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is LevelInformationImpl.
 */
public class LevelInformationImpl implements LevelInformation {

    private int numberOfBalls;
    private List<Velocity> ballVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int blocksToRemove;
    private BlocksFromSymbolsFactory factory;
    private int xpos;
    private int ypos;
    private int rowHeight;
    private int numBlocks;

    /**
     * This function constructor LevelInformationImpl.
     *
     * @param stringBuilder is StringBuilder
     */
    public LevelInformationImpl(StringBuilder stringBuilder) {
        this.blocks = new ArrayList<Block>();
        String[] lines = stringBuilder.toString().split(System.lineSeparator());
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].equals("START_BLOCKS")) {
                String[] blocksRows = stringBuilder.substring(stringBuilder.indexOf("START_BLOCKS")
                                + "START_BLOCKS".length() + System.lineSeparator().length(),
                        stringBuilder.indexOf("END_BLOCKS")).split(System.lineSeparator());
                for (int j = 0; j < blocksRows.length; j++) {
                    int tempX = this.xpos;
                    for (char symbol : blocksRows[j].toCharArray()) {
                        String symbolAsString = Character.toString(symbol);
                        if (this.factory.isBlockSymbol(symbolAsString)) {
                            Block block = this.factory.getBlock(symbolAsString, tempX, ypos);
                            tempX += block.getCollisionRectangle().getWidth();
                            this.blocks.add(block);
                        } else if (this.factory.isSpaceSymbol(symbolAsString)) {
                            tempX += this.factory.getSpaceWidth(symbolAsString);
                        }
                    }
                    this.ypos += this.rowHeight;
                }
                return;
            } else {
                String key = lines[i].substring(0, lines[i].indexOf(':'));
                String value = lines[i].substring(lines[i].indexOf(':') + 1);
                if (key.equals("level_name")) {
                    this.levelName = value;
                } else if (key.equals("ball_velocities")) {
                    this.ballVelocities = new ArrayList<Velocity>();
                    String[] velocities = value.split(" ");
                    for (int j = 0; j < velocities.length; j++) {
                        this.ballVelocities.add(Velocity.fromAngleAndSpeed(Double.parseDouble(velocities[j].substring(0
                                , velocities[j].indexOf(','))),
                                Double.parseDouble(velocities[j].substring(velocities[j].indexOf(',') + 1))));
                    }
                    this.numberOfBalls = this.ballVelocities.size();
                } else if (key.equals("paddle_speed")) {
                    this.paddleSpeed = Integer.parseInt(value);
                } else if (key.equals("paddle_width")) {
                    this.paddleWidth = Integer.parseInt(value);
                } else if (key.equals("background")) {
                    if (value.startsWith("color")) {
                        ColorsParser colorsParser = new ColorsParser();
                        this.background = new BackgroundSpriteImpl(colorsParser.colorFromString(value));
                    } else {
                        try {
                            String fileName = value;
                            fileName = fileName.substring(fileName.indexOf('(') + 1, fileName.indexOf(')'));
                            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
                            this.background = new BackgroundSpriteImpl(ImageIO.read(is));
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                } else if (key.equals("block_definitions")) {
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(value);
                    this.factory = BlocksDefinitionReader.fromReader(new InputStreamReader(is));
                } else if (key.equals("blocks_start_x")) {
                    this.xpos = Integer.parseInt(value);
                } else if (key.equals("blocks_start_y")) {
                    this.ypos = Integer.parseInt(value);
                } else if (key.equals("row_height")) {
                    this.rowHeight = Integer.parseInt(value);
                } else if (key.equals("num_blocks")) {
                    this.numBlocks = Integer.parseInt(value);
                    this.blocksToRemove = Integer.parseInt(value);
                }
            }
        }
    }

    /**
     * this function is numberOfBalls.
     *
     * @return int
     */
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * this function is initialBallVelocities.
     *
     * @return List of velocity
     */
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocities;
    }

    /**
     * this function is paddleSpeed.
     *
     * @return int
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    /**
     * this function is paddleWidth.
     *
     * @return int
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    /**
     * this function is levelName.
     *
     * @return String
     */
    // the level name will be displayed at the top of the screen.
    public String levelName() {
        return this.levelName;
    }

    @Override
    /**
     * this function is getBackground.
     *
     * @return Sprite
     */
    // Returns a sprite with the background of the level
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    /**
     * this function is blocks.
     *
     * @return List of Blocks
     */
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    /**
     * this function is numberOfBlocksToRemove.
     *
     * @return int
     */
    // Number of levels that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    public int numberOfBlocksToRemove() {
        return this.blocksToRemove;
    }
}
