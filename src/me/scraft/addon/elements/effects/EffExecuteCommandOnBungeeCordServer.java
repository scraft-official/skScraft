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
import me.dommi2212.BungeeBridge.packets.PacketRunCommand;


public class EffExecuteCommandOnBungeeCordServer extends Effect {
	 
    static {
        Skript.registerEffect(EffExecuteCommandOnBungeeCordServer.class, "execute command %string% on [bungee[cord]] proxy");
    }
 
    private Expression<String> command;
 
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expression, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        this.command = (Expression<String>) expression[0];
        return true;
    }
 
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Effect: execute command %string% on [bungee[cord]] proxy";
    }
 
    @Override
    protected void execute(Event event) {
    	if(command.getSingle(event) == null) {
    		if (customConfig.get().getBoolean("DEBUG MODE") == true) {
				Bukkit.getLogger().info("§3[§bDEBUG§3] §eskScraft §e» §6Command not set!");
			}
    	}
    	else {
    		PacketRunCommand packet = new PacketRunCommand(command.getSingle(event));
    		packet.send();
    	}
    }
}
