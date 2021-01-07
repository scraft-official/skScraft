package me.scraft.addon.elements.expressions;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.dommi2212.BungeeBridge.packets.PacketGetSlotsServer;
import me.dommi2212.BungeeBridge.packets.PacketIsServerOnline;
import me.scraft.addon.files.customConfig;

public class ExprGetNumberOfSlotsOfServer extends SimpleExpression<Number> {
 
   static {
       Skript.registerExpression(ExprGetNumberOfSlotsOfServer.class, Number.class, ExpressionType.COMBINED, "[number of] [max] slots of [bungee[cord]] server %string%");
   }
 
   private Expression<String> server;
 
   @Override
   public Class<? extends Number> getReturnType() {
       return Number.class;
   }
 
   @Override
   public boolean isSingle() {
       return true;
   }
 
   @SuppressWarnings("unchecked")
   @Override
   public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
       server = (Expression<String>) exprs[0];
       return true;
   }
 
   @Override
   public String toString(@Nullable Event event, boolean debug) {
       return "Expr: [number of] [max] slots of server %string%";
   }
 
   @Override
   @Nullable
   protected Number[] get(Event event) {
   if(server.getSingle(event) == null) {
	   if (customConfig.get().getBoolean("DEBUG MODE") == true) {
			Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Server not set!");
		}
	   return null;
   }
   else {
	   	PacketIsServerOnline packet = new PacketIsServerOnline(server.getSingle(event));
		
		boolean info = (boolean) packet.send();

		if(info == true) {
			
			PacketGetSlotsServer packet2 = new PacketGetSlotsServer(server.getSingle(event));
			int slots = (int) packet2.send();
			if (customConfig.get().getBoolean("DEBUG MODE") == true) {
				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Number of max slots on server " + server.getSingle(event) + " is " + slots);
			}
			return new Number[] {slots};
		}
	
		else {
			if (customConfig.get().getBoolean("DEBUG MODE") == true) {
				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Server is offline!");
			}
			return null;
		}
		
   	}
  }
}