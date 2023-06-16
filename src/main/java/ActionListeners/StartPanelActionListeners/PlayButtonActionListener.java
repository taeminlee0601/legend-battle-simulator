package ActionListeners.StartPanelActionListeners;

// Import required packages
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import GamePanels.CharacterSelectPanel;
import GamePanels.GameOverPanel;
import GamePanels.ParentPanel;
import GamePanels.StartPanel;
import MainGameFrame.GameFrame;

/*
 * This class is the action listener class of the play button in the start panel
 * Implements ActionLister
 */
public class PlayButtonActionListener implements ActionListener {
    // Create instance variables
    private ParentPanel panel;
    // Create the next panel
    private CharacterSelectPanel selectPanel = new CharacterSelectPanel();
    private GameFrame frame;

    /**
     * This constructor will set the start panel and the frame
     * @param startPanel - Type: StartPanel (StartPanel of the game)
     * @param frame - Type: GameFrame (Main frame of the game)
     */
    public PlayButtonActionListener(StartPanel panel, GameFrame frame) {
        this.panel = panel;
        this.frame = frame;
    }

    public PlayButtonActionListener(GameOverPanel panel, GameFrame frame) {
        this.panel = panel;
        this.frame= frame;

        if (frame == null) {
            System.out.println("WHY IS THIS FUCKING NULL");
        }
    }

    /**
     * This method will check if the button is pressed and if yes will go to the next panel
     * Implements ActionListener
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // Initializes the settings of the next panel
        // Sets the main frame
        selectPanel.setFrame(frame);
        // Sets the backgroundFile of the character select panel
        selectPanel.setBackgroundImage(new ImageIcon(getClass().getResource("/assets/CharacterSelection.jpg")));
        // Setting the path to the font file
        try {
            selectPanel.setFontFile(Paths.get(getClass().getResource("/assets/BreatheFireIii-PKLOB.ttf").toURI()).toFile());
        } catch (URISyntaxException f) {
            f.printStackTrace();
        }
        // Creates the elements inside the panel
        selectPanel.createPanel();

        // Sets the start panel to not be visible
        panel.setVisible(false);

        // Adds the character select panel to the frame
        frame.add(selectPanel);

        // Show a option pane telling the player what to do
        JOptionPane.showMessageDialog(frame, "Player 1: Choose 3 characters by clicking on the Character's image");
    }
    
}
