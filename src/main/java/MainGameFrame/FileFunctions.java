package MainGameFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.peer.FileDialogPeer;

public class FileFunctions {

    /**
     * Returns a resized Image variable
     * @param width
     * @param height
     * @return resizedImage
     */
    public static Image resizeImage(File imageFile, int width, int height) {
        BufferedImage bufferedImage;
        Image resizedImage;

        try {
            bufferedImage = ImageIO.read(imageFile);
            resizedImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);

            return resizedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void createCustomFont(File fontFile) {
        Font customFont;

        // Checks for an IOException or FontFormatException
        try {
            // Creates an graphic environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            // Creates the custom font
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(50f);
            // Adds the font to the graphics environment
            ge.registerFont(customFont);
        // Checks for an IOException
        } catch (IOException e) {
            // Handle exception + Prints Error
            e.printStackTrace();
        // Checks for a FontFormatException
        } catch (FontFormatException e) {
            // Handles Exceptions + Prints Error
            e.printStackTrace();
        }
    }
}
