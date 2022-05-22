package com.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class LoadGame {
    private JLabel titleLabel;
    public JButton newGameButton;
    public JButton loadButton;
    public JPanel mainPanel;
    public JButton exitButton;

    public LoadGame(){
        mainPanel.setBackground(new Color(10, 10, 23));
        try {
            FontCreator tmp_font = new FontCreator();
            this.newGameButton.setFont(tmp_font.getHeavyData(36));
            this.loadButton.setFont(tmp_font.getHeavyData(36));
            this.exitButton.setFont(tmp_font.getHeavyData(36));

            this.newGameButton.setForeground(tmp_font.getFontColorNormal());
            this.loadButton.setForeground(tmp_font.getFontColorNormal());
            this.exitButton.setForeground(tmp_font.getFontColorNormal());
        } catch (IOException e) {
            System.out.println("Something went wrong reading files, check if all necessary files exists");
            e.printStackTrace();
        } catch (FontFormatException e) {
            System.out.println("Something went wrong with fonts");
            e.printStackTrace();
        }

        /* MOUSE LISTENERS, just for some nice color when hover */

        this.exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setForeground(new Color(255, 0, 161));
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setForeground(new Color(234, 164, 0));
                super.mouseExited(e);
            }
        });

        this.newGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                newGameButton.setForeground(new Color(255, 0, 161));
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newGameButton.setForeground(new Color(234, 164, 0));
                super.mouseExited(e);
            }
        });

        this.loadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loadButton.setForeground(new Color(255, 0, 161));
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loadButton.setForeground(new Color(234, 164, 0));
                super.mouseExited(e);
            }
        });
    }
}
