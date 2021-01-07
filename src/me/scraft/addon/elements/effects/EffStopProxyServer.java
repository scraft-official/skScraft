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
import me.dommi2212.BungeeBridge.packets.PacketStopProxy;


public class EffStopProxyServer extends Effect {
	 
    static {
        Skript.registerEffect(EffStopProxyServer.class, "stop [bungee[cord]] proxy [with message %string%]");
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
        return "Effect: stop [bungee[cord]] proxy";
    }
 
    @Override
    protected void execute(Event event) {
    	if(message.getSingle(event) == null) {
    		PacketStopProxy packet = new PacketStopProxy();
    		packet.send();
    		
    		if (customConfig.get().getBoolean("DEBUG MODE") == true) {
				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Stopping proxy!");
			}
    	}
    	else {
    		PacketStopProxy packet = new PacketStopProxy(message.getSingle(event));
    		packet.send();
    		
    		if (customConfig.get().getBoolean("DEBUG MODE") == true) {
				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Stopping proxy with message: " + message.getSingle(event));
			}
    	}
    }
}
