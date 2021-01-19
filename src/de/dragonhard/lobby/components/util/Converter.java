package de.dragonhard.lobby.components.util;

public class Converter {

    public static boolean convertIntToBool(int value){
        return value >= 1;
    }

    public static int convertBoolToInt(boolean value){
        if(value){return 1;}
        return 0;
    }

}
