package de.dragonhard.lobby.reader;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Reader {
	private String fileName;
	private final String type;

	public Reader(String type, String fileName){

		this.type = type;
		this.fileName = fileName + ".yml";
	}
	
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

	public float getFloat(String item) {
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
		return new File("plugins/LobbySystem/Data/" + type, fileName + ".yml");
	}

	public boolean exists(){
		File file = new File("plugins/LobbySystem/Data/" + type,  fileName + ".yml");

		if(file.exists()){return true;}
		return false;
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
