package com.backend;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Monster {
    private final int damage;
    private final int hp;
    private final int defence;

    public Monster(int node) throws IOException {
        // Read content of te file
        //Path configPath = Path.of("src" + File.separator + "Configs" + File.separator + "monsters.json");
        Path configPath = Path.of("Config" + File.separator + "battle" + File.separator + "monsters.json");
        String content = Files.readString(configPath);

        // Parse Json data into object attributes
        JSONObject obj = new JSONObject(content).getJSONObject(String.valueOf(node));
        this.damage = obj.getInt("base_damage");
        this.hp = obj.getInt("base_hp");
        this.defence = obj.getInt("base_defence");
    }

    // Getters
    public int getDamage() {
        return damage;
    }

    public int getHp() {
        return hp;
    }

    public int getDefence() {
        return defence;
    }
}
