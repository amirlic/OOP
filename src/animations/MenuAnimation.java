package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is MenuAnimation<T>.
 *
 * @param <T>
 */
public class MenuAnimation<T> implements Menu<T> {
    private String name;
    private boolean stop;
    private List<MenuChoice<T>> list;
    private KeyboardSensor keyboard;

    /**
     * This function constructor MenuAnimation.
     *
     * @param s is String
     * @param k is KeyboardSensor
     */
    public MenuAnimation(String s, KeyboardSensor k) {
        this.name = s;
        this.stop = false;
        this.list = new ArrayList<MenuChoice<T>>();
        this.keyboard = k;
    }

    /**
     * This function add selection.
     *
     * @param key       is String
     * @param message   is String
     * @param returnVal is <T>
     */
    public void addSelection(String key, String message, T returnVal) { //todo OBJECT not working with T!!
        this.list.add(new MenuChoice<T>(key, message, returnVal, false));
    }

    /**
     * This function return a status.
     *
     * @return a status
     */
    public T getStatus() {
        for (MenuChoice<T> menuC : this.list) {
            if (this.keyboard.isPressed(menuC.getKey()) && !menuC.getSub()) {
                return menuC.getReturnVal(); //todo cast?
            } else if (this.keyboard.isPressed(menuC.getKey()) && menuC.getSub()) {
                return null;
            }
        }
        return null;
    }

    /**
     * This function do one frame.
     *
     * @param d  is DrawSurface
     * @param dt is double
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.pink);
        d.drawText(50, 50, this.name, 40);
        int i = 120;
        int j = 120;
        d.setColor(Color.blue);
        for (MenuChoice m : this.list) {
            // todo brackets
            d.drawText(i, j, m.getKey(), 20);
            d.drawText(i + 50, j, m.getPrint(), 20);
            j += 50;
        }

        for (i = 0; i < this.list.size(); i++) {
            if (this.keyboard.isPressed(this.list.get(i).getKey())) {
                this.stop = true;
                break;
            }
        }

    }

    /**
     * This function add selection.
     *
     * @param key     is String
     * @param message is String
     * @param subMenu is Menu<T>
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.list.add(new MenuChoice<T>(key, message, (T) subMenu, true));
    }

    /**
     * This function return if its stop.
     *
     * @return if its stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}