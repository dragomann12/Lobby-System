package de.dragonhard.lobby.components;

public class PermissionList {

    public static String getPermission(String category, int permissionID){
            String permissionBase = "lbSys.use.";
            // case 0: return permissionBase + "";
        switch(category){

            case "User":
                switch(permissionID){
                    case 0: return permissionBase + "Menu.Test";//in use
                }
                break;
            case "Config":

                switch(permissionID){
                    case 0: return permissionBase + "Warp.config";//in use
                    case 1: return permissionBase + "Spawn.set";//in use
                    case 2: return permissionBase + "Spawn.remove";//in use
                    case 3: return permissionBase + "GlobalWarp.set";//in use
                    case 4: return permissionBase + "GlobalWarp.remove";//in use
                }
                break;

            case "Use":

                switch(permissionID){
                    case 0: return permissionBase + "Warp"; //in use
                    case 1: return permissionBase + "Spawn"; //in use
                    case 2: return permissionBase + "AutoWarp";//in use
                    case 3: return permissionBase + "GlobalWarp";//in use
                }
                break;

            case "Message":

                switch(permissionID){
                    case 0: return permissionBase + "Message.Alert";//in use
                    case 1: return permissionBase + "Message.Warning";//in use
                    case 2: return permissionBase + "Message.Information";//in use
                }
            break;

            case "external":

                switch(permissionID){
                    case 0: return permissionBase + "cp.open";
                }

                break;

            case "Menu":

                switch(permissionID){
                    case 0: return permissionBase + "Menu.Admin";
                    case 1: return permissionBase + "Menu.Lobby";
                    case 2: return permissionBase + "Menu.Settings";
                    case 3: return permissionBase + "Menu.Shop";
                    case 4: return permissionBase + "Menu.Hide";
                    case 5: return permissionBase + "Menu.Friends";

                }

                break;

            case "Menu_Item":

                switch(permissionID){
                    case 0: return permissionBase + "Menu.Items.Owner";
                    case 1: return permissionBase + "Menu.Items.Moderator";
                    case 2: return permissionBase + "Menu.Items.Builder";
                    case 3: return permissionBase + "Menu.Items.Administrator";
                    case 4: return permissionBase + "Menu.Items.Developer";

                }

                break;

            case "Mode":

                switch(permissionID){
                    case 0: return permissionBase + "Mode.Build";
                    case 1: return permissionBase + "Game.Dev";
                    case 2: return permissionBase + "Game.BedWars";
                    case 3: return permissionBase + "Game.TntRun";
                    case 4: return permissionBase + "Game.SkyWars";
                    case 5: return permissionBase + "Game.Beta";
                }

                break;

            case "Game":

                switch(permissionID){
                    case 0: return permissionBase + "Game.Public";
                    case 1: return permissionBase + "Game.Beta";
                    case 2: return permissionBase + "Game.Dev";
                    case 3: return permissionBase + "Game.BedWars";
                    case 4: return permissionBase + "Game.TntRun";
                    case 5: return permissionBase + "Game.SkyWars";

                }

                break;


        }
        return "";
    }

}
