package me.scraft.addon.elements.expressions;

import java.net.InetSocketAddress;
import java.util.UUID;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.dommi2212.BungeeBridge.packets.PacketGetPlayerUUID;
import me.dommi2212.BungeeBridge.packets.PacketIsPlayerOnline;
import me.dommi2212.BungeeBridge.util.IsOnlineResult;
import me.scraft.addon.files.customConfig;
import me.dommi2212.BungeeBridge.packets.PacketGetPlayerIP;
public class ExprGetPlayerIP extends SimpleExpression<String> {
 
   static {
       Skript.registerExpression(ExprGetPlayerIP.class, String.class, ExpressionType.COMBINED, "[bungee[cord]] network ip of [player] %string%");
   }
 
   private Expression<String> player;
 
   @Override
   public Class<? extends String> getReturnType() {
       return String.class;
   }
 
   @Override
   public boolean isSingle() {
       return true;
   }
 
   @SuppressWarnings("unchecked")
   @Override
   public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
       player = (Expression<String>) exprs[0];
       return true;
   }
 
   @Override
   public String toString(@Nullable Event event, boolean debug) {
       return "Expr: network ip of %string%";
   }
 
   @Override
   @Nullable
   protected String[] get(Event event) {
	   
	   if(player.getSingle(event) == null) {
		   return null;
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
			
			
			PacketGetPlayerIP packet3 = new PacketGetPlayerIP(info);
			InetSocketAddress info2 = (InetSocketAddress) packet3.send();
			
			if (customConfig.get().getBoolean("DEBUG MODE") == true) {
				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Ip of player " + player.getSingle(event) + " is " + info2.toString());
			}
			
			return new String[] {info2.toString()};
       
       }
       else {
    	   if (customConfig.get().getBoolean("DEBUG MODE") == true) {
				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Player " + player.getSingle(event) +  " is offline!");
			}
    	   return null;
       		}
       }
   }
}