package com.backend;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SaveGameSystem {

    /* Retrieves the current saved game information */
    public static String[] loadGame() throws IOException {
        String[] data = new String[6];
        Path saveGamePath = Path.of("Data/saved_game.json");
        String content = Files.readString(saveGamePath);
        JSONObject obj = new JSONObject(content);
        data[0] = String.valueOf(obj.getInt("node"));
        data[1] = obj.getString("character");
        data[2] = String.valueOf(obj.getJSONObject("stats").getInt("hp"));
        data[3] = String.valueOf(obj.getJSONObject("stats").getInt("attack"));
        data[4] = String.valueOf(obj.getJSONObject("stats").getInt("defence"));
        data[5] = String.valueOf(obj.getInt("points"));
        return data;
    }

    /* Saves the game overriding existing data */
    public static void saveGame(int node, String selected_character, int hp, int damage, int defence, int points) throws IOException {
        Path saveGamePath = Path.of("Data" + File.separator + "saved_game.json");
        String content = Files.readString(saveGamePath);
        JSONObject obj = new JSONObject(content);
        obj.put("node", node);
        obj.put("character", selected_character);
        obj.getJSONObject("stats").put("hp", hp);
        obj.getJSONObject("stats").put("attack", damage);
        obj.getJSONObject("stats").put("defence", defence);
        obj.put("points", points);

        FileWriter file = new FileWriter("Data" + File.separator + "saved_game.json");
        file.write(obj.toString());
        file.flush();
    }

    /* Gets the current saved point in story, if 0 then story is not yet started */
    public static int getSavedPoint() throws IOException {
        Path saveGamePath = Path.of("Data" + File.separator + "saved_game.json");
        String content = Files.readString(saveGamePath);
        JSONObject obj = new JSONObject(content);
        return obj.getInt("node");
    }
}
