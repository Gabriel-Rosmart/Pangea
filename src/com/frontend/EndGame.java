package com.frontend;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EndGame {
    public JPanel mainPanel;
    private JLabel endLabel;

    public EndGame(){
        mainPanel.setBackground(new Color(10, 10, 23));
        try {
            FontCreator tmp_font = new FontCreator();
            this.endLabel.setFont(tmp_font.getHeavyData(72));
            this.endLabel.setForeground(tmp_font.getFontColorNormal());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }
}
