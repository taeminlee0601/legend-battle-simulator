package ActionListeners.CharacterSelectPanelActionListeners;

// Import required packages
import javax.swing.*;
import java.awt.*;
import GameData.Legends;
import GamePanels.CharacterSelectPanel;
import MainGameFrame.FileFunctions;
import MainGameFrame.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import GamePanels.GamePanel;

/*
 * This class will set the characters that the player selects into an 
 */
public class CharacterSelectButtonActionListener implements ActionListener {
    private Legends[] currentDisplayed;
    private int index;
    private ArrayList<Legends> player1;
    private ArrayList<Legends> player2;
    private GameFrame frame;
    public boolean[] hasPopupOpened;
    private int response = 0;
    private CharacterSelectPanel currentPanel;
    private HashMap<String, ArrayList<Legends>> legendsMap;
    private ArrayList<String> legendsType;
    private ArrayList<Image> player1Image;
    private ArrayList<Image> player2Image;

    /**
     * Sets the instance variables of the CharacterSelectButtonActionListener class
     * Preconditions: Takes in the parameters and sets them to the corresponding varaibles
     * @param currentDisplayed
     * @param index
     * @param player1
     * @param player2
     * @param frame
     * @param hasPopupOpened
     * @param currentPanel
     * @param legendsMap
     * @param legendType
     */
    public CharacterSelectButtonActionListener(Legends[] currentDisplayed, int index, ArrayList<Legends> player1, 
            ArrayList<Legends> player2, GameFrame frame, boolean[] hasPopupOpened, CharacterSelectPanel currentPanel,
            HashMap<String,ArrayList<Legends>> legendsMap, ArrayList<String> legendType, ArrayList<Image> player1Image,
            ArrayList<Image> player2Image) {
        this.currentDisplayed = currentDisplayed;
        this.index = index;
        this.player1 = player1;
        this.player2 = player2;
        this.frame = frame;
        this.hasPopupOpened = hasPopupOpened;
        this.currentPanel = currentPanel;
        this.legendsMap = legendsMap;
        this.player1Image = player1Image;
        this.player2Image = player2Image;
        this.legendsType = legendType;
    }

    /**
     * This method will check if the button is pressed and then add the legend pressed to the arraylist of the player that 
     * is choosing.
     * Precondition: Button must be clicked
     * Postconditions: Adds the legend into the arraylist or restarts process
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Checks is the length of arraylist is less than 3
        if (player1.size() < 3) {
            // Checks if the legend is contained in the arraylist
            // If not, add the legend to the arraylist, add the legend face file to the array, and reload the panel
            // If yes, show a warning that says that you cannot select the same legend 
            if (!player1.contains(currentDisplayed[index])) {

                player1.add(currentDisplayed[index]);

                Image faceImage = FileFunctions.resizeImage(currentDisplayed[index].getFaceImageFile(), 50, 50);

                player1Image.add(faceImage);

                currentPanel.repaint();

            } else {
                JOptionPane.showMessageDialog(frame, "You cannot select the same legend twice!");
            }

            // End method if the length of the arraylist is less than 3
            if (player1.size() < 3) {
                return;
            }
        }

        // If the arraylist contains 3 elements and if the popup has not opened
        if (player1.size() == 3 && !hasPopupOpened[0]) {
            // Show a popup that confirms the person's choice
            response = JOptionPane.showConfirmDialog(frame, "Player 1 has selected " + 
                player1.get(0).getName() + ", " + player1.get(1).getName() + ", " + player1.get(2).getName() + ".",
                "Confirm", JOptionPane.YES_NO_OPTION);
            hasPopupOpened[0] = true;

            // If they choose no, reset the choosing process by creating a new panel
            if (response == JOptionPane.NO_OPTION) {
                CharacterSelectPanel selectPanel = new CharacterSelectPanel();

                // Initializes the settings of the next panel
                // Sets the main frame
                selectPanel.setFrame(frame);
                // Sets the backgroundFile of the character select panel
                // Sets the font file of the character select panel
                try {
                    selectPanel.setBackgroundImage(new ImageIcon(getClass().getResource("/assets/CharacterSelection.jpg")));
                    selectPanel.setFontFile(Paths.get(getClass().getResource("/assets/BreatheFireIii-PKLOB.ttf").toURI()).toFile());
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
                
                // Creates the elements inside the panel
                selectPanel.createPanel();

                // Sets the start panel to not be visible
                currentPanel.setVisible(false);

                // Adds the character select panel to the frame
                frame.add(selectPanel);

                // Show the instructions for player 1 on the screen
                JOptionPane.showMessageDialog(frame, "Player 1: Choose 3 characters by clicking on the Character's image");
            // If they select 1, show instruction for player 2
            } else {
                JOptionPane.showMessageDialog(frame, "Player 2: Choose 3 characters by clicking on the character's image");
            }

            // End the method if this line is reached
            return;
        }

        /*
         * The following code has the same process as the code above but for player 2 (refer to above)
         */

        if (player2.size() < 3) {
            if (!player1.contains(currentDisplayed[index]) && !player2.contains(currentDisplayed[index])) {
                player2.add(currentDisplayed[index]);

                Image faceImage = FileFunctions.resizeImage(currentDisplayed[index].getFaceImageFile(), 50, 50);

                player2Image.add(faceImage);

                currentPanel.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "You cannot select the same legend twice!");
            }
        }

        if (player2.size() == 3) {
            response = JOptionPane.showConfirmDialog(frame, "Player 2 has selected " + 
                player2.get(0).getName() + ", " + player2.get(1).getName() + ", " + player2.get(2).getName() + ".",
                "Confirm", JOptionPane.YES_NO_OPTION);
            hasPopupOpened[0] = true;

            if (response == JOptionPane.NO_OPTION) {
                // Create an character select panel object that keeps the choices of player 1
                CharacterSelectPanel selectPanel = new CharacterSelectPanel(player1, hasPopupOpened, legendsMap, legendsType, player1Image);

                // Initializes the settings of the next panel
                // Sets the main frame
                selectPanel.setFrame(frame);
                // Sets the backgroundFile of the character select panel
                // Sets the font file of the character select panel
                try {
                    selectPanel.setBackgroundImage(new ImageIcon(getClass().getResource("/assets/CharacterSelection.jpg")));
                    selectPanel.setFontFile(Paths.get(getClass().getResource("/assets/BreatheFireIii-PKLOB.ttf").toURI()).toFile());
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
                
                // Creates the elements inside the panel
                selectPanel.createPanel();

                // Sets the start panel to not be visible
                currentPanel.setVisible(false);

                // Adds the character select panel to the frame
                frame.add(selectPanel);

                JOptionPane.showMessageDialog(frame, "Player 2: Choose 3 characters by clicking on the Character's image");
            // Go to the next panel (game panel)
            } else {
                // Create a new GamePanel takes in the player1 choices and player2 choices
                GamePanel nextPanel = new GamePanel(player1, player2);
                // Add the frame to the next panel
                nextPanel.setFrame(frame);
                
                // Add the font file to the next panel
                try {
                    nextPanel.setFontFile(Paths.get(getClass().getResource("/assets/BreatheFireIii-PKLOB.ttf").toURI()).toFile());
                } catch (URISyntaxException f) {
                    f.printStackTrace();
                }

                // Add the elements in the next panel
                nextPanel.createPanel();
                // Set the frame to be not visible
                currentPanel.setVisible(false);
                // Add the next panel to the frame
                frame.add(nextPanel);
            }

            // If this is reached, end the method
            return;
        }

    }
}
