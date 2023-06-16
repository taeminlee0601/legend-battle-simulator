package ActionListeners.GamePanelActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.Query;
import javax.swing.JButton;

import GameData.QueuedMove;
import GamePanels.GamePanel;

public class AttackOptionActionListeners implements ActionListener {
    private int index;
    private GamePanel currentPanel;

    public AttackOptionActionListeners(int index, GamePanel currentPanel) {
        this.index = index;
        this.currentPanel = currentPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentPanel.getMoveQueue().add(new QueuedMove(1, index));

        changeButtons();

        currentPanel.changeTurn();

        if (currentPanel.getTurn()[0] == 1) {
            currentPanel.initiateMoves();
        }
    }
    
    public void changeButtons() {
        currentPanel.getAttackButton().setVisible(true);
        currentPanel.getSwapButton().setVisible(true);
        currentPanel.getBuffButton().setVisible(true);

        currentPanel.getMove1Button().setVisible(false);
        currentPanel.getMove2Button().setVisible(false);
        currentPanel.getMove3Button().setVisible(false);
    }
}
