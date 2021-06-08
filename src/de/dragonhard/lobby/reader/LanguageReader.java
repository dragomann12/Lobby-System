package de.dragonhard.lobby.reader;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
@Deprecated
public class LanguageReader {
	private static String fileName;
	private static String languageBase;
	
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
		
		return new File(Config.path_reader_language + languageBase, fileName + ".yml");
		
	}

	public File getFile(String filename) {

		return new File(Config.path_reader_language + languageBase, filename  + ".yml");

	}
	
	public void setFile(String configName) {
		fileName = configName;

	}

	public void setLanguage(String lang){
		languageBase = lang;
	}

	public boolean exists( String languageItem){
		File file = new File(Config.path_reader_language + languageBase,  languageItem + ".yml");

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
