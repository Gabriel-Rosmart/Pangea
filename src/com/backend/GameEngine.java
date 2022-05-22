package com.backend;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class GameEngine {
    private final HistoryTree ht;
    private final Connector conn;
    private final CombatSystem cs;
    private final RecordSystem rs;
    private MainCharacter mc;

    public GameEngine() throws IOException, SQLException {
        this.cs = new CombatSystem();
        this.ht = new HistoryTree();
        this.conn = new Connector(ht);
        this.rs = new RecordSystem();
        this.conn.initConnector();
    }

    // Initialize the connection with the database and retrieve first tuple
    public void init(javax.swing.JLabel plot_text, javax.swing.JButton fopt_text, javax.swing.JButton sopt_text, JLabel iconLabel) throws SQLException{
        this.conn.fetchHistory();
        setFieldsText(plot_text, fopt_text, sopt_text, iconLabel);
    }

    // Choose the character, needed in order to the battle nodes to work
    public void chooseMainCharacter(String chrPath) throws IOException{
        this.mc = new MainCharacter(chrPath);
    }

    // Load game
    public void loadSavedGame() throws IOException {
        String[] data = SaveGameSystem.loadGame();
        this.ht.setNextNode(Integer.parseInt(data[0]));
        this.mc = new MainCharacter(data[1] + ".json", Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]));
        this.rs.setPoints(Integer.parseInt(data[5]));
    }

    /* Gets the current character the user is playing with */
    public String getCurrentCharacter(){
        return this.mc.getCharacterType();
    }

    public int getRecordPoints(){ return this.rs.getPoints(); }

    /* Gets the stats of the character */
    public int[] getCharacterStats(){
        int[] stats = new int[3];
        stats[0] = this.mc.getHp();
        stats[1] = this.mc.getDamage();
        stats[2] = this.mc.getDefence();
        return stats;
    }

    /* Gets the current node in story where the player is */
    public int getSavedNode(){
        return this.ht.getCurrentNode();
    }

    /* Resets the story and saves game*/
    public void reset(){
        this.ht.setNextNode(1);
        this.rs.resetPoints();
        try {
            SaveGameSystem.saveGame(0, "", 0, 0, 0, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Traverse the history leftwards, retrieving database information
    public Result goLeft(JLabel plot_text, JButton fopt_text, JButton sopt_text, JLabel iconLabel) throws SQLException, IOException{
        this.ht.setNodeToFirstChild();
        Result res = this.conn.fetchHistory();
        this.rs.increasePoints(10);
        setFieldsText(plot_text, fopt_text, sopt_text, iconLabel);
        /* If it's a combat node decide automatically where to go, the retrieve information from database*/
        checkIfBattleNode(plot_text, fopt_text, sopt_text, iconLabel);
        return res;
    }

    // Traverse the history rightwards, retrieving database information
    public Result goRigth(JLabel plot_text, JButton fopt_text, JButton sopt_text, JLabel iconLabel) throws SQLException, IOException{
        ht.setNodeToSecondChild();
        Result res = conn.fetchHistory();
        this.rs.increasePoints(10);
        setFieldsText(plot_text, fopt_text, sopt_text, iconLabel);
        /* If it's a combat node decide automatically where to go, the retrieve information from database */
        checkIfBattleNode(plot_text, fopt_text, sopt_text, iconLabel);
        return res;
    }

    private void setFieldsText(javax.swing.JLabel plot_text, javax.swing.JButton fopt_text, javax.swing.JButton sopt_text, JLabel iconLabel){
        plot_text.setText(this.ht.getMainText());
        fopt_text.setText(this.ht.getFirstOption());
        sopt_text.setText(this.ht.getSecondOption());
        if(this.ht.getImageUrl() != null){
            iconLabel.setIcon(new ImageIcon(this.ht.getImageUrl()));
        }
    }

    private void checkIfBattleNode(javax.swing.JLabel plot_text, javax.swing.JButton fopt_text, javax.swing.JButton sopt_text, JLabel iconLabel) throws SQLException, IOException {
        if(cs.isBattleNode(this.ht.getCurrentNode())){
            if(cs.fight(mc, ht) == BattleResult.WIN){
                this.mc.increaseHp();
                this.mc.increaseDamage();
                this.mc.increaseDefence();
                this.ht.setNodeToFirstChild();
                this.conn.fetchHistory();
                this.rs.increasePoints(20);
                setFieldsText(plot_text, fopt_text, sopt_text, iconLabel);
            }
            else{
                this.ht.setNodeToSecondChild();
                this.conn.fetchHistory();
                setFieldsText(plot_text, fopt_text, sopt_text, iconLabel);
            }
        }
    }
}
