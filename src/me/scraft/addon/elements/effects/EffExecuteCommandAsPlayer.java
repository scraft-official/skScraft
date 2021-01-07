package me.scraft.addon.elements.effects;

import java.util.UUID;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import me.dommi2212.BungeeBridge.packets.PacketChat;
import me.dommi2212.BungeeBridge.util.IsOnlineResult;
import me.scraft.addon.files.customConfig;
import me.dommi2212.BungeeBridge.packets.PacketGetPlayerUUID;
import me.dommi2212.BungeeBridge.packets.PacketIsPlayerOnline;


public class EffExecuteCommandAsPlayer extends Effect {
	 
    static {
        Skript.registerEffect(EffExecuteCommandAsPlayer.class, "execute command %string% on [bungee[cord]] network as [player] %string%");
    }
 
    private Expression<String> message;
    private Expression<String> player;
 
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expression, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        this.message = (Expression<String>) expression[0];
        this.player = (Expression<String>) expression[1];
        return true;
    }
 
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Effect: execute command %string% on [bungee[cord]] network as [player] %string%";
    }
 
    @Override
    protected void execute(Event event) {
    	if(message.getSingle(event) == null) {
    		if (customConfig.get().getBoolean("DEBUG MODE") == true) {
				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Command not set!");
			}
    	}
    	if(player.getSingle(event) == null) {
    		if (customConfig.get().getBoolean("DEBUG MODE") == true) {
				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Player not set!");
			}
    	}
    	else {
    		PacketIsPlayerOnline packet = new PacketIsPlayerOnline(player.getSingle(event));
    		Object obj = packet.send();
    		IsOnlineResult isOnline = (IsOnlineResult) obj;

    		if(isOnline == IsOnlineResult.ONLINE) {
    			
    			PacketGetPlayerUUID packet2 = new PacketGetPlayerUUID(player.getSingle(event));
    			UUID info = (UUID) packet2.send();
    			if (customConfig.get().getBoolean("DEBUG MODE") == true) {
    				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Getting global UUID of player " + player.getSingle(event) + "!");
    			}
    			
    			
    			PacketChat packet3 = new PacketChat(info, "/" + message.getSingle(event));
    			packet3.send();
    			
    			if (customConfig.get().getBoolean("DEBUG MODE") == true) {
    				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Executing command " + message.getSingle(event) + " as " + player.getSingle(event) + "!");
    			}
    		
    		}
    		else if (customConfig.get().getBoolean("DEBUG MODE") == true) {
				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Player " + player.getSingle(event) + " is offline!");
			}
    	}
    }
}
