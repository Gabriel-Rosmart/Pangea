package com.app;

import com.backend.GameEngine;
import com.backend.Result;
import com.frontend.CharacterSelect;
import com.frontend.EndGame;
import com.frontend.GamePlay;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class GameRunner {
    private GameEngine gameEngine;
    private CharacterSelect characterSelect;
    private GamePlay gamePlay;
    private EndGame endGame;
    private JFrame mainFrame;

    private ImageIcon img;

    public GameRunner() throws SQLException, IOException {
        this.gameEngine = new GameEngine();
        this.characterSelect = new CharacterSelect();
        this.gamePlay = new GamePlay();
        this.endGame = new EndGame();
        this.mainFrame = new JFrame();
        this.img = new ImageIcon("Icons" + File.separator + "pangea.png");
    }

    public void load(){
        this.mainFrame.setIconImage(img.getImage());
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.getContentPane().add(characterSelect.mainPanel);
        this.mainFrame.getContentPane().add(gamePlay.mainPanel);
        this.mainFrame.setContentPane(characterSelect.mainPanel);

        this.characterSelect.buttonSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainFrame.setContentPane(gamePlay.mainPanel);
                mainFrame.invalidate();
                mainFrame.validate();
                mainFrame.repaint();

                try {
                    if(characterSelect.radioButtonWizard.isSelected()){
                        gameEngine.chooseMainCharacter("wizard.json");
                    }
                    else if(characterSelect.radioButtonArcher.isSelected()){
                        gameEngine.chooseMainCharacter("archer.json");
                    }
                    else if(characterSelect.radioButtonDwarf.isSelected()){
                        gameEngine.chooseMainCharacter("dwarf.json");
                    }
                    else{
                        gameEngine.chooseMainCharacter("knight.json");
                    }
                    gameEngine.init(gamePlay.plotLabel, gamePlay.leftChoiceButton, gamePlay.rigthChoiceButton, gamePlay.iconLabel);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        this.gamePlay.leftChoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if(gameEngine.goLeft(gamePlay.plotLabel, gamePlay.leftChoiceButton, gamePlay.rigthChoiceButton, gamePlay.iconLabel) == Result.ERR){
                        mainFrame.setContentPane(endGame.mainPanel);
                        mainFrame.invalidate();
                        mainFrame.validate();
                        mainFrame.repaint();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        this.gamePlay.rigthChoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if(gameEngine.goRigth(gamePlay.plotLabel, gamePlay.leftChoiceButton, gamePlay.rigthChoiceButton, gamePlay.iconLabel) == Result.ERR){
                        mainFrame.setContentPane(endGame.mainPanel);
                        mainFrame.invalidate();
                        mainFrame.validate();
                        mainFrame.repaint();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void run(){
        MusicPlayer.play();
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }
}
