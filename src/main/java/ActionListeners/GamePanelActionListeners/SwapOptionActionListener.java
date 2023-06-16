package ActionListeners.GamePanelActionListeners;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import GameData.Legends;
import GameData.QueuedMove;
import GamePanels.GamePanel;
import MainGameFrame.FileFunctions;

public class SwapOptionActionListener implements ActionListener {
    private GamePanel currentPanel;
    private int[] turn;
    private ArrayList<Legends> player;
    private Image playerImage;
    private int index;
    private int playerNum;

    public SwapOptionActionListener(GamePanel currentPanel, int index) {
        this.currentPanel = currentPanel;
        this.turn = currentPanel.getTurn();
        this.index = index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (turn[0] > 0) {
            player = currentPanel.getPlayer1();
            playerNum = 1;
        } else {
            player = currentPanel.getPlayer2();
            playerNum = 2;
        }

        Legends swapping = player.get(index);
        Legends current = player.get(0);
        
        if (!swapping.isAlive()) {
            currentPanel.getTextLabel().setText("Cannot swap to that legend! Please choose again, Player " + playerNum + "!");
            changeButtons();
            return;
        }

        player.remove(index);
        player.remove(0); 
        
        player.add(0, swapping);
        player.add(current);

        currentPanel.getMoveQueue().add(new QueuedMove(3));

        currentPanel.setCurrentCharacter(swapping, playerNum);
        
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

        currentPanel.getSwapCharacter1().setVisible(false);
        currentPanel.getSwapCharacter2().setVisible(false);
    }
}
