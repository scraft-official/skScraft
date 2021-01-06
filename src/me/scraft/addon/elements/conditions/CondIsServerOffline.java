package me.scraft.addon.elements.conditions;

import javax.annotation.Nullable;

import me.dommi2212.BungeeBridge.packets.PacketIsServerOnline;
import me.scraft.addon.files.customConfig;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
 
public class CondIsServerOffline extends Condition {
 
    static {
        Skript.registerCondition(CondIsServerOffline.class, "server %string% is offline");
    }
 
    private Expression<String> server;
 
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        this.server = (Expression<String>) expressions[0];
        setNegated(parser.mark == 1);
        return true;
    }
 
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Condition: if server %player% is offline";
        
    }
 
    @Override
    public boolean check(Event event) {
    	if(server.getSingle(event) == null) {
    		if (customConfig.get().getBoolean("DEBUG MODE") == true) {
				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Server not set!");
			}
    		return false;
    	}
    	else {
    		PacketIsServerOnline packet = new PacketIsServerOnline(server.getSingle(event));
    		
    		boolean info = (boolean) packet.send();

    		if(info == false) {
    			if (customConfig.get().getBoolean("DEBUG MODE") == true) {
    				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Server " + server.getSingle(event) + " is offline!");
    			}
    			return true;
    		}
    	
    		else {
    			if (customConfig.get().getBoolean("DEBUG MODE") == true) {
    				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Server " + server.getSingle(event) + " is online!");
    			}
    			return false;
    		}
    	
    	}
    }
}