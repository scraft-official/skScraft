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

import me.dommi2212.BungeeBridge.packets.PacketMessagePlayer;
import me.dommi2212.BungeeBridge.util.IsOnlineResult;
import me.scraft.addon.files.customConfig;
import me.dommi2212.BungeeBridge.packets.PacketGetPlayerUUID;
import me.dommi2212.BungeeBridge.packets.PacketIsPlayerOnline;


public class EffSendMessageToPlayer extends Effect {
	 
    static {
        Skript.registerEffect(EffSendMessageToPlayer.class, "send bungee[cord] message %string% to [player] %string%");
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
        return "Effect: send bungeecord message %string% to %player%";
    }
 
    @Override
    protected void execute(Event event) { 	
    	if(player.getSingle(event) == null) {
    		if (customConfig.get().getBoolean("DEBUG MODE") == true) {
				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Player not set!");
			}
    	}
    	else if(message.getSingle(event) == null) {
    		if (customConfig.get().getBoolean("DEBUG MODE") == true) {
				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Message not set!");
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
    			
    			
    			PacketMessagePlayer packet3 = new PacketMessagePlayer(info, message.getSingle(event));
    			packet3.send();
    			if (customConfig.get().getBoolean("DEBUG MODE") == true) {
    				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Message sent to player " + player.getSingle(event) + "!");
    			}
    		}
    		else {
    			if (customConfig.get().getBoolean("DEBUG MODE") == true) {
    				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Player " + player.getSingle(event) + " is offline!");
    			}
    		}
    	}
    }
}
