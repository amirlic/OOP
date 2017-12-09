package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This class is KeyPressStoppableAnimation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private Boolean stop;
    private boolean isAlreadyPressed;

    /**
     * This function constructor KeyPressStoppableAnimation.
     *
     * @param animation is Animation
     * @param sensor    is KeyboardSensor
     * @param key       is String
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * This function doOneFrame.
     *
     * @param d  is DrawSurface
     * @param dt is double
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.sensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        }
        if (!(this.sensor.isPressed(this.key))) {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * This function shouldStop.
     *
     * @return Boolean
     */
    public boolean shouldStop() {
        return this.stop;
    }
    // ...
    // think about the implementations of doOneFrame and shouldStop.
}
