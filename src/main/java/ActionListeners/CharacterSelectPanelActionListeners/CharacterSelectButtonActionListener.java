package ActionListeners.CharacterSelectPanelActionListeners;

// Import required packages
import javax.swing.*;

import GameData.Legends;
import GamePanels.CharacterSelectPanel;
import MainGameFrame.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

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
    private ArrayList<String> legendType;

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
            HashMap<String,ArrayList<Legends>> legendsMap, ArrayList<String> legendType) {
        this.currentDisplayed = currentDisplayed;
        this.index = index;
        this.player1 = player1;
        this.player2 = player2;
        this.frame = frame;
        this.hasPopupOpened = hasPopupOpened;
        this.currentPanel = currentPanel;
        this.legendsMap = legendsMap;
        this.legendType = legendType;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (player1.size() < 3) {
            if (!player1.contains(currentDisplayed[index])) {
                player1.add(currentDisplayed[index]);
            } else {
                JOptionPane.showMessageDialog(frame, "You cannot select the same legend twice!");
            }

            if (player1.size() < 3) {
                return;
            }
        }

        if (player1.size() == 3 && !hasPopupOpened[0]) {
            response = JOptionPane.showConfirmDialog(frame, "Player 1 has selected " + 
                player1.get(0).getName() + ", " + player1.get(1).getName() + ", " + player1.get(2).getName() + ".",
                "Confirm", JOptionPane.YES_NO_OPTION);
            hasPopupOpened[0] = true;

            if (response == JOptionPane.NO_OPTION) {
                CharacterSelectPanel selectPanel = new CharacterSelectPanel();

                // Initializes the settings of the next panel
                // Sets the main frame
                selectPanel.setFrame(frame);
                // Sets the backgroundFile of the character select panel
                try {
                    selectPanel.setBackgroundFile(Paths.get(getClass().getResource("/assets/CharacterSelection.jpg").toURI()).toFile());
                } catch (URISyntaxException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                // Sets the font file of the character select panel
                try {
                    selectPanel.setFontFile(Paths.get(getClass().getResource("/assets/BreatheFireIii-PKLOB.ttf").toURI()).toFile());
                } catch (URISyntaxException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                // Creates the elements inside the panel
                selectPanel.createPanel();

                // Sets the start panel to not be visible
                currentPanel.setVisible(false);

                // Adds the character select panel to the frame
                frame.add(selectPanel);

                JOptionPane.showMessageDialog(frame, "Player 1: Choose 3 characters by clicking on the Character's image");
            } else {
                JOptionPane.showMessageDialog(frame, "Player 2: Choose 3 characters by clicking on the character's image");
            }

            return;
        }

        if (player2.size() < 3) {
            if (!player1.contains(currentDisplayed[index]) && !player2.contains(currentDisplayed[index])) {
                player2.add(currentDisplayed[index]);
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
                CharacterSelectPanel selectPanel = new CharacterSelectPanel(player1, hasPopupOpened, legendsMap, legendType);

                // Initializes the settings of the next panel
                // Sets the main frame
                selectPanel.setFrame(frame);
                // Sets the backgroundFile of the character select panel
                try {
                    selectPanel.setBackgroundFile(Paths.get(getClass().getResource("/assets/CharacterSelection.jpg").toURI()).toFile());
                } catch (URISyntaxException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                // Sets the font file of the character select panel
                try {
                    selectPanel.setFontFile(Paths.get(getClass().getResource("/assets/BreatheFireIii-PKLOB.ttf").toURI()).toFile());
                } catch (URISyntaxException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                // Creates the elements inside the panel
                selectPanel.createPanel();

                // Sets the start panel to not be visible
                currentPanel.setVisible(false);

                // Adds the character select panel to the frame
                frame.add(selectPanel);

                JOptionPane.showMessageDialog(frame, "Player 2: Choose 3 characters by clicking on the Character's image");
            }

            return;
        }

    }
}
