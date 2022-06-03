package com.frontend;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class GamePlay extends JPanel{
    public JPanel mainPanel;
    public JButton leftChoiceButton;
    public JButton rigthChoiceButton;
    public JLabel plotLabel;
    public JLabel iconLabel;
    public JButton saveButton;
    public JButton menuButton;

    public JFrame IconFrame;
    public IconView iconPanel;
    private ImageIcon frameIcon;
    private boolean isVisible = false;

    public GamePlay(){
        mainPanel.setBackground(new Color(10, 10, 23));
        try {
            FontCreator tmp_font = new FontCreator();
            this.plotLabel.setFont(tmp_font.getCaskaydiaCove(36));
            this.leftChoiceButton.setFont(tmp_font.getHeavyData(36));
            this.rigthChoiceButton.setFont(tmp_font.getHeavyData(36));
            this.menuButton.setFont(tmp_font.getCaskaydiaCove(26));
            this.saveButton.setFont(tmp_font.getCaskaydiaCove(26));

            this.rigthChoiceButton.setForeground(tmp_font.getFontColorNormal());
            this.leftChoiceButton.setForeground(tmp_font.getFontColorNormal());
            this.plotLabel.setForeground(tmp_font.getFontColorSelect());
            this.saveButton.setForeground(tmp_font.getFontColorNormal());
            this.menuButton.setForeground(tmp_font.getFontColorNormal());

            this.IconFrame = new JFrame("Icon");
            this.frameIcon = new ImageIcon("Icons" + File.separator + "pangea.png");
            this.IconFrame.setIconImage(this.frameIcon.getImage());
            this.iconPanel = new IconView();
            this.IconFrame.setSize(320, 320);
            this.IconFrame.getContentPane().add(iconPanel.mainPanel);
            this.IconFrame.setContentPane(iconPanel.mainPanel);
        } catch (IOException e) {
            System.out.println("Something went wrong reading files, check if all necessary files exists");
            e.printStackTrace();
        } catch (FontFormatException e) {
            System.out.println("Something went wrong with fonts");
            e.printStackTrace();
        }
        plotLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toogleFrame();
                super.mouseClicked(e);
            }
        });
    }

    private void toogleFrame(){
        isVisible = !isVisible;
        IconFrame.setVisible(isVisible);
    }
}
