package com.frontend;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EndGame {
    public JPanel mainPanel;
    private JLabel endLabel;
    public JButton mainMenuButton;

    public EndGame(){
        mainPanel.setBackground(new Color(10, 10, 23));
        try {
            FontCreator tmp_font = new FontCreator();
            this.endLabel.setFont(tmp_font.getHeavyData(72));
            this.endLabel.setForeground(tmp_font.getFontColorNormal());
            this.mainMenuButton.setFont(tmp_font.getHeavyData(48));
            this.mainMenuButton.setForeground(tmp_font.getFontColorSelect());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }
}
