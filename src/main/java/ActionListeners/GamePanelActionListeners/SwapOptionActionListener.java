package ActionListeners.GamePanelActionListeners;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import GameData.Legends;
import GamePanels.GamePanel;
import MainGameFrame.FileFunctions;

public class SwapOptionActionListener implements ActionListener {
    private GamePanel currentPanel;
    private int[] turn;
    private ArrayList<Legends> player;
    private Image playerImage;
    private int index;

    public SwapOptionActionListener(GamePanel currentPanel, int index) {
        this.currentPanel = currentPanel;
        this.turn = currentPanel.getTurn();
        this.index = index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (turn[0] > 0) {
            this.player = currentPanel.getPlayer1();
        } else {
            this.player = currentPanel.getPlayer2();
        }

        currentPanel.setPlayerImage(FileFunctions.resizeImage(player.get(index).getImageFile(), 200, 250));

        Legends swapping = player.remove(index);
        Legends current = player.remove(0);
        
        player.add(0, swapping);
        player.add(current);

        currentPanel.getAttackButton().setVisible(true);
        currentPanel.getSwapButton().setVisible(true);
        currentPanel.getBuffButton().setVisible(true);

        currentPanel.getSwapCharacter1().setVisible(false);;
        currentPanel.getSwapCharacter2().setVisible(false);;
        
        currentPanel.changeTurn();

        currentPanel.repaint();
    }
    
}
