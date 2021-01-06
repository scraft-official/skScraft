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
import me.dommi2212.BungeeBridge.packets.PacketGetOnlineCountGlobal;
import me.scraft.addon.files.customConfig;

public class ExprGetNumberOfPlayersOnNetwork extends SimpleExpression<Number> {
 
   static {
       Skript.registerExpression(ExprGetNumberOfPlayersOnNetwork.class, Number.class, ExpressionType.COMBINED, "number of players on [bungee[cord]] network");
   }
 
 
   @Override
   public Class<? extends Number> getReturnType() {
       return Number.class;
   }
 
   @Override
   public boolean isSingle() {
       return true;
   }
 
   @Override
   public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
       return true;
   }
 
   @Override
   public String toString(@Nullable Event event, boolean debug) {
       return "Expr: number of players on bungeecord network";
   }
 
   @Override
   @Nullable
   protected Number[] get(Event event) {
	PacketGetOnlineCountGlobal packet = new PacketGetOnlineCountGlobal();
	int onlinecount = (int) packet.send();
	if (customConfig.get().getBoolean("DEBUG MODE") == true) {
		Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Number of proxy players is " + onlinecount + "!");
	}
	return new Number[] {onlinecount};	
   }
 }
