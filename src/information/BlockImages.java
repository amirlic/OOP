package information;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is BlockImages.
 */
public class BlockImages {

    private static Map<String, Image> images = new HashMap<String, Image>();

    /**
     * This function static is getImage.
     *
     * @param fileName is String
     * @return Image
     */
    public static Image getImage(String fileName) {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
        if (!images.containsKey(fileName)) {
            try {
                images.put(fileName, ImageIO.read(is));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return images.get(fileName);
    }
}