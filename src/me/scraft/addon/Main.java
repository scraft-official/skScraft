package me.scraft.addon;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import me.scraft.addon.bstats.Metrics;
import me.scraft.addon.commands.helpMessage;
import me.scraft.addon.files.customConfig;

import org.bukkit.plugin.Plugin;


 
public class Main extends JavaPlugin {

Main instance;
   SkriptAddon addon;
   
   
   public void onEnable() {
	   double ver = 1.3;
	   int pluginId = 9931;
       Metrics metrics = new Metrics(this, pluginId);
       
	   customConfig.setup(ver);
	   customConfig.get().addDefault("DEBUG MODE", false);
	   customConfig.get().addDefault("Default kick message", "§eYou have been kicked from the network!");
	   customConfig.get().addDefault("Default stop proxy message", "§eProxy server is restarting!");
	   customConfig.get().addDefault("VERSION", ver);
	   customConfig.get().options().copyDefaults(true);
	   customConfig.save();
	   
	   metrics.addCustomChart(new Metrics.SimplePie("version", () -> String.valueOf(ver)));
       metrics.addCustomChart(new Metrics.SimplePie("debug_mode", () -> String.valueOf(customConfig.get().getBoolean("DEBUG MODE"))));
		     
	   Plugin skriptPlugin = Bukkit.getPluginManager().getPlugin("Skript");
	   if (skriptPlugin == null) {
	    	Bukkit.getLogger().info("");
	    	Bukkit.getLogger().info("§4=============================================");
	    	Bukkit.getLogger().info("§cPlugin Skript not found!");
	    	Bukkit.getLogger().info("§eTo use skScraft, you need to have Skript.");
	    	Bukkit.getLogger().info("§eDownload Skript at §6https://github.com/SkriptLang/Skript/releases");
	    	Bukkit.getLogger().info("§4=============================================");
	      	Bukkit.getLogger().info("");
	      	Bukkit.getPluginManager().disablePlugin((Plugin)this);
	      return;
	   }
	   
	   Plugin BungeeBridgePlugin = Bukkit.getPluginManager().getPlugin("BungeeBridgeC");
	   if (BungeeBridgePlugin == null) {
	    	Bukkit.getLogger().info("");
	      	Bukkit.getLogger().info("§4=============================================");
	      	Bukkit.getLogger().info("§cPlugin BungeeBridge not found!");
	      	Bukkit.getLogger().info("§eTo use skScraft, you need to have BungeeBridge.");
	      	Bukkit.getLogger().info("§eDownload BungeeBridge at §6https://www.spigotmc.org/resources/bungeebridge.5820/");
	      	Bukkit.getLogger().info("§4=============================================");
	      	Bukkit.getLogger().info("");
	      	Bukkit.getPluginManager().disablePlugin((Plugin)this);
	      return;
	   } 
	   
	   getCommand("skscraft").setExecutor(new helpMessage());
	   
       instance = this;
       addon = Skript.registerAddon(this);
       try {
           addon.loadClasses("me.scraft.addon", "elements");
       } catch (IOException e) {
           e.printStackTrace();
       }
       Bukkit.getLogger().info("§6=============================================");
       Bukkit.getLogger().info("§a(skScraft) has been enabled!");
       Bukkit.getLogger().info("§eIf you have any questions, fell free to pm me ;)");
       Bukkit.getLogger().info("§eOn my discord -> https://discord.gg/DE4Tqr6CDD");
       Bukkit.getLogger().info("§6=============================================");
   }
   
   public void onDisable() {
       Bukkit.getLogger().info("§6=============================================");
       Bukkit.getLogger().info("§c(skScraft) has been disabled!");
       Bukkit.getLogger().info("§6=============================================");
   }
 
   public Main getInstance() {
       return instance;
   }
 
   public SkriptAddon getAddonInstance() {
       return addon;
   }
}
