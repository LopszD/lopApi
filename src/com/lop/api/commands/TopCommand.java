package com.wandy.api.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wandy.api.listeners.CombatLogListener;

public class TopCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command comando, String subcomando, String[] args) {
		if (comando.getName().equalsIgnoreCase("top")) {
			if (!sender.hasPermission("wandy.top")) {
				sender.sendMessage("§cVocê não tem permissão para executar este comando.");
				return true;
			}
			Player p = (Player) sender;
			if (CombatLogListener.combate.containsKey(p.getName())) {
				p.sendMessage("§cVocê não pode executar este comando em combate.");
				return true;
			}
			Location top = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
			top.setY(top.getWorld().getHighestBlockYAt(top) + 1);
			p.teleport(top);
			p.sendMessage("§aTeleportado para o topo.");
		}
		return false;
	}
}