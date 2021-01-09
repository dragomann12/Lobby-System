package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.reader.PluginConfigReader;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SoundManager extends PluginConfigReader {

    private static final String fileName = "sound_config";
    private static Sound sound = null;
    private static ArrayList<Sound> sounds = new ArrayList<Sound>();

    private String getFileName(){
        return fileName;
    }

    private Sound getSound(){
        return sound;
    }

    public void generateConfig(){

        this.setFile(fileName);

        this.setDefault("onMenuClick","");
        this.setDefault("SoundEnabled", true);
        this.setDefault("PlayerCanDisable", true);
    }

    public void play(Player p, String sound){// @TODO fix an IllegalArgumentException when you try to load a not existing sound
            try{
                if(!soundExists(sound)){p.sendMessage("§4Der Sound §e" + sound + " §4konnte nicht geladen werden!");return;}

                p.sendMessage("§aDer Sound §b" + sound + " §awird abgespielt!");
                p.playSound(p.getLocation(), getSound(), 1.0F, 1.0F);

            }catch(IllegalArgumentException e){
                p.sendMessage("§4Der Sound §e" + sound + " §4konnte nicht geladen werden!");
            }
    }

    public void getSoundOfString(String soundName){
        sound = Sound.valueOf(soundName);
    }

    public boolean soundExists(String soundName){
        if(sounds.contains(sound.valueOf(soundName))){return true;}
        return false;
    }

    public void addSoundsToList(){
        for(Sound s : Sound.values()){
            sounds.add(s);
        }
    }
    public void setSound(String type, int soundID){
        this.setFile(fileName);

        switch(type){

            case "CLICK":

                switch(soundID){

                    case 0: sound = Sound.CLICK; break;
                    case 1: sound = Sound.ANVIL_USE; break;
                    case 3: sound = Sound.ANVIL_LAND; break;

                    case 4: sound = Sound.CHEST_OPEN; break;
                    case 5: sound = Sound.CHEST_CLOSE; break;
                    case 6: sound = Sound.CHICKEN_EGG_POP; break;

                    case 7: sound = Sound.CREEPER_HISS; break;
                    case 8: sound = Sound.CAT_MEOW; break;
                    case 9: sound = Sound.WOLF_BARK; break;
                }

                break;

        }

    }

}
