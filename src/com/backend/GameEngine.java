package com.backend;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class GameEngine {
    private final HistoryTree ht;
    private final Connector conn;
    private final CombatSystem cs;
    private MainCharacter mc;

    public GameEngine() throws IOException, SQLException {
        this.cs = new CombatSystem();
        this.ht = new HistoryTree();
        this.conn = new Connector(ht);
        this.conn.initConnector();
    }

    // Initialize the connection with the database and retrieve first tuple
    public void init(javax.swing.JLabel plot_text, javax.swing.JButton fopt_text, javax.swing.JButton sopt_text, JLabel iconLabel) throws SQLException{
        this.conn.fetchHistory();
        setFieldsText(plot_text, fopt_text, sopt_text, iconLabel);
    }

    // Choose the character, needed in order to the battle nodes to work
    public void chooseMainCharacter(String chrPath) throws IOException{
        this.mc = new MainCharacter("Config" + File.separator + chrPath);
    }

    // Traverse the history leftwards, retrieving database information
    public Result goLeft(JLabel plot_text, JButton fopt_text, JButton sopt_text, JLabel iconLabel) throws SQLException, IOException{
        this.ht.setNodeToFirstChild();
        Result res = this.conn.fetchHistory();
        setFieldsText(plot_text, fopt_text, sopt_text, iconLabel);
        /* If it's a combat node decide automatically where to go, the retrieve information from database*/
        checkIfBattleNode(plot_text, fopt_text, sopt_text, iconLabel);
        return res;
    }

    // Traverse the history rightwards, retrieving database information
    public Result goRigth(JLabel plot_text, JButton fopt_text, JButton sopt_text, JLabel iconLabel) throws SQLException, IOException{
        ht.setNodeToSecondChild();
        Result res = conn.fetchHistory();
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
                this.ht.setNodeToFirstChild();
                this.conn.fetchHistory();
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
