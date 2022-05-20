package com.frontend;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePlay extends JPanel{
    public JPanel mainPanel;
    public JButton leftChoiceButton;
    public JButton rigthChoiceButton;
    public JLabel plotLabel;
    public JLabel iconLabel;

    public GamePlay(){
        mainPanel.setBackground(new Color(10, 10, 23));
        try {
            FontCreator tmp_font = new FontCreator();
            this.plotLabel.setFont(tmp_font.getCaskaydiaCove(26));
            this.leftChoiceButton.setFont(tmp_font.getHeavyData(36));
            this.rigthChoiceButton.setFont(tmp_font.getHeavyData(36));

            this.rigthChoiceButton.setForeground(tmp_font.getFontColorNormal());
            this.leftChoiceButton.setForeground(tmp_font.getFontColorNormal());
            this.plotLabel.setForeground(tmp_font.getFontColorSelect());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }
}
