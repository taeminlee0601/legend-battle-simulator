package GamePanels;

// Import the required Packages
import javax.imageio.ImageIO;
import javax.swing.*;

import MainGameFrame.GameFrame;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

/*
 * This class is the Parent Class to the Panels that will be made in this project.
 */
public class ParentPanel extends JPanel {
    // Initialize the instance variables needed
    // Set to protected so the subclasses can access them
    protected BufferedImage bufferedBackground;
    protected Image background;
    protected File backgroundFile;
    protected File fontFile;
    protected Font customFont;
    protected ParentPanel nextPanel;
    protected GameFrame frame;
    
    /**
     * Empty Constructor so the object can be created without parameters
     */
    public ParentPanel() {
        
    }

    /**
     * Adds the background file and the font file of the panel
     * @param backgroundFile - Type: File (File of background image)
     * @param fontFile - Type: File (File of font)
     */
    public ParentPanel(File backgroundFile, File fontFile) {
        this.backgroundFile = backgroundFile;
        this.fontFile = fontFile;
    }

    public ParentPanel(File fontFile) {
        this.fontFile = fontFile;
    }

    /**
     * Creates the background of the panel
     * Resizes the image to 900x500 (size of frame/panel)
     */
    public void setBackground() {
        // Checks if an IOException is thrown
        // IOException -> occurs when a file is not found
        try {
            // Creates a buffredImage that reads the image files
            bufferedBackground = ImageIO.read(backgroundFile);
            // Resizing the background file 
            background = bufferedBackground.getScaledInstance(900, 600, Image.SCALE_DEFAULT);
        // Checks for and IOException
        } catch (IOException e) {
            // Prints the error
            e.printStackTrace();
        }
    }

    /**
     * Creates the font used in the panel
     * Sets the default size to be 30
     */
    public void createFont() {
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
    
    /**
     * Sets the background file
     * @param backgroundFile - Type: File (File of background image)
     */
    public void setBackgroundFile(File backgroundFile) {
        this.backgroundFile = backgroundFile;
    }

    /**
     * Sets the font file
     * @param fontFile - Type: File (File of font)
     */
    public void setFontFile(File fontFile) {
        this.fontFile = fontFile;
    }

    /**
     * Sets the main frame
     * @param frame - Type: GameFrame (Main gameframe)
     */
    public void setFrame(GameFrame frame) {
        this.frame = frame;
    }
 
    /**
     * Sets the background image
     * @param background
     */
    public void setBackgroundImage(ImageIcon background) {
        this.background = background.getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT);
    }
}
