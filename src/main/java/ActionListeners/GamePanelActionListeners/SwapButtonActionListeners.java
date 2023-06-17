package ActionListeners.GamePanelActionListeners;

// Import required packages
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import GameData.Legends;
import GamePanels.GamePanel;

/*
 * This class is the action listeners of the swap button
 */
public class SwapButtonActionListeners implements ActionListener {
    // Create necessary instance variables
    private ArrayList<Legends> player;
    private GamePanel currentPanel;
    private JButton option1;
    private JButton option2;
    private JButton attackButton;
    private JButton swapButton;
    private JButton buffButton;
    private int[] turn;

    /**
     * This is the constructor for the swap action listeners
     * Preconditions: Takes in the currentPanel (GamePanel)
     * Postconditions: Sets the parameters with the corresponding instance variables
     * @param currentPanel - GamePanel
     */
    public SwapButtonActionListeners(GamePanel currentPanel) {
        this.currentPanel = currentPanel;
        this.attackButton = currentPanel.getAttackButton();
        this.swapButton = currentPanel.getSwapButton();
        this.buffButton = currentPanel.getBuffButton();
        this.turn = currentPanel.getTurn();
        this.option1 = currentPanel.getSwapCharacter1();
        this.option2 = currentPanel.getSwapCharacter2();
    }

    /**
     * This method shows the options of who the player can swap to
     * Preconditions: Button is clicked
     * Postconditions: Sets the visibility of the options to true 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Gets the arraylist of legends based on whose turn it is
        if (turn[0] > 0) {
            this.player = currentPanel.getPlayer1();
        } else {
            this.player = currentPanel.getPlayer2();
        }

        // Sets the text of who to swap to and sets the visibility to true
        option1.setText("Swap to " + player.get(1).getName());
        option1.setVisible(true);

        option2.setText("Swap to " + player.get(2).getName());
        option2.setVisible(true);

        // Sets the visibility of the basic buttons (attack, buff, swap) to false
        attackButton.setVisible(false);
        swapButton.setVisible(false);
        buffButton.setVisible(false);
    }
    
}
