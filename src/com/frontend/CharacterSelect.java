package com.frontend;

import com.app.MusicPlayer;

import javax.swing.*;
import java.awt.*;

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
    }
}
