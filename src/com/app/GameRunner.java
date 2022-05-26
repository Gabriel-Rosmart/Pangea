package com.app;

import com.backend.GameEngine;
import com.backend.Result;
import com.backend.SaveGameSystem;
import com.frontend.CharacterSelect;
import com.frontend.EndGame;
import com.frontend.GamePlay;
import com.frontend.LoadGame;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class GameRunner {
    private final GameEngine gameEngine;
    private final LoadGame loadGame;
    private final CharacterSelect characterSelect;
    private final GamePlay gamePlay;
    private final EndGame endGame;
    private final JFrame mainFrame;

    private final ImageIcon img;

    public GameRunner() throws SQLException, IOException {
        this.gameEngine = new GameEngine();
        this.loadGame = new LoadGame();
        this.characterSelect = new CharacterSelect();
        this.gamePlay = new GamePlay();
        this.endGame = new EndGame();
        this.mainFrame = new JFrame();
        this.img = new ImageIcon("Icons" + File.separator + "pangea.png");
    }

    public void load(){
        this.mainFrame.setIconImage(img.getImage());
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.getContentPane().add(loadGame.mainPanel);
        this.mainFrame.getContentPane().add(characterSelect.mainPanel);
        this.mainFrame.getContentPane().add(gamePlay.mainPanel);
        this.mainFrame.setContentPane(loadGame.mainPanel);

        /* Always check if load game button has to be disabled at startup */
        disableLoadButton();

        /* ACTION LISTENERS */

        /* Load saved game when load game button is pressed, the go to the game play panel */
        this.loadGame.loadButton.addActionListener(actionEvent -> {
            try {
                gameEngine.loadSavedGame();
                mainFrame.setContentPane(gamePlay.mainPanel);
                mainFrame.invalidate();
                mainFrame.validate();
                mainFrame.repaint();
                gameEngine.init(gamePlay.plotLabel, gamePlay.leftChoiceButton, gamePlay.rigthChoiceButton, gamePlay.iconLabel);
            } catch (IOException e) {
                alertUser("Something went wrong when reading files, check if all necessary files exists");
                e.printStackTrace();
            } catch (SQLException e) {
                alertUser("Something went wrong with the database, check your configuration file");
                e.printStackTrace();
            }
        });

        /* Delete old saved game if exists, the go to the character select pane */
        this.loadGame.newGameButton.addActionListener(actionEvent -> {
            mainFrame.setContentPane(characterSelect.mainPanel);
            mainFrame.invalidate();
            mainFrame.validate();
            mainFrame.repaint();
            try {
                gameEngine.reset();
                gameEngine.init(gamePlay.plotLabel, gamePlay.leftChoiceButton, gamePlay.rigthChoiceButton, gamePlay.iconLabel);
            } catch (SQLException e) {
                alertUser("Something went wrong with the database, check your configuration file");
                e.printStackTrace();
            }
        });

        /* Exists the application when pressed */
        this.loadGame.exitButton.addActionListener(actionEvent -> System.exit(0));

        /* Select a character, if the files doesn't exist the game will break, even with the try/catch clause */
        this.characterSelect.buttonSelection.addActionListener(actionEvent -> {
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
                alertUser("Something went wrong when reading files, check if all necessary files exists");
                e.printStackTrace();
            } catch (SQLException e) {
                alertUser("Something went wrong with the database, check data integrity in database");
                e.printStackTrace();
            }
        });

        /* Goes to the first option in the story when pressed */
        this.gamePlay.leftChoiceButton.addActionListener(actionEvent -> {
            try {
                if(gameEngine.goLeft(gamePlay.plotLabel, gamePlay.leftChoiceButton, gamePlay.rigthChoiceButton, gamePlay.iconLabel) == Result.ERR){
                    drawRecordLabels();
                    //gameEngine.reset();
                    mainFrame.setContentPane(endGame.mainPanel);
                    mainFrame.invalidate();
                    mainFrame.validate();
                    mainFrame.repaint();
                }
            } catch (SQLException e) {
                alertUser("Something went wrong when reading files, check if all necessary files exists");
                e.printStackTrace();
            } catch (IOException e) {
                alertUser("Something went wrong with the database, check data integrity in database");
                e.printStackTrace();
            }
        });

        /* Goes the second option of the story when pressed */
        this.gamePlay.rigthChoiceButton.addActionListener(actionEvent -> {
            try {
                if(gameEngine.goRigth(gamePlay.plotLabel, gamePlay.leftChoiceButton, gamePlay.rigthChoiceButton, gamePlay.iconLabel) == Result.ERR){
                    drawRecordLabels();
                    //gameEngine.reset();
                    mainFrame.setContentPane(endGame.mainPanel);
                    mainFrame.invalidate();
                    mainFrame.validate();
                    mainFrame.repaint();
                }
            } catch (SQLException e) {
                alertUser("Something went wrong when reading files, check if all necessary files exists");
                e.printStackTrace();
            } catch (IOException e) {
                alertUser("Something went wrong with the database, check data integrity in database");
                e.printStackTrace();
            }
        });

        /* Saves the game when pressed */
        this.gamePlay.saveButton.addActionListener(actionEvent -> saveGame());

        /* Goes to the main menu when pressed */
        this.gamePlay.menuButton.addActionListener(actionEvent -> {
            disableLoadButton();
            mainFrame.setContentPane(loadGame.mainPanel);
            mainFrame.invalidate();
            mainFrame.validate();
            mainFrame.repaint();
        });

        /* Goes to the main menu when pressed and resets the story and disables the load game button */
        this.endGame.mainMenuButton.addActionListener(actionEvent -> {
            try {
                gameEngine.addRecord(endGame.getUsername(), gameEngine.getCurrentCharacter(), gameEngine.getRecordPoints());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            gameEngine.reset();
            mainFrame.setContentPane(loadGame.mainPanel);
            mainFrame.invalidate();
            mainFrame.validate();
            mainFrame.repaint();
            disableLoadButton();
        });
    }

    /* Just run the application */
    public void run(){
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }

    /* Private functions */

    /* Disables the load game button if there's no saved game */
    private void disableLoadButton(){
        try {
            loadGame.loadButton.setEnabled(SaveGameSystem.getSavedPoint() != 0);
        } catch (IOException e) {
            alertUser("Something went wrong when reading files, check if all necessary files exists");
            e.printStackTrace();
        }
    }

    /* Saves the current progress of the game */
    private void saveGame(){
        try {
            int[] chrStats = gameEngine.getCharacterStats();
            SaveGameSystem.saveGame(gameEngine.getSavedNode(), gameEngine.getCurrentCharacter(), chrStats[0], chrStats[1], chrStats[2], gameEngine.getRecordPoints());
        } catch (IOException e) {
            alertUser("Something went wrong when reading files, check if all necessary files exists");
            e.printStackTrace();
        }
    }

    /* Draws the points the user got, in the end game panel */
    private void drawRecordLabels(){
        endGame.pointsLabel.setText("Points: " + gameEngine.getRecordPoints());
        endGame.chrLabel.setText("Character: " + gameEngine.getCurrentCharacter());
    }

    /* Shows an alert dialog to the user, the exists with error code 1*/
    private void alertUser(String message){
        JOptionPane.showMessageDialog(null, message);
        System.exit(1);
    }
}
