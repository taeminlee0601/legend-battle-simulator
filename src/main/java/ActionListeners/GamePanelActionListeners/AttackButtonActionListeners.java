package ActionListeners.GamePanelActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.text.DefaultEditorKit.CutAction;

import GameData.Legends;
import GamePanels.GamePanel;

public class AttackButtonActionListeners implements ActionListener{
    private JButton move1Button;
    private JButton move2Button;
    private JButton move3Button;
    private GamePanel currentPanel;
    private JButton attackButton;
    private JButton buffButton;
    private JButton swapButton;
    private Legends current;

    public AttackButtonActionListeners(GamePanel currentPanel) {
        this.currentPanel = currentPanel;
        this.move1Button = currentPanel.getMove1Button();
        this.move2Button = currentPanel.getMove2Button();
        this.move3Button = currentPanel.getMove3Button();
        this.attackButton = currentPanel.getAttackButton();
        this.buffButton = currentPanel.getBuffButton();
        this.swapButton = currentPanel.getSwapButton();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentPanel.getTurn()[0] > 0) {
            current = currentPanel.getPlayer1().get(0);
        } else {
            current = currentPanel.getPlayer2().get(0);
        }

        move1Button.setVisible(true);
        move1Button.setText("Move 1: " + current.getMoveset().get(0).getMoveName());

        move2Button.setVisible(true);
        move2Button.setText("Move 2: " + current.getMoveset().get(1).getMoveName());

        move3Button.setVisible(true);
        move3Button.setText("Move 3: " + current.getMoveset().get(2).getMoveName());

        attackButton.setVisible(false);
        buffButton.setVisible(false);
        swapButton.setVisible(false);
    }
    
}