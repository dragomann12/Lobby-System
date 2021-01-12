package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.manager.Managers;
import org.bukkit.entity.Player;

import java.util.Objects;

public class AcessManager extends Managers {

    public void addPlayerToBlackList(Player p, Player target){

        this.getConnectionManager().setRowBlacklist(target);
        p.sendMessage("§aDer Spieler §4" + target.getName() + " §awurde erfolgreich ausgeschlossen!");
        target.sendMessage("§4Du wurdest gesperrt du hast nun kein Adminzugang mehr");

    }

    public boolean isBlacklisted(Player p){
        return Objects.equals(p.getUniqueId().toString(), this.getConnectionManager().callRowBlacklist(p));
    }

}
