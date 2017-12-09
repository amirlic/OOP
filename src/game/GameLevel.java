package game;

import java.awt.Color;
import java.util.ArrayList;

import animations.Animation;
import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import geometry.Point;
import collidables.Collidable;
import collidables.Block;
import collidables.Paddle;
import collidables.Ball;
import collidables.Sprite;
import collidables.SpriteCollection;
import information.LevelInformation;
import information.Velocity;
import listener.ScoreTrackingListener;
import remover.BallRemover;
import remover.BlockRemover;
import biuoop.KeyboardSensor;
import biuoop.GUI;
import biuoop.DrawSurface;

/**
 * This class create GameLevel.
 *
 * @author Amir Lichter , Dvir levitas
 * @version 1.7
 */
public class GameLevel implements Animation {
    private LevelInformation levelInformation;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blockCounter;
    private Counter ballCounter;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private Counter scoreCounter;
    private Counter numberOfLives;
    private ScoreTrackingListener scoreTrackingListener;
    private Paddle paddle;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboardSensor;
    private Boundary bound;

    /**
     * This function constructor GameLevel.
     *
     * @param levelInformation is LevelInformation
     * @param ks               is KeyboardSensor
     * @param ar               is AnimationRunner
     * @param gui              is GUI
     * @param scoreCounter     is Counter
     * @param numberOfLives    is Counter
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor ks,
                     AnimationRunner ar, GUI gui, Counter scoreCounter, Counter numberOfLives) {
        this.levelInformation = levelInformation;
        this.gui = gui;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment(new ArrayList());
        this.blockCounter = new Counter(0);
        this.ballCounter = new Counter(0);
        this.scoreCounter = scoreCounter;
        this.blockRemover = new BlockRemover(this, this.blockCounter);
        this.scoreTrackingListener = new ScoreTrackingListener(this.scoreCounter);
        this.ballRemover = new BallRemover(this, this.ballCounter);
        this.numberOfLives = numberOfLives;
        this.blockCounter.increase(this.levelInformation.numberOfBlocksToRemove());
        this.keyboardSensor = ks;
        this.runner = ar;
        this.bound = addBoundaries();

    }

    /**
     * This function getNumberOfLives.
     *
     * @return Counter
     */
    public Counter getNumberOfLives() {
        return this.numberOfLives;
    }

    /**
     * This function add Collidable to the Game.
     *
     * @param c is Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * This function add sprite to the Game.
     *
     * @param s is sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * This function initialize the Game.
     */
    // Initialize a new Game: create the Blocks and Ball (and Paddle)
    // and add them to the Game.
    public void initialize() {
        addSprite(this.levelInformation.getBackground());
        this.createBlocks();
    }

    /**
     * This function do the boundaries.
     *
     * @return bound
     */
    public Boundary addBoundaries() {
        int height = gui.getDrawSurface().getHeight();
        int width = gui.getDrawSurface().getWidth();
        Block b1 = new Block(new Point(0, 20), width, 20, 0, Color.black);
        Block b2 = new Block(new Point(0, 20), 20, height - 20, 0, Color.black);
        Block b3 = new Block(new Point(width - 20, 20), 20, height - 20, 0, Color.black);
        Block b4 = new Block(new Point(20, width - 200), width - 40, 2, 0, Color.black);
        Block[] blockArr = {b1, b2, b3, b4};
        for (int i = 0; i < blockArr.length; i++) {
            blockArr[i].addToGame(this);
        }
        b4.addHitListener(this.getBallRemover());
        int widthRect = (int) b2.getCollisionRectangle().getWidth();
        int heightRect = (int) b2.getCollisionRectangle().getHeight();
        return new Boundary(width - widthRect, widthRect, heightRect, height - heightRect);
    }

    /**
     * This function getEnvironment.
     *
     * @return GameEnvironment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * This function getBlockRemover.
     *
     * @return BlockRemover
     */
    public BlockRemover getBlockRemover() {
        return this.blockRemover;
    }

    /**
     * This function getBallRemover.
     *
     * @return BallRemover
     */
    public BallRemover getBallRemover() {
        return this.ballRemover;
    }

    /**
     * This function getLevelInformation.
     *
     * @return LevelInformation
     */
    public LevelInformation getLevelInformation() {
        return this.levelInformation;
    }

    /**
     * This function getBlockCounter.
     *
     * @return Counter
     */
    public Counter getBlockCounter() {
        return this.blockCounter;
    }

    /**
     * This function getBallCounter.
     *
     * @return Counter
     */
    public Counter getBallCounter() {
        return this.ballCounter;
    }

    // ...

    /**
     * This function shouldStop.
     *
     * @return Boolean
     */
    public boolean shouldStop() {
        boolean b = !this.running;
        return b;
    }

    /**
     * This function doOneFrame.
     *
     * @param d  is DrawSurface
     * @param dt is double
     */
    public void doOneFrame(DrawSurface d, double dt) {
        // the logic from the previous playOneTurn method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        // ...
        if (this.blockCounter.getValue() == 0) {
            this.scoreCounter.increase(100);
            this.running = false;
        }
        if (this.ballCounter.getValue() == 0) { //if touching shield
            this.numberOfLives.decrease(1);
            this.running = false;
        }
//            long startTime = System.currentTimeMillis(); // timing
        this.sprites.drawAllOn(d);
//            runner.getGui().show(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.keyboardSensor.isPressed("p")) {
            runner.run(new KeyPressStoppableAnimation(this.keyboardSensor
                    , KeyboardSensor.SPACE_KEY, new PauseScreen(this.keyboardSensor)));
        }
    }

    /**
     * This function playOneTurn the Game.
     */
    // Run the Game -- start the animation loop.
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle(this.bound); // or a similar method
        this.paddle = new Paddle(this.keyboardSensor, this.bound, this.levelInformation);
        this.paddle.addToGame(this);
        //group of aliens
        this.runner.run(new CountdownAnimation(300, 300, sprites)); // countdown before turn starts.
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        paddle.removeFromGame(this);
    }

    /**
     * This function createBallsOnTopOfPaddle.
     *
     * @param boundary is Boundary
     */
    private void createBallsOnTopOfPaddle(Boundary boundary) {
        for (Velocity vel : this.getLevelInformation().initialBallVelocities()) {
            Ball ball = new Ball((boundary.getRightbound() + boundary.getLeftbound()) / 2,
                    boundary.getUpbound() - 30, 5, Color.WHITE, this.getEnvironment());
            ball.setVelocity(vel);
            ball.addToGame(this);
            this.getBallCounter().increase(1);
        }
    }

    /**
     * This function createBlocks.
     */
    public void createBlocks() {
        for (Block block : this.getLevelInformation().blocks()) {
            block.addHitListener(this.scoreTrackingListener);
            block.addHitListener(this.getBlockRemover());
            block.addToGame(this);
        }
    }

    /**
     * This function remove Collidable.
     *
     * @param c is Collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.remomveCollidable(c);
    }

    /**
     * This function remove sprite to the Game.
     *
     * @param s is sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This function compration first and second.
     *
     * @param first  is double
     * @param second is double
     * @return true if equals
     */
    public static Boolean comperation(double first, double second) {
        if ((first - second) > -0.01 && (first - second) < 0.01) {
            return true;
        }
        return false;
    }

    /**
     * This function chek if the first bigger then the second.
     *
     * @param first  is double
     * @param second is double
     * @return true if bigger or equals
     */
    public static Boolean biggerComperation(double first, double second) {
        if (comperation(first, second) || first > second) {
            return true;
        }
        return false;
    }

    /**
     * This function chek if the second bigger then the first.
     *
     * @param first  is double
     * @param second is double
     * @return true if less or equals
     */
    public static Boolean lessComperation(double first, double second) {
        if (comperation(first, second) || first < second) {
            return true;
        }
        return false;
    }
}

