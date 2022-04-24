package de.dragonhard.lobby.manager.skill;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class SkillManager {
    private static final HashMap<String, Skill> skills = new HashMap<>();

    public static void addSkill(Skill skill){

        if(skills.containsKey(skill.id())){
            return;
        }

        skills.put(skill.id(), skill);
    }

    public static void initSkill(String id, Player player){
        if(skills.containsKey(id)){
            skills.get(id).initialize(player);
        }
    }
}
