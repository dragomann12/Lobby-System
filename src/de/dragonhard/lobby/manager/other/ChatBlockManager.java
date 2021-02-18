package de.dragonhard.lobby.manager.other;

public class ChatBlockManager {

    public boolean isContainLink(String value){

        String[] blacklist = {"www.","http://",".php",".html"};

        for (String s : blacklist) {
            if (value.contains(s)) {
                return true;
            }
        }

        return false;
    }

    public boolean isContainNumber(String value){

        int[] blacklist = {0,1,3,4,5,6,7,8,9};

        for (int j : blacklist) {
            if (value.contains("" + j)) {
                return true;
            }
        }

        return false;
    }

    public String stringReplacer(String value){
        if(isContainBadComponent(value)){
            value.replace("%","");
            value.replace("!","");
            value.replace("$","");}
        return value;
    }

    public boolean isContainBadComponent(String value){

        String[] blacklist = {"%","!","$"};

        for (String s : blacklist) {
            if(value.contains(s)){
                return true;
            }

        }

        return false;
    }


}
