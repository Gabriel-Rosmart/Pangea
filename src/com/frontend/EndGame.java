package com.frontend;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EndGame {
    public JPanel mainPanel;
    private JLabel endLabel;
    public JButton mainMenuButton;
    public JLabel pointsLabel;
    public JLabel chrLabel;

    public EndGame(){
        mainPanel.setBackground(new Color(10, 10, 23));
        try {
            FontCreator tmp_font = new FontCreator();
            this.endLabel.setFont(tmp_font.getHeavyData(72));
            this.endLabel.setForeground(tmp_font.getFontColorNormal());
            this.mainMenuButton.setFont(tmp_font.getHeavyData(48));
            this.mainMenuButton.setForeground(tmp_font.getFontColorSelect());
            this.pointsLabel.setFont(tmp_font.getGohuFont(26));
            this.pointsLabel.setForeground(tmp_font.getFontColorNormal());
            this.chrLabel.setFont(tmp_font.getGohuFont(26));
            this.chrLabel.setForeground(tmp_font.getFontColorNormal());

        } catch (IOException e) {
            System.out.println("Something went wrong reading files, check if all necessary files exists");
            e.printStackTrace();
        } catch (FontFormatException e) {
            System.out.println("Something went wrong with fonts");
            e.printStackTrace();
        }
    }
}
