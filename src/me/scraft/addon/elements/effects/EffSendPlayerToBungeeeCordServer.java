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

import me.dommi2212.BungeeBridge.packets.PacketConnectPlayer;
import me.dommi2212.BungeeBridge.util.IsOnlineResult;
import me.scraft.addon.files.customConfig;
import me.dommi2212.BungeeBridge.packets.PacketGetPlayerUUID;
import me.dommi2212.BungeeBridge.packets.PacketIsPlayerOnline;


public class EffSendPlayerToBungeeeCordServer extends Effect {
	 
    static {
        Skript.registerEffect(EffSendPlayerToBungeeeCordServer.class, "(send|connect) [player] %string% to bungee[cord] server %string%");
    }
 
    private Expression<String> server;
    private Expression<String> player;
 
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expression, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        this.server = (Expression<String>) expression[1];
        this.player = (Expression<String>) expression[0];
        return true;
    }
 
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Effect: (send|connect) [player] %string% to [bungee[cord]] server %string%";
    }
 
    @Override
    protected void execute(Event event) {
    	if(server.getSingle(event) == null) {
    		if (customConfig.get().getBoolean("DEBUG MODE") == true) {
				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Server not set!");
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
    			
    			
    			PacketConnectPlayer packet3 = new PacketConnectPlayer(info, server.getSingle(event));
    			packet3.send();
    			
    			if (customConfig.get().getBoolean("DEBUG MODE") == true) {
    				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Sending player " + player.getSingle(event) + " to server " + server.getSingle(event) + "!");
    			}
    		
    		}
    		else if (customConfig.get().getBoolean("DEBUG MODE") == true) {
				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Player " + player.getSingle(event) + " is offline!");
			}
    	}
    }
}
