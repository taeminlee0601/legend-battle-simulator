package ActionListeners.GamePanelActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GameData.QueuedMove;
import GamePanels.GamePanel;

public class BuffOptionActionListeners implements ActionListener {
    private int index;
    private GamePanel currentPanel;

    public BuffOptionActionListeners(int index, GamePanel currentPanel) {
        this.currentPanel = currentPanel;
        this.index = index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentPanel.getMoveQueue().add(new QueuedMove(2, index));
        
        changeButtons();

        currentPanel.changeTurn();

        if (currentPanel.getTurn()[0] > 0) {
            currentPanel.initiateMoves();
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
