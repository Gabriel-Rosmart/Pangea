package com.backend;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainCharacter {
    private int damage;
    private int hp;
    private int defence;

    public MainCharacter(String configFile) throws IOException {
        // Read the content of the file
        Path configPath = Path.of(configFile);
        String content = Files.readString(configPath);

        // Parse Json data into object attributes
        JSONObject obj = new JSONObject(content);
        this.damage = obj.getInt("base_damage");
        this.hp = obj.getInt("base_hp");
        this.defence = obj.getInt("base_defence");
    }


    // Getters an Setters
    public int getDamage() {
        return damage;
    }

    public int getHp() {
        return hp;
    }

    public int getDefence() {
        return defence;
    }

    public void increaseHp(){
        this.hp += 20;
    }

    public void increaseDamage(){
        this.damage += 15;
    }

    public void increaseDefence(){
        this.defence += 10;
    }
}
