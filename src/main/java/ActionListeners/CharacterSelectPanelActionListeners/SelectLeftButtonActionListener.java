package ActionListeners.CharacterSelectPanelActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import GameData.Legends;
import MainGameFrame.FileFunctions;

public class SelectLeftButtonActionListener implements ActionListener{
    private JButton[] buttonArray;
    private ArrayList<String> legendType;
    private HashMap<String, ArrayList<Legends>> legendsMap;
    private JLabel typeTitle;
    private JLabel[] nameArray;
    private Legends[] currentDisplayed;
    private JLabel[] descriptionArray;

    public SelectLeftButtonActionListener(JButton[] buttonArray, ArrayList<String> legendType, 
            HashMap<String,ArrayList<Legends>> legendsMap, JLabel typeTitle, JLabel[] nameArray, Legends[] currentDisplayed,
            JLabel[] descriptionArray) {
        this.buttonArray = buttonArray;
        this.legendType = legendType;
        this.legendsMap = legendsMap;
        this.typeTitle = typeTitle;
        this.nameArray = nameArray;
        this.currentDisplayed = currentDisplayed;
        this.descriptionArray = descriptionArray;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String currentType = legendType.remove(0);

        typeTitle.setText(currentType);

        for (int a = 0; a < buttonArray.length; a++) {
            currentDisplayed[a] = legendsMap.get(currentType).get(a);
            buttonArray[a].setIcon(new ImageIcon(FileFunctions.resizeImage(currentDisplayed[a].getImageFile(),228, 275)));
            nameArray[a].setText(currentDisplayed[a].getName());
            descriptionArray[a].setText(setDescription(currentDisplayed[a].getDescription()));
        }

        legendType.add(currentType);
    }

    public String setDescription(String text) {
        return "<html>" + text + "</html>";
    }
}
