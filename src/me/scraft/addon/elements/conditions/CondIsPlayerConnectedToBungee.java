package me.scraft.addon.elements.conditions;

import javax.annotation.Nullable;

import me.dommi2212.BungeeBridge.packets.PacketIsPlayerOnline;
import me.dommi2212.BungeeBridge.util.IsOnlineResult;
import me.scraft.addon.files.customConfig;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
 
public class CondIsPlayerConnectedToBungee extends Condition {
 
    static {
        Skript.registerCondition(CondIsPlayerConnectedToBungee.class, "[player] %string% is connected to [bungeecord] network");
    }
 
    private Expression<String> player;
 
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        this.player = (Expression<String>) expressions[0];
        setNegated(parser.mark == 1);
        return true;
    }
 
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Condition: if %player% is connected to bungeecord network";
        
    }
 
    @Override
    public boolean check(Event event) {
    	if(player.getSingle(event) == null) {
    		return false;
    	}
    	else {
    		PacketIsPlayerOnline packet = new PacketIsPlayerOnline(player.getSingle(event));
    		Object obj = packet.send();
    		IsOnlineResult isOnline = (IsOnlineResult) obj;

    		if(isOnline == IsOnlineResult.ONLINE) {
    			if (customConfig.get().getBoolean("DEBUG MODE") == true) {
    				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Player " + player.getSingle(event) + " is online!");
    			}
    			return true;
    		}
    	
    		else {
    			if (customConfig.get().getBoolean("DEBUG MODE") == true) {
    				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Player " + player.getSingle(event) + " is offline!");
    			}
    			return false;  			
    		}
    	
    	}
    }
}