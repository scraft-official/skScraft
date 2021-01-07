package me.scraft.addon.elements.effects;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.scraft.addon.files.customConfig;
import me.dommi2212.BungeeBridge.packets.PacketKickAllPlayers;


public class EffKickAllPlayersFromBungeeCord extends Effect {
	 
    static {
        Skript.registerEffect(EffKickAllPlayersFromBungeeCord.class, "kick all players from [bungee[cord]] network [with message %-string%]");
    }
 
    private Expression<String> message;
 
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expression, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        this.message = (Expression<String>) expression[0];
        return true;
    }
 
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Effect: kick all players from [bungee[cord]] network [with message %-string%]";
    }
 
    @Override
    protected void execute(Event event) {
    	final String m = message != null ? message.getSingle(event) : "";
    	
    	if(m == "") {
    		PacketKickAllPlayers packet = new PacketKickAllPlayers(customConfig.get().getString("Default kick message"));
    		packet.send();
    		if (customConfig.get().getBoolean("DEBUG MODE") == true) {
    			Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Kicking all players from network");
			}
    	}
    	else {
    		PacketKickAllPlayers packet = new PacketKickAllPlayers(message.getSingle(event));
    		packet.send();
    		
    		if (customConfig.get().getBoolean("DEBUG MODE") == true) {
				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Kicking all players from network with message: " + message.getSingle(event));
			}
    	}
    }
}
