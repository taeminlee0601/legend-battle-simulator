package ActionListeners.GamePanelActionListeners;

// Import the required packages
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GameData.QueuedMove;
import GamePanels.GamePanel;

/*
 * This class is the action listener of the buff buttons
 */
public class BuffOptionActionListeners implements ActionListener {
    // Create the required action listeners
    private int index;
    private GamePanel currentPanel;

    /**
     * This is the constructor of the buff option action listeners
     * Preconditions: Takes in the index (buff option) and the currentPanel (GamePanel)
     * Postconditions: Takes the parameters and sets the to the corresponding instance variables
     * @param index - int
     * @param currentPanel - GamePanel
     */
    public BuffOptionActionListeners(int index, GamePanel currentPanel) {
        this.currentPanel = currentPanel;
        this.index = index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (currentPanel.getTurn()[0] > 0 && currentPanel.getCountNumBuffs()[0] >= 5) {
            currentPanel.getTextLabel().setText("Player 1 has no more buffs left! Please choose again, Player 1!");
            changeButtons();
            return;
        } else if (currentPanel.getTurn()[0] < 0 && currentPanel.getCountNumBuffs()[1] >= 5) {
            currentPanel.getTextLabel().setText("Player 2 has no more buffs left! Please choose again, Player 2!");
            changeButtons();
            return;
        }

        currentPanel.getMoveQueue().add(new QueuedMove(2, index));
        
        changeButtons();

        currentPanel.changeTurn();

        if (currentPanel.getTurn()[0] > 0) {
            currentPanel.initiateMoves();
        } else {
            currentPanel.getPlayerChoosingLabel().setText("Player 2 is Choosing...");
        }
    }

    public void changeButtons() {
        currentPanel.getAttackButton().setVisible(true);
        currentPanel.getSwapButton().setVisible(true);
        currentPanel.getBuffButton().setVisible(true);

        currentPanel.getBuffAttButton().setVisible(false);
        currentPanel.getBuffDefButton().setVisible(false);
        currentPanel.getBuffHPButton().setVisible(false);
        currentPanel.getBuffSpeedButton().setVisible(false);
    }
    
}
