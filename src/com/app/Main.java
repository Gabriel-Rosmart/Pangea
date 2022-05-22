package com.app;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){

        SwingUtilities.invokeLater(() -> {
            try {
                GameRunner gr = new GameRunner();
                gr.load();
                gr.run();
            } catch (SQLException e) {
                System.out.println("Something went wrong when talking with the database, check the configuration file");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Something went wrong reading files, check if all files exists");
                e.printStackTrace();
            }
        });
    }
}
