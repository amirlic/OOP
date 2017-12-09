package information;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is ColorsParser.
 */
public class ColorsParser {

    // parse color definition and return the specified color.

    /**
     * This function return the image.
     *
     * @param s is String
     * @return a color
     */
    public java.awt.Color colorFromString(String s) {
        //(RGB(244,248,129))
        String fill = s.replaceAll("color", "");
        Pattern p = Pattern.compile("(\\(.*)\\((.*)\\)");
        Matcher m = p.matcher(fill);
        if (m.matches()) {
            String format = m.group(1);
            format = format.substring(1, format.length());
            String colors = m.group(2);
            colors = colors.substring(0, colors.length() - 1);
            if (format.equals("RGB")) {
                return new Color(Integer.parseInt(colors.split(",")[0]),
                        Integer.parseInt(colors.split(",")[1]),
                        Integer.parseInt(colors.split(",")[2]));
            }
        } else if (s.contains("color")) {
            String color = s.substring(6, s.length() - 1);
            if (color.equals("blue")) {
                return Color.blue;
            }
            if (color.equals("black")) {
                return Color.black;
            }
            if (color.equals("pink")) {
                return Color.pink;
            }
            if (color.equals("lightGray")) {
                return Color.lightGray;
            }
            if (color.equals("cyan")) {
                return Color.cyan;
            }
            if (color.equals("gray")) {
                return Color.gray;
            }
            if (color.equals("green")) {
                return Color.green;
            }
            if (color.equals("orange")) {
                return Color.orange;
            }
            if (color.equals("red")) {
                return Color.red;
            }
            if (color.equals("white")) {
                return Color.white;
            }
            if (color.equals("yellow")) {
                return Color.yellow;
            }
        }
        return null;
    }
}
