package me.scraft.addon.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class customConfig {

	private static File file;
	private static FileConfiguration customFile;
	
	public static void setup(double version) {
		file = new File(Bukkit.getServer().getPluginManager().getPlugin("skScraft").getDataFolder(), "config.yml");
	
		if(!file.exists()) {
			try{
				file.createNewFile();
			}catch(IOException e) {}
			Bukkit.getLogger().info("§3[§bCONFIG§3] §eskScraft §e» §6No config found! Creating one!");
		}
		else {
			customFile = YamlConfiguration.loadConfiguration(file);
			if (customConfig.get().getDouble("VERSION") != version) {
				file.delete();
				try {
					file.createNewFile();
				} catch (IOException e) {}
				Bukkit.getLogger().info("§3[§bCONFIG§3] §eskScraft §e» §6New version found! Regenerating config!");
				customFile = YamlConfiguration.loadConfiguration(file);
			}
		}
		customFile = YamlConfiguration.loadConfiguration(file);
	}
	public static FileConfiguration get(){
		return customFile;
	}
	
	public static void recreate() {
		file.delete();
		if(!file.exists()) {
			try{
				file.createNewFile();
				
			}catch(IOException e) {
			}
		
		}
		customFile = YamlConfiguration.loadConfiguration(file);
	}
	
	public static void save(){
		try {
			customFile.save(file);
		}catch (IOException e) {
			System.out.println("Couldn't save the config file!");
		}
	}
	
	public static void reload() {
		customFile = YamlConfiguration.loadConfiguration(file);
	}
}
