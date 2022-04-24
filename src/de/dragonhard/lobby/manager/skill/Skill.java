package de.dragonhard.lobby.manager.skill;

import org.bukkit.entity.Player;

public interface Skill {
    String id();
    void initialize(Player player);
}
