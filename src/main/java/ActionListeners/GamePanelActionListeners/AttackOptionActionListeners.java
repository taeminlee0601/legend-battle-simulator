package ActionListeners.GamePanelActionListeners;

// Import required packages
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GameData.QueuedMove;
import GamePanels.GamePanel;

/*
 * This class is the action listener of the moves buttons in the GamePanel
 */
public class AttackOptionActionListeners implements ActionListener {
    // Create necessary instance variables
    private int index;
    private GamePanel currentPanel;

    /**
     * This is the constructor of the attack option action listener
     * Preconditions: Takes in the index (which move) and currentPanel (GamePanel)
     * @param index - int
     * @param currentPanel - GamePanel
     */
    public AttackOptionActionListeners(int index, GamePanel currentPanel) {
        this.index = index;
        this.currentPanel = currentPanel;
    }

    /**
     * This method will add a QueuedMove object to the moveQueue arraylist
     * Preconditions: Button needs to be clicked
     * Postconditions: Adds a QueuedMove to the moveQueue arraylist
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Adds a new QueuedMove object to the moveQueu action listener
        currentPanel.getMoveQueue().add(new QueuedMove(1, index));

        changeButtons();

        currentPanel.changeTurn();

        // If the turn is back to player 1, initiate the moves
        // If not, state that player 2 is choosing
        if (currentPanel.getTurn()[0] == 1) {
            currentPanel.initiateMoves();
        } else {
            currentPanel.getPlayerChoosingLabel().setText("Player 2 is Choosing...");
        }
    }
    
    public void changeButtons() {
        // Set the basic buttons (attack, buff, swap) to visible
        currentPanel.getAttackButton().setVisible(true);
        currentPanel.getSwapButton().setVisible(true);
        currentPanel.getBuffButton().setVisible(true);

        // Set the move buttons to not visibile
        currentPanel.getMove1Button().setVisible(false);
        currentPanel.getMove2Button().setVisible(false);
        currentPanel.getMove3Button().setVisible(false);
    }
}
