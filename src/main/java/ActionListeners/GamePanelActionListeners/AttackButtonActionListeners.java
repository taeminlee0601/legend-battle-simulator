package ActionListeners.GamePanelActionListeners;

// Import required packages
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GameData.Legends;
import GamePanels.GamePanel;

/* 
 * This class is the action listener for the attack button in the game panel
 */
public class AttackButtonActionListeners implements ActionListener{
    // Create necessary instance variables
    private JButton move1Button;
    private JButton move2Button;
    private JButton move3Button;
    private GamePanel currentPanel;
    private JButton attackButton;
    private JButton buffButton;
    private JButton swapButton;
    private Legends current;

    /**
     * This is the constructor for attack button action listener
     * Preconditions: Takes in the currentpanel 
     * Postconditions: Sets the variables with the instance variables
     * @param currentPanel - Game Panel
     */
    public AttackButtonActionListeners(GamePanel currentPanel) {
        this.currentPanel = currentPanel;
        this.move1Button = currentPanel.getMove1Button();
        this.move2Button = currentPanel.getMove2Button();
        this.move3Button = currentPanel.getMove3Button();
        this.attackButton = currentPanel.getAttackButton();
        this.buffButton = currentPanel.getBuffButton();
        this.swapButton = currentPanel.getSwapButton();
    }

    /**
     * This method will show the moves that can be done when the attack button is clicked
     * Preconditions: Buttons need to be clicked
     * Postconditions: Shows the move buttons
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the corresponding active legend for the turn
        if (currentPanel.getTurn()[0] > 0) {
            current = currentPanel.getPlayer1().get(0);
        } else {
            current = currentPanel.getPlayer2().get(0);
        }

        // Set the move buttons to be visible
        move1Button.setVisible(true);
        move1Button.setText("Move 1: " + current.getMoveset().get(0).getMoveName());

        move2Button.setVisible(true);
        move2Button.setText("Move 2: " + current.getMoveset().get(1).getMoveName());

        move3Button.setVisible(true);
        move3Button.setText("Move 3: " + current.getMoveset().get(2).getMoveName());

        // Set the attack, buff and swap button to be not visible
        attackButton.setVisible(false);
        buffButton.setVisible(false);
        swapButton.setVisible(false);
    }
    
}