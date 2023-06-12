package GamePanels;

// Import the required Packages
import javax.swing.*;
import java.awt.*;
import java.io.File;
import ActionListeners.StartPanelActionListeners.*;

/*
 * This class creates the start screen of the game
 * Inherits ParentPanel
 */
public class StartPanel extends ParentPanel {
    // Create the instance variables
    private JButton playButton = new JButton();
    private JButton exitButton = new JButton();
    private JLabel title = new JLabel();

    /**
     * Empty constructor 
     */
    public StartPanel() {
        
    }

    /**
     * Sets the background file and the font file
     * @param backgroundFile - Type: File (file of background image)
     * @param fontFile - Type: File (file of font)
     */
    public StartPanel(File backgroundFile, File fontFile) {
        super(backgroundFile, fontFile);
        createPanel();
    }

    /**
     * Creates the elements inside the panel
     */
    public void createPanel() {
        // Initalizing the settings of the panel
        // Make the panel visible
        setVisible(true);
        // Set the size of the panel to be 900x500
        setSize(900,600);
        // Make it so that there is no layout
        setLayout(null);

        // Set the background and the font (Methods from super class)
        //setBackground();
        createFont();

        // Set the font of the buttons as the custom font
        playButton.setFont(customFont);
        exitButton.setFont(customFont);

        // Initialize the settings of the play button
        // Set the text to "Play"
        playButton.setText("Play");
        // Make the button transparent
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        // Make the border of the button not visible
        playButton.setBorderPainted(false);
        // Set the location and the size of but button
        playButton.setBounds(getWidth()/2 - 250, getHeight()/2, 200, 100);
        // Set the colour of text to white
        playButton.setForeground(Color.WHITE);

        // Initialize the settings of the exit button
        // Set the text to "Exit"
        exitButton.setText("Exit");
        // Make the button transparent
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        // Make the border of the butotn not visible
        exitButton.setBorderPainted(false);
        // Set the location and size of the button
        exitButton.setBounds(getWidth()/2 + 50, getHeight()/2, 200, 100);
        // Set the colour of the text to white
        exitButton.setForeground(Color.WHITE);

        // Add an action listener to both buttons
        playButton.addActionListener(new PlayButtonActionListener(this, frame));
        exitButton.addActionListener(new ExitButtonActionListener());

        // Initialize the text of the label
        // Set the text/title as "Legend Battle Simulator"
        title.setText("Legend Battle Simulator");
        // Set the font to the custom font
        title.setFont(customFont.deriveFont(70f));
        // Set the text colour to white
        title.setForeground(Color.WHITE);
        // Set the location and size of the button
        title.setBounds(125, getHeight()/2 - 100, 700, 100);

        // Add the buttons and label to the panel
        add(playButton);
        add(exitButton);
        add(title);
    }

    /**
     * Paints the background of the panel
     */
    @Override
    public void paintComponent(Graphics g) {
        // Create a paint component
        super.paintComponent(g);

        // Set the background image of the panel
        g.drawImage(background, 0, 0, this);
    }
    
}
