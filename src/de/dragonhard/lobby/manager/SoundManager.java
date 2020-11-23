package de.dragonhard.lobby.manager;

import de.dragonhard.lobby.reader.PluginConfigReader;

public class SoundManager extends PluginConfigReader {

    private static final String fileName = "sound_config";


    private String getFileName(){
        return fileName;
    }


    public void generateConfig(){

        this.setFile(fileName);

        this.setDefault("onMenuClick","");
        this.setDefault("SoundEnabled", true);
        this.setDefault("PlayerCanDisable", true);
    }

    public void playSound(String type, int soundID){
        this.setFile(fileName);

        switch(type){

            case "CLICK_BAR":

                switch(soundID){
                    case 0: break;
                    case 1: break;
                    case 3: break;
                    case 4: break;
                }

                break;

            case "CLICK_MENU":

                switch(soundID){
                    case 0: break;
                    case 1: break;
                    case 3: break;
                    case 4: break;
                }

                break;

            case "EFFECT_PLAYER":

                switch(soundID){
                    case 0: break;
                    case 1: break;
                    case 3: break;
                    case 4: break;
                }

                break;

            case "EFFECT_WORLD":

                switch(soundID){
                    case 0: break;
                    case 1: break;
                    case 3: break;
                    case 4: break;
                }

                break;

            case "EFFECT_SIGN":

                switch(soundID){
                    case 0: break;
                    case 1: break;
                    case 3: break;
                    case 4: break;
                }

                break;

            case "EFFECT_REDSTONE":

                switch(soundID){
                    case 0: break;
                    case 1: break;
                    case 3: break;
                    case 4: break;
                }

                break;

        }
    }

}
