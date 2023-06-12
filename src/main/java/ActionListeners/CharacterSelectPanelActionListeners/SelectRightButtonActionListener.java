package ActionListeners.CharacterSelectPanelActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import GameData.Legends;
import MainGameFrame.FileFunctions;

public class SelectRightButtonActionListener implements ActionListener {
    private JButton[] buttonArray;
    private ArrayList<String> legendType;
    private HashMap<String, ArrayList<Legends>> legendsMap;
    private JLabel typeTitle;
    private JLabel[] nameArray;
    private Legends[] currentDisplayed;

    public SelectRightButtonActionListener(JButton[] buttonArray, ArrayList<String> legendType, HashMap<String,ArrayList<Legends>> legendsMap, JLabel typeTitle, JLabel[] nameArray, Legends[] currentDisplayed) {
        this.buttonArray = buttonArray;
        this.legendType = legendType;
        this.legendsMap = legendsMap;
        this.typeTitle = typeTitle;
        this.nameArray = nameArray;
        this.currentDisplayed = currentDisplayed;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String currentType = legendType.remove(legendType.size()-1);

        typeTitle.setText(currentType);

        for (int a = 0; a < buttonArray.length; a++) {
            currentDisplayed[a] = legendsMap.get(currentType).get(a);
            buttonArray[a].setIcon(new ImageIcon(FileFunctions.resizeImage(currentDisplayed[a].getImageFile(),228, 275)));
            nameArray[a].setText(currentDisplayed[a].getName());
        }

        legendType.add(0, currentType);
    }
}
