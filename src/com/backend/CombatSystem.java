package com.backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class CombatSystem {
    //private static final int battle_nodes[] = {4};
    private final ArrayList<Integer> battle_nodes;

    public CombatSystem() throws IOException {
        battle_nodes = new ArrayList<>();

        // Reads the contents of the config file
        //Path configPath = Path.of("src" + File.separator + "Configs" + File.separator + "battle_nodes.json");
        Path configPath = Path.of("Config" + File.separator + "battle_nodes.json");
        String content = Files.readString(configPath);

        // Parse the Json array inside it
        JSONObject obj = new JSONObject(content);
        JSONArray arr = obj.getJSONArray("nodes");

        // Populate the array with the contents of the json
        for(int i = 0; i < arr.length(); i++){
            battle_nodes.add(arr.optInt(i));
        }
    }

    // Detect if the node is a battle one
    public boolean isBattleNode(int node){
        return battle_nodes.contains(node);
    }

    // Fight between the use character and the corresponding node monsters
    public BattleResult fight(MainCharacter ch, HistoryTree ht) throws IOException{
        Monster m = new Monster(ht.getCurrentNode());
        int chr_total_damage = (int) (ch.getDamage() * (1 - ((float) m.getDefence() / 200)));
        int monster_total_damage = (int) (m.getDamage() * (1- ((float) ch.getDefence() / 200)));
        int chr_hp = ch.getHp();
        int monster_hp = m.getHp();

        while(chr_hp > 0 && monster_hp > 0){
            monster_hp -= chr_total_damage;
            chr_hp -= monster_total_damage;
        }

        if(chr_hp > 0){
            return BattleResult.WIN;
        }
        else{
            return BattleResult.LOSE;
        }
    }
}
