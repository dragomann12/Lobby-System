package de.dragonhard.lobby.reader;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class WarpReader {
	private static String fileName;
	private static UUID pUUID;
	private static boolean isList = false;

	public void set(String item, String value) {
		FileConfiguration cfg = getFileConfiguration();
		
			cfg.set(item, value);
		
		
		try {
			cfg.save(getFile());
		} catch(IOException e) {

		}
	}

	public void addToList(String item, String value) {
		FileConfiguration cfg = getFileConfiguration();

		cfg.set(item, value);


		try {
			cfg.save(getList());
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
		String _path = Config.path_reader_warp.replace("%pid%",pUUID.toString());
		return new File(_path, fileName + ".yml");
		
	}

	public File getFile(String filename) {
		String _path = Config.path_reader_warp.replace("%pid%",pUUID.toString());
		return new File(_path, filename + ".yml");

	}

	private File getList() {
		String _path = Config.path_reader_warp.replace("%pid%",pUUID.toString());
		return new File(_path, fileName + ".yml");

	}

	public void setFile(Player p ,String warpName) {

			fileName = "warp_" + warpName;
			pUUID = p.getUniqueId();


	}

	public boolean exists(Player p, String warpName){
		pUUID = p.getUniqueId();
		String _path = Config.path_reader_warp.replace("%pid%",pUUID.toString());
		File file = new File(_path,  "warp_" + warpName + ".yml");

		if(file.exists()){return true;}else{return false;}

	}
	public File getCurrentFile(Player p, String warpName){
		pUUID = p.getUniqueId();

		return new File("plugins/LobbySystem/Data/Player/"+pUUID+"/warps", "warp_" + warpName + ".yml");
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
