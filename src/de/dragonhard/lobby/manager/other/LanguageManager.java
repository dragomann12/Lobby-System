package de.dragonhard.lobby.manager.other;

import org.bukkit.entity.Player;
@Deprecated
public class LanguageManager extends LanguageReader {

    public void sendMessageInLanguage(String language, String textID, Player p){

        if(language != ""){

                this.setLanguage(language);

            if(textID != ""){

                this.setFile(textID);
                p.sendMessage(this.getString("Title") + " " + this.getString("Splitter") + " " + this.getString("Message"));

            }

        }

    }

    public void createMessageInLanguage(String language, String titleColor, String messageColor, String splitterColor, String textID, String title, String message, String splitter){

        if(language != ""){

            this.setLanguage(language);

            if(textID != ""){

                this.setFile(textID);

                if(title != "" && message != "" && splitter != "" && titleColor != "" && messageColor != "" && splitterColor != ""){

                    this.set("Title",title);
                    this.set("Message", message);
                    this.set("Splitter", splitter);
                    this.set("Color_Title", titleColor);
                    this.set("Color_Message", messageColor);
                    this.set("Color_Splitter", splitterColor);

                }

            }

        }

    }
}
