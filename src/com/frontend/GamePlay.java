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
    public JButton saveButton;
    public JButton menuButton;

    public GamePlay(){
        mainPanel.setBackground(new Color(10, 10, 23));
        try {
            FontCreator tmp_font = new FontCreator();
            this.plotLabel.setFont(tmp_font.getCaskaydiaCove(26));
            this.leftChoiceButton.setFont(tmp_font.getHeavyData(36));
            this.rigthChoiceButton.setFont(tmp_font.getHeavyData(36));
            this.menuButton.setFont(tmp_font.getCaskaydiaCove(26));
            this.saveButton.setFont(tmp_font.getCaskaydiaCove(26));

            this.rigthChoiceButton.setForeground(tmp_font.getFontColorNormal());
            this.leftChoiceButton.setForeground(tmp_font.getFontColorNormal());
            this.plotLabel.setForeground(tmp_font.getFontColorSelect());
            this.saveButton.setForeground(tmp_font.getFontColorNormal());
            this.menuButton.setForeground(tmp_font.getFontColorNormal());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }
}
