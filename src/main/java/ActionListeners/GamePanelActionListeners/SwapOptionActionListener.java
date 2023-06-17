package ActionListeners.GamePanelActionListeners;

// Import required packages
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import GameData.Legends;
import GameData.QueuedMove;
import GamePanels.GamePanel;

/*
 * This class is the action listener to the swap option buttons
 */
public class SwapOptionActionListener implements ActionListener {
    // Create the instance variables
    private GamePanel currentPanel;
    private int[] turn;
    private ArrayList<Legends> player;
    private int index;
    private int playerNum;

    /**
     * This is the constructor of swap option action listener
     * Preconditions: Takes in the currentPanel (the panel) and the index (which character to change to)
     * Postconditions: Sets the instance variables with the corresponding parameters
     * @param currentPanel - GamePanel
     * @param index - int
     */
    public SwapOptionActionListener(GamePanel currentPanel, int index) {
        this.currentPanel = currentPanel;
        this.turn = currentPanel.getTurn();
        this.index = index;
    }

    /**
     * This method checks if the swapping can occur and adds a QueuedMove to the moveQueue arraylist
     * Precondition: Button is clicked
     * Postcondition: Adds a QueuedMove to the moveQueue arraylist
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Gets the information of the whose turn it is
        if (turn[0] > 0) {
            player = currentPanel.getPlayer1();
            playerNum = 1;
        } else {
            player = currentPanel.getPlayer2();
            playerNum = 2;
        }

        // Get the legend to swap to and the current legend
        Legends swapping = player.get(index);
        Legends current = player.get(0);
        
        // Check if the legend is alive
        // If not, the player cannot swap to that character
        if (!swapping.isAlive()) {
            currentPanel.getTextLabel().setText("Cannot swap to that legend! Please choose again, Player " + playerNum + "!");
            changeButtons();
            return;
        }

        // Remove the legend to swap to and the current legend
        player.remove(index);
        player.remove(0); 

        // Add the legend to swap to in the front and the current legend to the back
        player.add(0, swapping);
        player.add(current);

        // Add a new QueuedMove object to the moveQueue arraylist
        currentPanel.getMoveQueue().add(new QueuedMove(3));

        // Change the current character of the player
        currentPanel.setCurrentCharacter(swapping, playerNum);
        
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
    
    /**
     * This method changes the visibilitity of the buttons
     */
    public void changeButtons() {
        currentPanel.getAttackButton().setVisible(true);
        currentPanel.getSwapButton().setVisible(true);
        currentPanel.getBuffButton().setVisible(true);

        currentPanel.getSwapCharacter1().setVisible(false);
        currentPanel.getSwapCharacter2().setVisible(false);
    }
}
