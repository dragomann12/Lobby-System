package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.components.events.*;

public class EventManager {

    public Build_Event getBuildEvent(){
        return new Build_Event();
    }

    public Chat_Event getChatEvent(){
        return new Chat_Event();
    }

    public Click_Event getClickEvent(){
        return new Click_Event();
    }

    public Damage_Event getDamageEvent(){
        return new Damage_Event();
    }

    public Drag_Event getDragEvent(){
        return new Drag_Event();
    }

    public Drop_Event getDropEvent(){
        return new Drop_Event();
    }

    public Event_Blocker getEventBlocker(){
        return new Event_Blocker();
    }

    public Health_Event getHealthEvent(){
        return new Health_Event();
    }

    public Hide_Event getHideEvent(){
        return new Hide_Event();
    }

    public Hunger_Event getHungerEvent(){
        return new Hunger_Event();
    }

    public Interact_Event getInteractEvent(){
        return new Interact_Event();
    }

    public Join_Event getJoinEvent(){
        return new Join_Event();
    }

}
