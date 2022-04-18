package com.wandy.api.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DesbugarCommand implements CommandExecutor{

	public static HashMap<String,Long> timing = new HashMap<String,Long>();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(cmd.getName().equalsIgnoreCase("desbugar")) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				if(!timing.containsKey(p.getName())) {
					fixChunkPlayer(p);
					p.sendMessage("§aVocê e a chunk que você está foram desbugadas com sucesso.");
					timing.put(p.getName(), System.currentTimeMillis());
				}else if(passed5minutes(timing.get(p.getName()))){
					fixChunkPlayer(p);
					p.sendMessage("§aVocê e a chunk que você está foram desbugadas com sucesso.");
					timing.put(p.getName(), System.currentTimeMillis());
				}else {
				    p.sendMessage("§cVocê deve aguardar 5 minutos para utilizar este comando novamente.");
				    return true;
				}
			}
		}
		return false;
	}
	private boolean passed5minutes(long currentMillis)
	{
		long a = System.currentTimeMillis() - currentMillis;
	  long millis = 5 * 60 * 1000;
	  return a > millis;
	}
	   @SuppressWarnings("deprecation")
	public void fixChunkPlayer(final Player p) {
	        final int x = p.getLocation().getChunk().getX();
	        final int z = p.getLocation().getChunk().getZ();
	        p.teleport(p.getLocation());
	        p.getWorld().refreshChunk(x, z);
	        p.getWorld().unloadChunk(x, z);
	        p.getWorld().loadChunk(x, z);
	    }
}
