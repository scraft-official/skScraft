package me.scraft.addon.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.scraft.addon.files.customConfig;

public class helpMessage implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§6§m                                                                          ");
			sender.sendMessage("§eSkScraft §e» §6/skScraft reload §8- §7Reload skScraft config file.");
			sender.sendMessage("§6§m                                                                          ");
		}
		else if(args[0].equalsIgnoreCase("reload")){
			sender.sendMessage("§6§m                                                                          ");
			sender.sendMessage("§eSkScraft §e» §aConfig reloaded.");
			sender.sendMessage("§6§m                                                                          ");
			customConfig.reload();
		}
		else {
			sender.sendMessage("§6§m                                                                          ");
			sender.sendMessage("§eSkScraft §e» §6/skScraft reload §8- §7Reload skScraft config file.");
			sender.sendMessage("§6§m                                                                          ");
		}
		
		return true;
			
	
	}
}
