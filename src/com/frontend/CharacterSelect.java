package com.frontend;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CharacterSelect extends JPanel{
    public JPanel mainPanel;
    public JRadioButton radioButtonWizard;
    public JRadioButton radioButtonArcher;
    public JRadioButton radioButtonDwarf;
    public JButton buttonSelection;
    public JRadioButton radioButtonKnight;
    private JLabel titleLabel;
    private JLabel wizardIcon;
    private JLabel archerIcon;
    private JLabel dwarfIcon;
    private JLabel knightIcon;

    public CharacterSelect(){
        mainPanel.setBackground(new Color(10, 10, 23));
        try {
            FontCreator tmp_font = new FontCreator();
            Font heavyDataFont = tmp_font.getHeavyData(48);

            this.buttonSelection.setFont(heavyDataFont);
            this.radioButtonWizard.setFont(heavyDataFont);
            this.radioButtonDwarf.setFont(heavyDataFont);
            this.radioButtonKnight.setFont(heavyDataFont);
            this.radioButtonArcher.setFont(heavyDataFont);
            this.titleLabel.setFont(tmp_font.getHeavyData(72));

            Color font_color_normal = tmp_font.getFontColorNormal();
            Color font_color_selected = tmp_font.getFontColorSelect();

            this.buttonSelection.setForeground(font_color_selected);
            this.radioButtonArcher.setForeground(font_color_normal);
            this.radioButtonKnight.setForeground(font_color_normal);
            this.radioButtonDwarf.setForeground(font_color_normal);
            this.radioButtonWizard.setForeground(font_color_normal);
            this.titleLabel.setForeground(font_color_normal);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }
}
