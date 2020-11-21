package de.dragonhard.lobby.reader;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class GroupReader {
	private static String fileName;
	private static UUID pUUID;
	
	public void set(String item, String value) {
		FileConfiguration cfg = getFileConfiguration();
		
			cfg.set(item, value);
		
		
		try {
			cfg.save(getFile());
		} catch(IOException e) {

		}
	}
	
	public void set(String item, int value) {
		FileConfiguration cfg = getFileConfiguration();
		
			cfg.set(item, value);
		
		
		try {
			cfg.save(getFile());
		} catch(IOException e) {

		}
	}
	
	public void set(String item, boolean value) {
		FileConfiguration cfg = getFileConfiguration();
		
			cfg.set(item, value);
		
		
		try {
			cfg.save(getFile());
		} catch(IOException e) {

		}
	}

	public void set(String item, double value) {
		FileConfiguration cfg = getFileConfiguration();

		cfg.set(item, value);


		try {
			cfg.save(getFile());
		} catch(IOException e) {

		}
	}
	
	public void setDefault(String item, String value) {
		
		FileConfiguration cfg = getFileConfiguration();
		
		cfg.options().copyDefaults(true);
		
		cfg.addDefault(item, (String)value);
		
		
		try {
			cfg.save(getFile());
		} catch(IOException e) {

		}
		
	}

	public void readData(String item) {
		FileConfiguration cfg = getFileConfiguration();
			cfg.getString(item);
	}
	
	public String getString(String item) {
		FileConfiguration cfg = getFileConfiguration();
		return cfg.getString(item);
	}

	public double getDouble(String item) {
		FileConfiguration cfg = getFileConfiguration();
		return cfg.getDouble(item);
	}

	public float getfloat(String item) {
		FileConfiguration cfg = getFileConfiguration();
		return (float)cfg.getDouble(item);
	}

	public int getInteger(String item) {
		
		FileConfiguration cfg = getFileConfiguration();
		return cfg.getInt(item);
		
	}
	
	public Boolean getBoolean(String item) {
		
		FileConfiguration cfg = getFileConfiguration();
		return cfg.getBoolean(item);
		
	}
	
	private File getFile() {
		
		return new File("plugins/LobbySystem/Data/Player/Groups", fileName + ".yml");
		
	}
	//plugins/LobbySystem/Data/Player/pUUID/warps
	public File getFile(String filename) {

		return new File("plugins/LobbySystem/Data/Player/Groups", "group_" + filename + ".yml");

	}
	
	public void setFile(String groupName) {
		fileName = "group_" + groupName;
	}

	public boolean exists(String groupName){
		File file = new File("plugins/LobbySystem/Data/Player/Groups",  "group_" + groupName + ".yml");

		if(file.exists()){return true;}else{return false;}

	}
	
	private FileConfiguration getFileConfiguration() {
		
		return YamlConfiguration.loadConfiguration(getFile());
	}

	public void setDefault(String item, boolean value) {
	
	FileConfiguration cfg = getFileConfiguration();
		
		cfg.options().copyDefaults(true);
		
		cfg.addDefault(item, value);
		
		
		try {
			cfg.save(getFile());
		} catch(IOException e) {

		}
		
	}
	
	public void setDefault(String item, int value) {
		
		FileConfiguration cfg = getFileConfiguration();
			
			cfg.options().copyDefaults(true);
			
			cfg.addDefault(item, value);
			
			
			try {
				cfg.save(getFile());
			} catch(IOException e) {

			}
			
		}
	
}
